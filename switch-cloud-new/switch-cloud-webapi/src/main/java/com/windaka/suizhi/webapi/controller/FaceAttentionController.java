package com.windaka.suizhi.webapi.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.utils.TimesUtil;
import com.windaka.suizhi.webapi.model.ext.ExtCarGroupDetail;
import com.windaka.suizhi.webapi.model.ext.ExtFaceAttentionDetail;
import com.windaka.suizhi.webapi.model.ext.ExtFaceGroup;
import com.windaka.suizhi.webapi.service.FaceAttentionService;
import com.windaka.suizhi.webapi.service.impl.ImageToFdfsServiceImpl;
import com.windaka.suizhi.webapi.util.ExportExcel;
import com.windaka.suizhi.webapi.util.ExportExcelUtil;
import com.windaka.suizhi.webapi.util.Paginator;
import javafx.scene.control.Pagination;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @ClassName FaceAttentionController
 * @Description 重点关注人员控制类
 * @Author lixianhua
 * @Date 2020/4/18 11:09
 * @Version 1.0
 */
@RestController
@RequestMapping("/faceAttention")
public class FaceAttentionController extends BaseController {

    @Autowired
    private FaceAttentionService faceAttentionService;

    @Autowired
    private ImageToFdfsServiceImpl imageToFdfsService;

    /**
     * 功能描述: 重点关注人员列表
     *
     * @auther: lixianhua
     * @date: 2020/4/9 13:57
     * @param:
     * @return:
     */
    @GetMapping("/personList")
    public Map<String, Object> personList(HttpServletRequest request, ExtFaceAttentionDetail attentionDetail) {
        JSONObject object = new JSONObject();
        try {
            attentionDetail.setPageFlag(true);
            List<ExtFaceAttentionDetail> list = this.faceAttentionService.selectPersonList(attentionDetail,request);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage",paginator.getPageNum());
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
     * 功能描述: 添加重点关注人员
     *
     * @auther: lixianhua
     * @date: 2020/4/14 16:10
     * @param:
     * @return:
     */
    @PostMapping("/insertPerson")
    public Map<String, Object> insertPerson(@RequestBody ExtFaceAttentionDetail attentionDetail) {
        try {
            int num = this.faceAttentionService.insertPerson(attentionDetail);
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
     * 功能描述: 批量添加重点关注人员
     * @auther: lixianhua
     * @date: 2020/4/27 10:28
     * @param:
     * @return:
     */
    @PostMapping("/insertBatchPerson")
    public Map<String, Object> insertBatchPerson(@RequestBody ExtFaceAttentionDetail attentionDetail) {
        try {
            int num = this.faceAttentionService.insertBatchPerson(attentionDetail);
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
     * 功能描述: 删除重点关注人员信息
     *
     * @auther: lixianhua
     * @date: 2020/4/17 16:51
     * @param:
     * @return:
     */
    @DeleteMapping("/deletePerson/{id}")
    public Map<String, Object> deletePerson(@PathVariable String id) {
        try {
            int num = this.faceAttentionService.deletePerson(id);
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
     * 功能描述: 更新重点关注人员信息
     *
     * @auther: lixianhua
     * @date: 2020/4/16 10:59
     * @param:
     * @return:
     */
    @PutMapping("/updatePerson")
    public Map<String, Object> updatePerson(@RequestBody ExtFaceAttentionDetail detail) {
        try {
            int num = this.faceAttentionService.updatePersonById(detail);
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
     * 功能描述: 导出重点关注人员
     * @auther: lixianhua
     * @date: 2020/5/14 9:27
     * @param:
     * @return:
     */
    @GetMapping(value = "/exportAction")
    public void downloadAction(HttpServletRequest request, HttpServletResponse response, ExtFaceAttentionDetail detail) {
        try {
            // 表格sheet名称
            String sheetName = "操作查询";
            String fileName ="重点关注"+ TimesUtil.getServerDateTime(8,new Date())+ ".xls";
            detail.setPageFlag(false);
            List<ExtFaceAttentionDetail> list = this.faceAttentionService.selectPersonList(detail,request);
            String[] titles = new String[]{"关注类型", "人员姓名",  "电话号码", "证件号码","布控状态", "加入时间","操作人员"};
            List<Map<String, Object>> infoList = new ArrayList<>();
            if (list != null && list.size() > 0) {
                Map<String, Object> map = null;
                for (ExtFaceAttentionDetail record : list) {
                    map = new HashMap<>();
                    map.put("关注类型", record.getAttentionName());
                    map.put("人员姓名", record.getPersonName());
                    map.put("电话号码", record.getPhone());
                    map.put("证件号码", record.getIdNo());
                    if(record.getStatus()){
                        map.put("布控状态", "未布控");
                    }else{
                        map.put("布控状态", "已布控");
                    }
                    map.put("加入时间", TimesUtil.getServerDateTime(6,record.getCreateTime()));
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
     * 功能描述: 导出重点关注布控人员
     * @auther: lixianhua
     * @date: 2020/5/14 9:27
     * @param:
     * @return:
     */
    @GetMapping(value = "/exportControllerAction")
    public void downloadControllerAction(HttpServletRequest request, HttpServletResponse response, ExtFaceAttentionDetail detail) {
        try {
            // 表格sheet名称
            String sheetName = "操作查询";
            String fileName ="重点关注布控"+ TimesUtil.getServerDateTime(8,new Date())+ ".xls";
            detail.setPageFlag(false);
            List<ExtFaceAttentionDetail> list = this.faceAttentionService.selectPersonList(detail,request);
            String[] titles = new String[]{"关注类型", "人员姓名",  "电话号码", "证件号码","报警等级", "布控原因", "布控时间", "人员图片", "操作人"};
            List<Map<String, Object>> infoList = new ArrayList<>();
            if (list != null && list.size() > 0) {
                Map<String, Object> map = null;
                for (ExtFaceAttentionDetail record : list) {
                    byte[] arr = this.imageToFdfsService.downloadImage(record.getImgUrl());
                    map = new HashMap<>();
                    map.put("关注类型", record.getAttentionName());
                    map.put("人员姓名", record.getPersonName());
                    map.put("电话号码", record.getPhone());
                    map.put("证件号码", record.getIdNo());
                    map.put("报警等级", record.getLevelName());
                    map.put("布控原因", record.getReason());
                    map.put("布控时间",record.getControlPeriods());
                    map.put("人员图片", arr);
                    map.put("操作人", record.getCreateUser());
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
}
