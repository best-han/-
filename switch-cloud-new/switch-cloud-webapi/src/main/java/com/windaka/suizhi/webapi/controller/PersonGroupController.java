package com.windaka.suizhi.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.constants.CommonConstants;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.webapi.dao.FaceGroupMapper;
import com.windaka.suizhi.webapi.dao.auto.FaceGroupDetailMapper;
import com.windaka.suizhi.webapi.feign.ImageFeatureClient;
import com.windaka.suizhi.webapi.model.FaceGroup;
import com.windaka.suizhi.webapi.model.FaceGroupDetail;
import com.windaka.suizhi.webapi.model.FaceGroupDetailExample;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroup;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroupDetail;
import com.windaka.suizhi.webapi.service.PersonGroupService;
import com.windaka.suizhi.webapi.service.impl.ImageToFdfsServiceImpl;
import com.windaka.suizhi.webapi.util.ExportExcel;
import com.windaka.suizhi.webapi.util.ExportExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @ClassName PersonGroupController
 * @Description 人员布控控制类
 * @Author lixianhua
 * @Date 2020/4/8 9:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/personGroup")
public class PersonGroupController extends BaseController {

    @Autowired
    private PersonGroupService personGroupService;

    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;

    @Autowired
    private FaceGroupMapper faceGroupMapper;

    @Autowired
    private FaceGroupDetailMapper faceGroupDetailMapper;

    @Autowired
    private ImageFeatureClient imageFeatureClient;

    /**
     * 功能描述: 获取人员布控库集合
     *
     * @auther: lixianhua
     * @date: 2020/4/8 10:09
     * @param:
     * @return:
     */
    @GetMapping("/groupList")
    public Map<String, Object> groupList(HttpServletRequest request) {
        JSONObject object = new JSONObject();
        try {
            ExtFaceGroup faceGroup = new ExtFaceGroup();
            faceGroup.setName(request.getParameter("name"));
            faceGroup.setOpUserId(request.getParameter("opUserId"));
            List<ExtFaceGroup> list = this.personGroupService.selectGroupList(faceGroup, request);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage", paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<ExtFaceGroup>());
            }
            return render(object);
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }

