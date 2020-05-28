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

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.elasticsearch.search.aggregations.metrics.geobounds.InternalGeoBounds;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @param <T> 应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx(),而不是isXxx()
 *            byte[]表jpg格式的图片数据
 */
public class ExportExcelUtil<T> implements Serializable {

    /**
     * serialVersionUID:序列化id
     */

    private static final long serialVersionUID = -5142024015171582474L;

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

    /**
     * 创建导出标题
     *
     * @param titles
     * @param sheetName   picStart:图片开始列数
     *                    picEnd:图片结束列数
     * @return
     */
    public static HSSFWorkbook createHSSFWorkbookInfo(String[] titles, List<Map<String, Object>> infoList, String sheetName,
                                                      Short picStart,Short picEnd) {
        // 生成一个表格
        //        XSSFWorkbook xBook = new XSSFWorkbook();// 2007版本用此workbook解析
        HSSFWorkbook workbook = new HSSFWorkbook();
        if (infoList == null || infoList.isEmpty()) {
            // 创建sheet
            HSSFSheet sheet = workbook.createSheet(sheetName);

            // 创建表头
            Row row = sheet.createRow(0);
            for (int celLength = 0; celLength < titles.length; celLength++) {
                // 创建相应的单元格
                Cell cell = row.createCell(celLength);
                cell.setCellValue(titles[celLength]);
            }

            return workbook;
        }

        // 数据总件数
        int listSize = infoList.size();
        // sheet总长度
        int sheetSize = listSize;
        if (listSize > 65534 || listSize < 1) {
            sheetSize = 65534;
        }
        //样式
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));

        //因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
        //所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
        // 计算一共有多少个工作表
        int sheetNum = (int) Math.ceil((double) listSize / (double) sheetSize);
        for (int i = 0; i < sheetNum; i++) {
            // 行号
            int rowNumber = 0;
            // 创建sheet
            HSSFSheet sheet = workbook.createSheet(sheetName + (i + 1));
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            sheet.setColumnWidth(7, 256*35);
            //获取开始索引和结束索引
            int firstIndex = i * sheetSize;
            int lastIndex = (i + 1) * sheetSize > listSize ? listSize : (i + 1) * sheetSize;

            // 构建临时数据
            List<Map<String, Object>> tempList = infoList.subList(firstIndex, lastIndex);

            // 创建表头
            Row row = sheet.createRow(rowNumber);
            for (int celLength = 0; celLength < titles.length; celLength++) {
                // 创建相应的单元格
                Cell cell = row.createCell(celLength);
                cell.setCellValue(titles[celLength]);
                sheet.setDefaultColumnStyle(celLength, style);
            }

            if (!tempList.isEmpty()) {
                for (Map<String, Object> info : tempList) {
                    rowNumber++;
                    row = sheet.createRow(rowNumber);
                    row.setHeightInPoints(100);
                    for (int celLength = 0; celLength < titles.length; celLength++) {
                        // 创建相应的单元格
                        if("布控图片".equals(titles[celLength])||"人员图片".equals(titles[celLength])){
                            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
                                    picStart, rowNumber,picEnd, rowNumber+1);
                            patriarch.createPicture(anchor,workbook.addPicture((byte[]) info.get(titles[celLength]),
                                    HSSFWorkbook.PICTURE_TYPE_JPEG));
                        }else{
                            Cell cell = row.createCell(celLength);
                            cell.setCellValue(info.get(titles[celLength]).toString());
                            sheet.setDefaultColumnStyle(celLength, style);
                        }
                    }
                }
            }
        }

        return workbook;
    }

    /**
     * 创建导出标题
     *
     * @param titles
     * @param sheetName
     * @return
     */
    public static HSSFWorkbook createHSSFWorkbookObjectInfo(String[] titles, List<Map<String, Object>> infoList, String sheetName) {
        // 生成一个表格
        //        XSSFWorkbook xBook = new XSSFWorkbook();// 2007版本用此workbook解析
        HSSFWorkbook workbook = new HSSFWorkbook();
        if (infoList == null || infoList.isEmpty()) {
            // 创建sheet
            HSSFSheet sheet = workbook.createSheet(sheetName);
            // 创建表头
            Row row = sheet.createRow(0);
            for (int celLength = 0; celLength < titles.length; celLength++) {
                // 创建相应的单元格
                Cell cell = row.createCell(celLength);
                cell.setCellValue(titles[celLength]);
            }

            return workbook;
        }

        // 数据总件数
        int listSize = infoList.size();
        // sheet总长度
        int sheetSize = listSize;
        if (listSize > 65534 || listSize < 1) {
            sheetSize = 65534;
        }
        //样式
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));

        //因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
        //所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
        // 计算一共有多少个工作表
        int sheetNum = (int) Math.ceil((double) listSize / (double) sheetSize);
        for (int i = 0; i < sheetNum; i++) {
            // 行号
            int rowNumber = 0;
            // 创建sheet
            HSSFSheet sheet = workbook.createSheet(sheetName + (i + 1));

            //获取开始索引和结束索引
            int firstIndex = i * sheetSize;
            int lastIndex = (i + 1) * sheetSize > listSize ? listSize : (i + 1) * sheetSize;

            // 构建临时数据
            List<Map<String, Object>> tempList = infoList.subList(firstIndex, lastIndex);

            // 创建表头
            Row row = sheet.createRow(rowNumber);
            for (int celLength = 0; celLength < titles.length; celLength++) {
                // 创建相应的单元格
                Cell cell = row.createCell(celLength);
                cell.setCellValue(titles[celLength]);
                sheet.setDefaultColumnStyle(celLength, style);
            }

            if (!tempList.isEmpty()) {
                for (Map<String, Object> info : tempList) {
                    rowNumber++;
                    row = sheet.createRow(rowNumber);
                    for (int celLength = 0; celLength < titles.length; celLength++) {
                        // 创建相应的单元格
                        Cell cell = row.createCell(celLength);
                        //cell.setCellValue(info.get(titles[celLength]));
                        try {
                            if (info.get(titles[celLength]) == null) {
                                cell.setCellValue("");
                            } else if (info.get(titles[celLength]) instanceof String) {
                                cell.setCellValue(String.valueOf(info.get(titles[celLength])));
                            } else if (info.get(titles[celLength]) instanceof Integer) {
                                cell.setCellValue((Integer) info.get(titles[celLength]));
                            } else if (info.get(titles[celLength]) instanceof Long) {
                                cell.setCellValue((Long) info.get(titles[celLength]));
                            } else if (info.get(titles[celLength]) instanceof Double) {
                                cell.setCellValue((Double) info.get(titles[celLength]));
                            } else if (info.get(titles[celLength]) instanceof Float) {
                                cell.setCellValue((Float) info.get(titles[celLength]));
                            } else if (info.get(titles[celLength]) instanceof Date) {
                                DataFormat cellFormat = workbook.createDataFormat();
                                style.setDataFormat(cellFormat.getFormat("yyyy-MM-dd HH:mm:ss"));
                                cell.setCellValue((Date) info.get(titles[celLength]));
                            } else if (info.get(titles[celLength]) instanceof BigDecimal) {
                                double doubleVal = ((BigDecimal) info.get(titles[celLength])).doubleValue();
                                DataFormat decimalFormat = workbook.createDataFormat();
                                style.setDataFormat(decimalFormat.getFormat("######0.00"));
                                cell.setCellValue(doubleVal);
                            } else {
                                cell.setCellValue(String.valueOf(info.get(titles[celLength]).toString()));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            cell.setCellValue(info.get(titles[celLength]).toString());
                        }
                        sheet.setDefaultColumnStyle(celLength, style);
                    }
                }
            }
        }

        return workbook;
    }

    /**
     * 创建导出标题
     *
     * @param titles
     * @param sheetName
     * @return
     */
    public static HSSFWorkbook createHSSFWorkbookInfoForHSBC(String[] titles, List<List<String>> infoList, String sheetName) {
        // 生成一个表格
        XSSFWorkbook xBook = new XSSFWorkbook();// 2007版本用此workbook解析
        HSSFWorkbook workbook = new HSSFWorkbook();
        int rowNumber = 0;
        HSSFSheet sheet = workbook.createSheet(sheetName);

        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));

        Row row = sheet.createRow(rowNumber);
        for (int celLength = 0; celLength < titles.length; celLength++) {
            // 创建相应的单元格
            Cell cell = row.createCell(celLength);
            cell.setCellValue(titles[celLength]);
            sheet.setDefaultColumnStyle(celLength, style);
        }

        if (infoList != null && infoList.size() > 0) {
            for (List<String> info : infoList) {
                rowNumber++;
                row = sheet.createRow(rowNumber);
                for (int celLength = 0; celLength < titles.length; celLength++) {
                    // 创建相应的单元格
                    Cell cell = row.createCell(celLength);
                    cell.setCellValue(info.get(celLength));
                    sheet.setDefaultColumnStyle(celLength, style);
                }
            }
        }

        return workbook;
    }

    /**
     * 创建导出标题
     *
     * @param titles
     * @param sheetName
     * @return
     */
    public static HSSFWorkbook createHSSFWorkbookInfo(String[] titles, List<Map> infoList, String sheetName, int type) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        // 2007版本用此workbook解析

        int rowNumber = 0;
        HSSFSheet sheet = workbook.createSheet(sheetName);

        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));

        Row row = sheet.createRow(rowNumber);
        for (int celLength = 0; celLength < titles.length; celLength++) {
            // 创建相应的单元格
            Cell cell = row.createCell(celLength);
            cell.setCellValue(titles[celLength]);

            sheet.setDefaultColumnStyle(celLength, style);
        }

        if (infoList != null && !infoList.isEmpty()) {
            for (Map<String, String> info : infoList) {
                rowNumber++;
                row = sheet.createRow(rowNumber);
                for (int celLength = 0; celLength < titles.length; celLength++) {
                    // 创建相应的单元格
                    Cell cell = row.createCell(celLength);
                    cell.setCellValue(info.get(titles[celLength]));

                    sheet.setDefaultColumnStyle(celLength, style);
                }
            }
        }
        return workbook;
    }

    /**
     * 创建导出标题
     *
     * @param titles
     * @param sheetName
     * @return
     */
    public static SXSSFWorkbook createSXSSFWorkbookInfo(String[] titles, List<Map<String, Object>> infoList, String sheetName) {
        // 生成一个表格
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);// 2007版本用此workbook解析
        //        HSSFWorkbook workbook = new HSSFWorkbook();
        if (infoList == null || infoList.isEmpty()) {
            // 创建sheet
            SXSSFSheet sheet = workbook.createSheet(sheetName);
            // 创建表头
            Row row = sheet.createRow(0);
            for (int celLength = 0; celLength < titles.length; celLength++) {
                // 创建相应的单元格
                Cell cell = row.createCell(celLength);
                cell.setCellValue(titles[celLength]);
            }

            return workbook;
        }

        // 数据总件数
        int listSize = infoList.size();
        // sheet总长度
        int sheetSize = listSize;
        if (listSize > 65534 || listSize < 1) {
            sheetSize = 65534;
        }
        //样式
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));

        //因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
        //所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
        // 计算一共有多少个工作表
        int sheetNum = (int) Math.ceil((double) listSize / (double) sheetSize);
        for (int i = 0; i < sheetNum; i++) {
            // 行号
            int rowNumber = 0;
            // 创建sheet
            SXSSFSheet sheet = workbook.createSheet(sheetName + (i + 1));

            //获取开始索引和结束索引
            int firstIndex = i * sheetSize;
            int lastIndex = (i + 1) * sheetSize > listSize ? listSize : (i + 1) * sheetSize;

            // 构建临时数据
            List<Map<String, Object>> tempList = infoList.subList(firstIndex, lastIndex);

            // 创建表头
            Row row = sheet.createRow(rowNumber);
            for (int celLength = 0; celLength < titles.length; celLength++) {
                // 创建相应的单元格
                Cell cell = row.createCell(celLength);
                cell.setCellValue(titles[celLength]);
                sheet.setDefaultColumnStyle(celLength, style);
            }

            if (!tempList.isEmpty()) {
                for (Map<String, Object> info : tempList) {
                    rowNumber++;
                    row = sheet.createRow(rowNumber);
                    for (int celLength = 0; celLength < titles.length; celLength++) {
                        // 创建相应的单元格
                        Cell cell = row.createCell(celLength);
                        try {
                            if (info.get(titles[celLength]) == null) {
                                cell.setCellValue("");
                            } else if (info.get(titles[celLength]) instanceof String) {
                                cell.setCellValue(String.valueOf(info.get(titles[celLength])));
                            } else if (info.get(titles[celLength]) instanceof Integer) {
                                cell.setCellValue((Integer) info.get(titles[celLength]));
                            } else if (info.get(titles[celLength]) instanceof Long) {
                                cell.setCellValue((Long) info.get(titles[celLength]));
                            } else if (info.get(titles[celLength]) instanceof Double) {
                                cell.setCellValue((Double) info.get(titles[celLength]));
                            } else if (info.get(titles[celLength]) instanceof Float) {
                                cell.setCellValue((Float) info.get(titles[celLength]));
                            } else if (info.get(titles[celLength]) instanceof Date) {
                                DataFormat cellFormat = workbook.createDataFormat();
                                style.setDataFormat(cellFormat.getFormat("yyyy-MM-dd HH:mm:ss"));
                                cell.setCellValue((Date) info.get(titles[celLength]));
                            } else if (info.get(titles[celLength]) instanceof BigDecimal) {
                                double doubleVal = ((BigDecimal) info.get(titles[celLength])).doubleValue();
                                DataFormat decimalFormat = workbook.createDataFormat();
                                style.setDataFormat(decimalFormat.getFormat("######0.00"));
                                cell.setCellValue(doubleVal);
                            } else {
                                cell.setCellValue(String.valueOf(info.get(titles[celLength]).toString()));
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            cell.setCellValue(info.get(titles[celLength]).toString());
                        }

                        sheet.setDefaultColumnStyle(celLength, style);
                    }
                }
            }
        }

        return workbook;
    }

    /**
     * 创建导出标题 20W每页
     *
     * @param titles
     * @param sheetName
     * @return
     */
    public static XSSFWorkbook createXSSFWorkbookInfo20(XSSFWorkbook workbook1, String[] titles, List<Map<String,
            String>> infoList, String sheetName, int rowNumber) {
        // 生成一个表格
        XSSFWorkbook workbook = workbook1;// 2007版本用此workbook解析
        if (infoList == null || infoList.isEmpty()) {
            // 创建sheet
            XSSFSheet sheet = workbook.createSheet(sheetName);
            // 创建表头
            Row row = sheet.createRow(0);
            for (int celLength = 0; celLength < titles.length; celLength++) {
                // 创建相应的单元格
                Cell cell = row.createCell(celLength);
                cell.setCellValue(titles[celLength]);
            }

            return workbook;
        }

        // 数据总件数
        int listSize = infoList.size();
        // sheet总长度
        int sheetSize = listSize;
        if (listSize > 200001 || listSize < 1) {
            sheetSize = 200001;
        }
        // 样式
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));

        // 因为2003的Excel一个工作表最多可以有65536条记录，除去列头剩下65535条
        // 所以如果记录太多，需要放到多个工作表中，其实就是个分页的过程
        // 计算一共有多少个工作表
        int sheetNum = (int) Math.ceil((double) listSize / (double) sheetSize);
        for (int i = 0; i < sheetNum; i++) {
            // 创建sheet


            // 获取开始索引和结束索引
            int firstIndex = i * sheetSize;
            int lastIndex = (i + 1) * sheetSize > listSize ? listSize : (i + 1) * sheetSize;

            // 构建临时数据
            List<Map<String, String>> tempList = infoList.subList(firstIndex, lastIndex);
            Row row = null;
            XSSFSheet sheet = null;
            if (rowNumber == 0) {
                sheet = workbook.createSheet(sheetName + (i + 1));
                // 创建表头
                row = sheet.createRow(rowNumber);
                for (int celLength = 0; celLength < titles.length; celLength++) {
                    // 创建相应的单元格
                    Cell cell = row.createCell(celLength);
                    cell.setCellValue(titles[celLength]);
                    sheet.setDefaultColumnStyle(celLength, style);
                }
            } else {
                sheet = workbook.getSheetAt(0);
            }
            if (!tempList.isEmpty()) {
                for (Map<String, String> info : tempList) {
                    rowNumber++;
                    row = sheet.createRow(rowNumber);
                    for (int celLength = 0; celLength < titles.length; celLength++) {
                        // 创建相应的单元格
                        Cell cell = row.createCell(celLength);
                        cell.setCellValue(info.get(titles[celLength]));

                        sheet.setDefaultColumnStyle(celLength, style);
                    }
                }
            }
        }

        return workbook;
    }

    /**
     * 输出附件
     *
     * @return
     */
    public static void writeExcelFileLocal(Workbook workbook,
                                           String fileName) {
        // 根据列名填充相应的数据
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出附件
     *
     * @param fileName
     * @return
     */
    public static void writeExcelFile(HttpServletRequest request, HttpServletResponse response, Workbook workbook, String fileName) {
        // 根据列名填充相应的数据
        try (ServletOutputStream out = response.getOutputStream();) {
            response.reset();
            response.setContentType("application/msexcel;charset=utf-8");
            //response.setHeader("content-disposition", "attachment;filename=" + new String((fileName).getBytes("UTF-8"), "ISO8859-1"));
            String userAgent = request.getHeader("user-agent");
            if (userAgent != null && userAgent.indexOf("Edge") >= 0) {
                fileName = URLEncoder.encode(fileName, "UTF8");
            } else if (userAgent.indexOf("Firefox") >= 0 || userAgent.indexOf("Chrome") >= 0
                    || userAgent.indexOf("Safari") >= 0) {
                fileName = new String((fileName).getBytes("UTF-8"), "ISO8859-1");
            } else {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
                //fileName = URLEncoder.encode(fileName, "UTF8"); //其他浏览器
            }
            response.setHeader("content-disposition", "attachment;filename=" + fileName);

            workbook.write(out);
            out.flush();
//            out.close();
        } catch (IOException e) {
            if (!e.getMessage().startsWith("org.apache.catalina.connector.ClientAbortException")) {
                e.printStackTrace();
            }
        }
    }

    public static Date getYYYYMMDDHHMMSSDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(date + ":00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getYYYYMMDDDate(Date thisDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(thisDate);
    }

    public static Date getStartTime(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(date + " 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getEndTime(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(date + " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计算时间
     *
     * @param date
     * @param day
     * @return
     */
    public static Date calDate(Date date, long day) {
        return new Date(date.getTime() + (day + 1) * 24L * 3600000L);
    }


    /**
     * 计算时间
     *
     * @param date
     * @param day
     * @return
     */
    public static String calDateYYYYMMDD(Date date, long day) {
        Date thisDate = calDate(date, day);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(thisDate);
    }

    public static int DAYS = 7;

    /**
     * 传入workbook和list 以及开始行。适用于读取现有的带有表头的模板 然后填充数据用
     *
     * @param colSize   有多少个格子。(列)
     * @param infoList
     * @param sheetName
     * @return
     */
    public static void writeListToHSSFWorkbook(XSSFWorkbook workbook, Integer startRow, List<List<String>> infoList, String sheetname) {
        // 数据总件数
        int listSize = infoList.size();
        // sheet总长度
        int sheetSize = listSize;
        if (listSize > 1000000 || listSize < 1) {
            sheetSize = 1000000;
        }
        //样式
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("@"));

        int sheetNum = (int) Math.ceil((double) listSize / (double) sheetSize);
        for (int i = 0; i < sheetNum; i++) {
            // 行号
            int rowNumber = startRow;
            // 创建sheet
//            XSSFSheet sheet = workbook.createSheet(sheetname + (i + 1));
            XSSFSheet sheet = workbook.getSheetAt(i);
            //获取开始索引和结束索引
            int firstIndex = i * sheetSize;
            int lastIndex = (i + 1) * sheetSize > listSize ? listSize : (i + 1) * sheetSize;

            // 构建临时数据
            List<List<String>> tempList = infoList.subList(firstIndex, lastIndex);

            Row row = null;
            if (!tempList.isEmpty()) {
                // 遍历每行，
                for (List<String> info : tempList) {
                    rowNumber++;
                    row = sheet.createRow(rowNumber);

                    if (info != null && !info.isEmpty()) {
                        // 每个格子
                        for (int k = 0; k < info.size(); k++) {
                            Cell cell = row.createCell(k);
                            cell.setCellValue(info.get(k));
                            sheet.setDefaultColumnStyle(k, style);
                        }
                    }
                }
            }
        }
    }
}