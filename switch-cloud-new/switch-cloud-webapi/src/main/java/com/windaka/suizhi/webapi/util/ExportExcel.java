/**
 * Description:利用poi导出excel
 * Copyright: Copyright (HYJF Corporation)2015
 * Company: HYJF Corporation
 *
 * @author: 王坤
 * @version: 1.0
 * Created at: 2015年11月26日 下午6:47:27
 * Modification History:
 * Modified by :
 */

package com.windaka.suizhi.webapi.util;

import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.constants.CommonConstants;
import com.windaka.suizhi.common.exception.OssRenderException;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @param <T> 应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class ExportExcel<T> implements Serializable {

    /**
     * serialVersionUID:序列化id
     */

    private static final long serialVersionUID = -5142024015171582474L;

    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param sheetName 表格sheet名称
     * @param author    表格作者
     * @param dataset   需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *                  javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param pattern   如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     * @throws IOException
     */
    public void exportExcel(String sheetName, String author, Map<String, String> columnNames, Collection<T> dataset, HttpServletResponse response, String pattern) throws IOException {
        exportExcel(sheetName, author, columnNames, dataset, response.getOutputStream(), pattern);
    }

    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     *
     * @param sheetName 表格sheet名称
     * @param author    表格作者
     * @param dataset   需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *                  javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out       与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern   如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    public void exportExcel(String sheetName, String author, Map<String, String> columnNames, Collection<T> dataset, ServletOutputStream out, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor(author);
        // 产生表格标题行
        HSSFRow columnTitleRow = sheet.createRow(0);
        // 根据标题列名字，导出时显示相应的导出列名字
        Iterator<Map.Entry<String, String>> columIterator = columnNames.entrySet().iterator();
        int columnIndex = 0;
        String[] columns = new String[columnNames.size()];
        while (columIterator.hasNext()) {
            Map.Entry<String, String> entry = columIterator.next();
            String column = (String) entry.getKey();
            String columnName = (String) entry.getValue();
            HSSFCell cellTitle = columnTitleRow.createCell(columnIndex);
            cellTitle.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(columnName);
            cellTitle.setCellValue(text);
            columns[columnIndex] = column;
            columnIndex++;
        }
        // 遍历集合数据，产生数据行
        int index = 1;// 行号
        Iterator<T> it = dataset.iterator();
        while (it.hasNext()) {
            // 创建一行
            HSSFRow columnValueRow = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            for (int i = 0; i < columns.length; i++) {
                // 创建相应的单元格
                HSSFCell cellValue = columnValueRow.createCell(i);
                cellValue.setCellStyle(style2);
                String getMethodName = "get" + columns[i].substring(0, 1).toUpperCase() + columns[i].substring(1);
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if (value != null) {
                        if (value instanceof Integer) {
                            int intValue = (Integer) value;
                            textValue = String.valueOf(intValue);
                        } else if (value instanceof Float) {
                            float fValue = (Float) value;
                            textValue = String.valueOf(fValue);
                        } else if (value instanceof Double) {
                            double dValue = (Double) value;
                            textValue = String.valueOf(dValue);
                        } else if (value instanceof Long) {
                            long longValue = (Long) value;
                            textValue = String.valueOf(longValue);
                        } else if (value instanceof Boolean) {
                            boolean bValue = (Boolean) value;
                            textValue = "true";
                            if (!bValue) {
                                textValue = "false";
                            }
                        } else if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else if (value instanceof byte[]) {
                            // 有图片时，设置行高为60px;
                            columnValueRow.setHeightInPoints(60);
                            // 设置图片所在列宽度为80px,注意这里单位的一个换算
                            sheet.setColumnWidth(i, (short) (35.7 * 80));
                            // sheet.autoSizeColumn(i);
                            byte[] bsValue = (byte[]) value;
                            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6, index);
                            anchor.setAnchorType(2);
                            patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            textValue = value.toString();
                        }
                    } else {
                        continue;
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cellValue.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(textValue);
                            HSSFFont font3 = workbook.createFont();
                            font3.setColor(HSSFColor.BLACK.index);
                            richString.applyFont(font3);
                            cellValue.setCellValue(richString);
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
                // 自动调整列的宽度
                sheet.autoSizeColumn(i);
            }
            index++;
        }
        // 根据列名填充相应的数据
        try {
            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建导出标题
     *
     * @param titles
     * @param sheetName
     * @return
     */
    public static HSSFSheet createHSSFWorkbookTitle(HSSFWorkbook workbook, String[] titles, String sheetName) {

        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(sheetName);
        Row row = sheet.createRow(0);
        for (int celLength = 0; celLength < titles.length; celLength++) {
            // 创建相应的单元格
            Cell cell = row.createCell(celLength);
            cell.setCellValue(titles[celLength]);
        }

        return sheet;
    }
    public static String toUtf8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }


    /**
     * 输出附件
     *
     * @param titles
     * @return
     */
    public static void writeExcelFile(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook workbook, String[] titles, String fileName) {
        // 根据列名填充相应的数据
        try (ServletOutputStream out = response.getOutputStream();) {

            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
//            response.setContentType("application/msexcel;charset=utf-8");
            //response.setHeader("content-disposition", "attachment;filename=" + new String((fileName).getBytes("UTF-8"), "ISO8859-1"));
            String userAgent = request.getHeader("user-agent");
            if (userAgent != null && userAgent.indexOf("Edge") >= 0) {
                fileName = URLEncoder.encode(fileName, "UTF8");
            } else if (userAgent.indexOf("Firefox") >= 0 || userAgent.indexOf("Chrome") >= 0
                    || userAgent.indexOf("Safari") >= 0) {
                fileName = new String((fileName).getBytes("UTF-8"), "ISO8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF8"); //其他浏览器
            }
//            fileName =toUtf8String(fileName);
            response.setHeader("content-disposition", "attachment;filename=" + fileName);

            workbook.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出附件
     *
     * @param response
     * @param fileName
     * @param content
     * @return
     */
    public static void writeFile(HttpServletResponse response, String fileName, String content) {
        // 根据列名填充相应的数据
        try (ServletOutputStream out = response.getOutputStream();) {
            response.reset();
            response.setContentType("application/octet-stream;charset=utf-8");
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            out.write(content.getBytes("UTF-8"));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 功能描述: 校验文件格式是否正确
     * @auther: lixianhua
     * @date: 2020/5/14 10:48
     * @param:
     * @return:
     */
    public static String  checkFile(MultipartFile file) throws OssRenderException {
        //判断文件是否存在
        if (null == file) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "文件不存在");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if (!fileName.endsWith(CommonConstants.XLS) && !fileName.endsWith(CommonConstants.XLSX)) {
            throw new OssRenderException(ReturnConstants.CODE_FAILED, "文件不是excel文件");
        }
        return fileName;
    }
    /**
     * 功能描述: 获取不同版本的Workbook
     * @auther: lixianhua
     * @date: 2020/5/14 10:48
     * @param:
     * @return:
     */
    public static Workbook getWorkBook(MultipartFile file) throws IOException {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        //获取excel文件的io流
        InputStream is = file.getInputStream();
        //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
        if (fileName.endsWith(CommonConstants.XLS)) {
            //2003
            workbook = new HSSFWorkbook(is);

        } else if (fileName.endsWith(CommonConstants.XLSX)) {
            //2007
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }
    /**
     * 功能描述: 获取表格中的值
     * @auther: lixianhua
     * @date: 2020/5/14 10:49
     * @param:
     * @return:
     */
    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            //数字
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            //字符串
            case Cell.CELL_TYPE_STRING:
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            //Boolean
            case Cell.CELL_TYPE_BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            //公式
            case Cell.CELL_TYPE_FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            //空值
            case Cell.CELL_TYPE_BLANK:
                cellValue = "";
                break;
            //故障
            case Cell.CELL_TYPE_ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
    /**
     * 获取图片和位置 (xls)
     * @param sheet
     * @return
     * @throws IOException
     */
    public static Map<String, PictureData> getPictures1 (HSSFSheet sheet) {
        Map<String, PictureData> map = new HashMap<String, PictureData>();
        Map<String, PictureData> result = new HashMap<String, PictureData>();
        List<HSSFShape> list = sheet.getDrawingPatriarch().getChildren();
        for (HSSFShape shape : list) {
            if (shape instanceof HSSFPicture) {
                HSSFPicture picture = (HSSFPicture) shape;
                HSSFClientAnchor cAnchor = (HSSFClientAnchor) picture.getAnchor();
                PictureData pdata = picture.getPictureData();
//                String key = cAnchor.getRow1() + "-" + cAnchor.getCol1(); // 行号-列号
                String key = cAnchor.getRow1() +"";// 行号
                map.put(key, pdata);
            }
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(x->result.put(x.getKey(),x.getValue()));
        }
        return result;
    }
    /**
     * 获取图片和位置 (xlsx)
     * @param sheet
     * @return
     * @throws IOException
     */
    public static Map<String, PictureData> getPictures2 (XSSFSheet sheet) throws IOException {
        Map<String, PictureData> map = new HashMap<String, PictureData>();
        Map<String, PictureData> result = new HashMap<String, PictureData>();
        List<POIXMLDocumentPart> list = sheet.getRelations();
        for (POIXMLDocumentPart part : list) {
            if (part instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) part;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture picture = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = picture.getPreferredSize();
                    CTMarker marker = anchor.getFrom();
                    String key = marker.getRow() + "" ;// 行号
                    map.put(key, picture.getPictureData());
                }
            }
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(x->result.put(x.getKey(),x.getValue()));
        }
        return result;
    }

}