    /**
     * 功能描述: 添加人员布控库
     *
     * @auther: lixianhua
     * @date: 2020/4/8 11:41
     * @param:
     * @return:
     */
    @PostMapping("/insertGroup")
    public Map<String, Object> insertGroup(@RequestBody FaceGroup faceGroup) {
        try {
            int num = this.personGroupService.insertRecord(faceGroup);
            if (num == 0) {
                return failRender(ReturnConstants.CODE_FAILED, "添加失败");
            }
            return render();
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 根据ID更新人员布控库
     *
     * @auther: lixianhua
     * @date: 2020/4/8 12:01
     * @param:
     * @return:
     */
    @PutMapping("/updateGroup")
    public Map<String, Object> updateGroup(@RequestBody FaceGroup faceGroup) {
        try {
            int num = this.personGroupService.updateGroup(faceGroup);
            if (num == 0) {
                return failRender(ReturnConstants.CODE_FAILED, "修改失败");
            }
            return render();
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 根据ID删除人员布控库
     *
     * @auther: lixianhua
     * @date: 2020/4/14 15:13
     * @param:
     * @return:
     */
    @DeleteMapping("/deleteGroup/{id}")
    public Map<String, Object> deleteGroup(@PathVariable String id) {
        try {
            int num = this.personGroupService.deleteGroupById(id);
            if (num == 0) {
                return failRender(ReturnConstants.CODE_FAILED, "删除失败");
            }
            return render();
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 布控人员列表
     *
     * @auther: lixianhua
     * @date: 2020/4/9 13:57
     * @param:
     * @return:
     */
    @GetMapping("/personList")
    public Map<String, Object> personList(HttpServletRequest request, ExtFaceGroupDetail groupDetail) {
        JSONObject object = new JSONObject();
        try {
            groupDetail.setPageFlag(true);
            List<ExtFaceGroupDetail> list = this.personGroupService.selectPersonList(groupDetail, request);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage", paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<ExtFaceGroup>());
            }
            return render(object);
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 添加人员布控
     *
     * @auther: lixianhua
     * @date: 2020/4/14 16:10
     * @param:
     * @return:
     */
    @PostMapping("/insertPerson")

    public Map<String, Object> insertPerson(@RequestBody ExtFaceGroupDetail faceGroupDetail) {
        try {
            int num = this.personGroupService.insertPerson(faceGroupDetail);
            if (num == 0) {
                return failRender(ReturnConstants.CODE_FAILED, "添加失败");
            }
            return render();
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }

    /**
     * 功能描述: 删除布控人员信息
     *
     * @auther: lixianhua
     * @date: 2020/4/17 16:51
     * @param:
     * @return:
     */
    @DeleteMapping("/deletePerson/{id}")
    public Map<String, Object> deletePerson(@PathVariable String id) {
        try {
            int num = this.personGroupService.deletePerson(id);
            if (num == 0) {
                return failRender(ReturnConstants.CODE_FAILED, "删除失败");
            }
            return render();
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }

    /**
     * 功能描述: 更新布控人员信息
     *
     * @auther: lixianhua
     * @date: 2020/4/16 10:59
     * @param:
     * @return:
     */
    @PutMapping("/updatePerson")
    public Map<String, Object> updatePerson(@RequestBody ExtFaceGroupDetail detail) {
        try {
            int num = this.personGroupService.updatePersonById(detail);
            if (num == 0) {
                return failRender(ReturnConstants.CODE_FAILED, "更新失败");
            }
            return render();
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }

    /**
     * 功能描述: 导出人员布控文件
     *
     * @auther: lixianhua
     * @date: 2020/5/13 15:55
     * @param:
     * @return:
     */
    @GetMapping(value = "/exportAction")
    public void downloadAction(HttpServletRequest request, HttpServletResponse response, ExtFaceGroupDetail detail) {
        try {
            // 表格sheet名称
            String sheetName = "操作查询";
            String fileName = "人员布控" + TimesUtil.getServerDateTime(8, new Date()) + ".xls";
            detail.setPageFlag(false);
            List<ExtFaceGroupDetail> list = this.personGroupService.selectPersonList(detail, request);
            String[] titles = new String[]{"所属人员库", "人员姓名", "电话号码", "证件号码", "报警等级", "布控原因", "布控时间", "布控图片", "操作人员"};

            List<Map<String, Object>> infoList = new ArrayList<>();
            if (list != null && list.size() > 0) {
                Map<String, Object> map = null;
                for (ExtFaceGroupDetail record : list) {
                    byte[] arr = this.imageToFdfsService.downloadImage(record.getPersonImage());
                    map = new HashMap<>();
                    map.put("所属人员库", record.getGroupName());
                    map.put("人员姓名", record.getPersonName());
                    map.put("电话号码", record.getPersonPhone());
                    map.put("证件号码", record.getPersonPaperNumber());
                    map.put("报警等级", record.getLevelName());
                    map.put("布控原因", record.getReason());
                    map.put("布控时间", record.getControlPeriods());
                    map.put("布控图片", arr);
                    map.put("操作人员", record.getCreateUser());
                    infoList.add(map);
                }
            }
            // 声明一个工作薄
            HSSFWorkbook workbook = ExportExcelUtil.createHSSFWorkbookInfo(titles, infoList, sheetName,new Short("7"),
                    new Short("8"));
            // 导出
            ExportExcel.writeExcelFile(request, response, workbook, titles, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 功能描述: 导出人员撤控文件
     * @auther: lixianhua
     * @date: 2020/5/18 8:42
     * @param:
     * @return:
     */
    @GetMapping(value = "/exportWithdrawAction")
    public void downloadWithdrawAction(HttpServletRequest request, HttpServletResponse response, ExtFaceGroupDetail detail) {
        try {
            // 表格sheet名称
            String sheetName = "操作查询";
            String fileName = "人员撤控" + TimesUtil.getServerDateTime(8, new Date()) + ".xls";
            detail.setPageFlag(false);
            List<ExtFaceGroupDetail> list = this.personGroupService.selectPersonList(detail, request);
            String[] titles = new String[]{ "人员姓名", "电话号码", "证件号码", "报警等级", "布控原因", "撤控时间", "布控图片", "操作人员"};

            List<Map<String, Object>> infoList = new ArrayList<>();
            if (list != null && list.size() > 0) {
                Map<String, Object> map = null;
                for (ExtFaceGroupDetail record : list) {
                    byte[] arr = this.imageToFdfsService.downloadImage(record.getPersonImage());
                    map = new HashMap<>();
                    map.put("人员姓名", record.getPersonName());
                    map.put("电话号码", record.getPersonPhone());
                    map.put("证件号码", record.getPersonPaperNumber());
                    map.put("报警等级", record.getLevelName());
                    map.put("布控原因", record.getReason());
                    map.put("撤控时间", record.getWithdrawTimeStr());
                    map.put("布控图片", arr);
                    map.put("操作人员", record.getCreateUser());
                    infoList.add(map);
                }
            }
            // 声明一个工作薄
            HSSFWorkbook workbook = ExportExcelUtil.createHSSFWorkbookInfo(titles, infoList, sheetName,new Short("6"),
                    new Short("7"));
            // 导出
            ExportExcel.writeExcelFile(request, response, workbook, titles, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述: 导入文件
     *
     * @auther: lixianhua
     * @date: 2020/5/13 16:03
     * @param:
     * @return:
     */
    @PostMapping("/importAction")
    public Map<String, Object> importFile(@RequestParam("file") MultipartFile file) {
        try {
            //检查文件
            String fileName = ExportExcel.checkFile(file);
            //获得Workbook工作薄对象
            Workbook workbook = ExportExcel.getWorkBook(file);
            //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
            List<ExtFaceGroupDetail> list = new ArrayList<ExtFaceGroupDetail>();
            if (workbook != null) {
                for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                    //获得当前sheet工作表
                    Sheet sheet = workbook.getSheetAt(sheetNum);
                    Row rowHead = sheet.getRow(0);
                    //获得当前行的列数
                    int lastCellNum = rowHead.getPhysicalNumberOfCells();
                    //判断表头是否正确(临时去掉)
//                    if (rowHead.getPhysicalNumberOfCells() != 9) {
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, "表头数量不对");
//                    }
                    // 文件中图片集合
                    Map<String, PictureData> map = null;
                    if (fileName.endsWith(CommonConstants.XLS)) {
                        map = ExportExcel.getPictures1((HSSFSheet) sheet);
                    } else if (fileName.endsWith(CommonConstants.XLSX)) {
                        map = ExportExcel.getPictures2((XSSFSheet) sheet);
                    }
                    if (null == map || map.size() == 0) {// 表格中没有图片
                        continue;
                    }
                    // 上传图片
//                    printImg(map);
                    if (sheet == null) {
                        continue;
                    }
                    //获得当前sheet的开始行
                    int firstRowNum = sheet.getFirstRowNum();
                    //获得当前sheet的结束行
                    int lastRowNum = sheet.getLastRowNum();
                    //循环除了第二行的所有行
                    for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                        //获得当前行
                        Row row = sheet.getRow(rowNum);
                        if (row == null) {
                            continue;
                        }
                        // 必填项未填写则跳过
                        if (StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(0))) || StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(1))) || StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(4))) || StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(5))) || StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(6))) || StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(8)))) {
                            continue;
                        }
                        //获得当前行的开始列
                        int firstCellNum = row.getFirstCellNum();
                        ExtFaceGroupDetail detail = new ExtFaceGroupDetail();
                        //循环当前行
                        for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                            Cell cell = row.getCell(cellNum);
                            String value = ExportExcel.getCellValue(cell);
//                            cells[cellNum] = ExportExcel.getCellValue(cell);
                            if (0 == cellNum) {
                                detail.setGroupName(value);
                            } else if (1 == cellNum) {
                                detail.setPersonName(value);
                            } else if (2 == cellNum) {
                                detail.setPersonPhone(value);
                            } else if (3 == cellNum) {
                                detail.setPersonPaperNumber(value);
                            } else if (4 == cellNum) {
                                detail.setLevelName(value);
                                if ("高".equals(value)) {
                                    detail.setLevel(Short.valueOf("1"));
                                } else if ("中".equals(value)) {
                                    detail.setLevel(Short.valueOf("2"));
                                } else if ("低".equals(value)) {
                                    detail.setLevel(Short.valueOf("3"));
                                }
                            } else if (5 == cellNum) {
                                detail.setReason(value);
                            } else if (6 == cellNum) {// 布控时间
                                String start = value.split("-")[0];
                                String end = value.split("-")[1];
                                detail.setStartTime(TimesUtil.stringToDate(start, TimesUtil.DATE_FORMAT_YMD));
                                detail.setEndTime(TimesUtil.stringToDate(end, TimesUtil.DATE_FORMAT_YMD));
                            } else if (8 == cellNum) {
                                if ("0".equals(value)) {// 非市局数据
                                    detail.setSource(false);
                                } else {// 市局数据
                                    detail.setSource(true);
                                }
                            }
                        }
//                        if (StringUtils.isBlank(detail.getGroupName()) || StringUtils.isBlank(detail.getPersonName()) || StringUtils.isBlank(detail.getLevelName()) || null == detail.getStartTime() || null == detail.getEndTime() || StringUtils.isBlank(detail.getReason())) {
//                            continue;
//                        }
                        FaceGroup group = this.faceGroupMapper.getRecordByName(detail.getGroupName());
                        // 布控库名称不存在则跳过
                        if (null == group) {
                            continue;
                        }
                        detail.setGroupCode(group.getCode());
                        if (StringUtils.isNotBlank(detail.getPersonPaperNumber())) {
                            // 存在证件号码且（证件号码+布控库名）存在则跳过，防止重复
                            FaceGroupDetailExample example = new FaceGroupDetailExample();
                            example.createCriteria().andGroupNameEqualTo(detail.getGroupName()).andPersonPaperNumberEqualTo(detail.getPersonPaperNumber());
                            List<FaceGroupDetail> detailList = this.faceGroupDetailMapper.selectByExample(example);
                            if (null != detailList && detailList.size() > 0) {
                                continue;
                            }
                        }
                        // 获取图片信息
                        PictureData picture = map.get(String.valueOf(rowNum));
                        if (null == picture) {
                            continue;
                        }
                        detail.setPicData(picture);
                        // 获取图片格式
                        String ext = picture.suggestFileExtension();
                        System.out.println(ext);
                        list.add(detail);
                    }
                }
                workbook.close();
            }
            if (null != list && list.size() > 0) {
                // 批量添加布控人员
                int num = this.personGroupService.insertBatchRecord(list);
            }
            return render();
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }

    //图片写出
    public void printImg(Map<String, PictureData> sheetList) {

        //for (Map<String, PictureData> map : sheetList) {
        Object key[] = sheetList.keySet().toArray();
        for (int i = 0; i < sheetList.size(); i++) {
            // 获取图片流
            PictureData pic = sheetList.get(key[i]);
            // 获取图片索引
            String picName = key[i].toString();
            // 获取图片格式
            String ext = pic.suggestFileExtension();

            byte[] data = pic.getData();
            Map<String, Object> map = this.imageToFdfsService.uploadImage(data);
            System.out.println(map.get("thumbFullPath"));
        }
        // }

    }
}
