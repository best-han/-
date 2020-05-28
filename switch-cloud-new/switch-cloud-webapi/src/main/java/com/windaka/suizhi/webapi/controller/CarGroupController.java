package com.windaka.suizhi.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.webapi.dao.CarGroupMapper;
import com.windaka.suizhi.webapi.dao.auto.CarGroupDetailMapper;
import com.windaka.suizhi.webapi.model.CarGroup;
import com.windaka.suizhi.webapi.model.CarGroupDetail;
import com.windaka.suizhi.webapi.model.CarGroupDetailExample;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroup;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroupDetail;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroup;
import com.windaka.suizhi.webapi.service.CarGroupService;
import com.windaka.suizhi.webapi.util.ExportExcel;
import com.windaka.suizhi.webapi.util.ExportExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @ClassName CarGroupController
 * @Description 车辆布控控制类
 * @Author lixianhua
 * @Date 2020/4/8 9:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/carGroup")
public class CarGroupController extends BaseController {

    @Autowired
    private CarGroupService carGroupService;

    @Autowired
    private CarGroupMapper carGroupMapper;

    @Autowired
    private CarGroupDetailMapper carGroupDetailMapper;

    /**
     * 功能描述: 获取车辆布控库集合
     *
     * @auther: lixianhua
     * @date: 2020/4/8 10:09
     * @param:
     * @return:
     */
    @GetMapping("/groupList")
    public Map<String, Object> groupList(HttpServletRequest request, ExtCarGroup carGroup) {
        JSONObject object = new JSONObject();
        try {
            List<ExtCarGroup> list = this.carGroupService.selectGroupList(carGroup, request);
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
     * 功能描述: 添加车辆布控库
     *
     * @auther: lixianhua
     * @date: 2020/4/8 11:41
     * @param:
     * @return:
     */
    @PostMapping("/insertGroup")
    public Map<String, Object> insertGroup(@RequestBody CarGroup carGroup) {
        try {
            int num = this.carGroupService.insertRecord(carGroup);
            if (num == 0) {
                return failRender(ReturnConstants.CODE_FAILED, "添加失败");
            }
            return render();
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 根据ID更新车辆布控库
     *
     * @auther: lixianhua
     * @date: 2020/4/8 12:01
     * @param:
     * @return:
     */
    @PutMapping("/updateGroup")
    public Map<String, Object> updateGroup(@RequestBody CarGroup carGroup) {
        try {
            int num = this.carGroupService.updateGroup(carGroup);
            if (num == 0) {
                return failRender(ReturnConstants.CODE_FAILED, "修改失败");
            }
            return render();
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }

    /**
     * 功能描述: 根据ID删除车辆布控库
     *
     * @auther: lixianhua
     * @date: 2020/4/14 15:13
     * @param:
     * @return:
     */
    @DeleteMapping("/deleteGroup/{id}")
    public Map<String, Object> deleteGroup(@PathVariable String id) {
        try {
            int num = this.carGroupService.deleteGroupById(id);
            if (num == 0) {
                return failRender(ReturnConstants.CODE_FAILED, "删除失败");
            }
            return render();
        } catch (Exception e) {
            return failRender(e);
        }
    }

    /**
     * 功能描述: 布控车辆列表
     *
     * @auther: lixianhua
     * @date: 2020/4/9 13:57
     * @param:
     * @return:
     */
    @GetMapping("/carList")
    public Map<String, Object> personList(HttpServletRequest request, ExtCarGroupDetail groupDetail) {
        JSONObject object = new JSONObject();
        try {
            groupDetail.setPageFlag(true);
            List<ExtCarGroupDetail> list = this.carGroupService.selectCarList(groupDetail, request);
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
     * 功能描述: 添加车辆布控
     *
     * @auther: lixianhua
     * @date: 2020/4/14 16:08
     * @param:
     * @return:
     */
    @PostMapping("/insertCar")
    public Map<String, Object> insertPerson(@RequestBody ExtCarGroupDetail carGroupDetail) {
        try {
            int num = this.carGroupService.insertCar(carGroupDetail);
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
     * 功能描述: 根据ID物理删除车辆布控记录
     *
     * @auther: lixianhua
     * @date: 2020/4/17 16:48
     * @param:
     * @return:
     */
    @DeleteMapping("/deleteCar/{id}")
    public Map<String, Object> deletePerson(@PathVariable String id) {
        try {
            int num = this.carGroupService.deleteCar(id);
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
     * 功能描述: 更新布控车辆信息
     *
     * @auther: lixianhua
     * @date: 2020/4/16 10:59
     * @param:
     * @return:
     */
    @PutMapping("/updateCar")
    public Map<String, Object> updateCar(@RequestBody ExtCarGroupDetail detail) {
        try {
            int num = this.carGroupService.updateCarById(detail);
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
     * 功能描述: 导出车辆布控
     *
     * @auther: lixianhua
     * @date: 2020/5/14 8:38
     * @param:
     * @return:
     */
    @GetMapping(value = "/exportAction")
    public void downloadAction(HttpServletRequest request, HttpServletResponse response, ExtCarGroupDetail detail) {
        try {
            // 表格sheet名称
            String sheetName = "操作查询";
            String fileName = "车辆布控" + TimesUtil.getServerDateTime(8, new Date()) + ".xls";
            detail.setPageFlag(false);
            List<ExtCarGroupDetail> list = this.carGroupService.selectCarList(detail, request);
            String[] titles = new String[]{"所属车辆库", "车牌号码", "报警等级", "布控原因", "布控时间", "操作人员"};

            List<Map<String, Object>> infoList = new ArrayList<>();
            if (list != null && list.size() > 0) {
                Map<String, Object> map = null;
                for (ExtCarGroupDetail record : list) {
                    map = new HashMap<>();
                    map.put("所属车辆库", record.getGroupName());
                    map.put("车牌号码", record.getCarNum());
                    map.put("报警等级", record.getLevelName());
                    map.put("布控原因", record.getReason());
                    map.put("布控时间", record.getControlPeriods());
                    map.put("操作人员", record.getCreateUser());
                    infoList.add(map);
                }
            }
            // 声明一个工作薄
            HSSFWorkbook workbook = ExportExcelUtil.createHSSFWorkbookInfo(titles, infoList, sheetName,null,null);
            // 导出
            ExportExcel.writeExcelFile(request, response, workbook, titles, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 功能描述: 导出车辆撤控记录
     * @auther: lixianhua
     * @date: 2020/5/18 8:34
     * @param:
     * @return:
     */
    @GetMapping(value = "/exportWithdrawAction")
    public void downloadWithDrawAction(HttpServletRequest request, HttpServletResponse response, ExtCarGroupDetail detail) {
        try {
            // 表格sheet名称
            String sheetName = "操作查询";
            String fileName = "车辆撤控" + TimesUtil.getServerDateTime(8, new Date()) + ".xls";
            detail.setPageFlag(false);
            List<ExtCarGroupDetail> list = this.carGroupService.selectCarList(detail, request);
            String[] titles = new String[]{ "车牌号码", "报警等级", "布控原因", "撤控时间", "操作人员"};

            List<Map<String, Object>> infoList = new ArrayList<>();
            if (list != null && list.size() > 0) {
                Map<String, Object> map = null;
                for (ExtCarGroupDetail record : list) {
                    map = new HashMap<>();
                    map.put("车牌号码", record.getCarNum());
                    map.put("报警等级", record.getLevelName());
                    map.put("布控原因", record.getReason());
                    map.put("撤控时间", record.getWithdrawTimeStr());
                    map.put("操作人员", record.getCreateUser());
                    infoList.add(map);
                }
            }
            // 声明一个工作薄
            HSSFWorkbook workbook = ExportExcelUtil.createHSSFWorkbookInfo(titles, infoList, sheetName,null,null);
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
     * @date: 2020/5/14 16:35
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
            List<CarGroupDetail> list = new ArrayList<CarGroupDetail>();
            if (workbook != null) {
                for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                    //获得当前sheet工作表
                    Sheet sheet = workbook.getSheetAt(sheetNum);
                    Row rowHead = sheet.getRow(0);
                    //获得当前行的列数
                    int lastCellNum = rowHead.getPhysicalNumberOfCells();
                    // 表头数量(临时不做判断)
//                    int headNum = rowHead.getPhysicalNumberOfCells();
//                    if (headNum != 5) {
//                        throw new OssRenderException(ReturnConstants.CODE_FAILED, "表头数量不对");
//                    }
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
                        if (StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(0))) || StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(1))) || StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(2))) || StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(3))) || StringUtils.isBlank(ExportExcel.getCellValue(row.getCell(4)))) {
                            continue;
                        }
                        //获得当前行的开始列
                        int firstCellNum = row.getFirstCellNum();
                        CarGroupDetail detail = new CarGroupDetail();
                        //循环当前行
                        for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                            Cell cell = row.getCell(cellNum);
                            String value = ExportExcel.getCellValue(cell);
//                            cells[cellNum] = ExportExcel.getCellValue(cell);
                            if (0 == cellNum) {
                                detail.setGroupName(value);
                            } else if (1 == cellNum) {
                                detail.setCarNum(value);
                            } else if (2 == cellNum) {
                                detail.setLevelName(value);
                                if ("高".equals(value)) {
                                    detail.setLevel(Short.valueOf("1"));
                                } else if ("中".equals(value)) {
                                    detail.setLevel(Short.valueOf("2"));
                                } else if ("低".equals(value)) {
                                    detail.setLevel(Short.valueOf("3"));
                                }
                            } else if (3 == cellNum) {
                                detail.setReason(value);
                            } else if (4 == cellNum) {// 布控时间
                                String start = value.split("-")[0];
                                String end = value.split("-")[1];
                                detail.setStartTime(TimesUtil.stringToDate(start, TimesUtil.DATE_FORMAT_YMD));
                                detail.setEndTime(TimesUtil.stringToDate(end, TimesUtil.DATE_FORMAT_YMD));
                            }
                        }
                        // 必填项未填写则跳过
//                        if (StringUtils.isBlank(detail.getGroupName()) || StringUtils.isBlank(detail.getCarNum()) || StringUtils.isBlank(detail.getLevelName()) || null == detail.getStartTime() || null == detail.getEndTime() || StringUtils.isBlank(detail.getReason())) {
//                            continue;
//                        }
                        CarGroup group = this.carGroupMapper.getRecordByName(detail.getGroupName());
                        // 布控库名称不存在则跳过
                        if (null == group) {
                            continue;
                        }
                        detail.setGroupCode(group.getCode());
                        // 存在证件号码且（证件号码+布控库名）存在则跳过，防止重复
                        CarGroupDetailExample example = new CarGroupDetailExample();
                        example.createCriteria().andGroupNameEqualTo(detail.getGroupName()).andCarNumEqualTo(detail.getCarNum());
                        List<CarGroupDetail> detailList = this.carGroupDetailMapper.selectByExample(example);
                        if (null != detailList && detailList.size() > 0) {
                            continue;
                        }
                        list.add(detail);
                    }
                }
                workbook.close();
            }
            if (null != list && list.size() > 0) {
                // 批量添加车辆布控
                this.carGroupService.insertBatchRecord(list);
            }
            return render();
        } catch (Exception e) {
            e.printStackTrace();
            return failRender(e);
        }
    }
}
