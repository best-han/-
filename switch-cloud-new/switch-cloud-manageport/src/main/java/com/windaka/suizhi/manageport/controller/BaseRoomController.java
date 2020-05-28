package com.windaka.suizhi.manageport.controller;

import com.alibaba.fastjson.JSON;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.manageport.model.BaseRoom;
import com.windaka.suizhi.manageport.service.BaseRoomService;
import com.windaka.suizhi.manageport.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class BaseRoomController extends BaseController {
    @Autowired
    private BaseRoomService baseRoomService;
    @Autowired
    private BaseServiceImpl baseService;

    /*
    * 房间信息 新增 ygy
    * */
    @PostMapping("/house")
    public Map<String,Object> insertHouse(@RequestBody Map<String,Object> baseRoom){
        try {
            String innerParams= JSON.toJSONString(baseRoom);
            baseRoomService.insertRoom((List) baseRoom.get("houses"));
            baseService.senderMessage("POST","/house",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseRoomController.insertHouse,参数：{},异常信息{}]", baseRoom, e.getMessage());
            return failRender(e);
        }
    }


    /*
    * 房间信息 删除 ygy
    * */
    @DeleteMapping("/house/{code}")
    public Map<String, Object> deleteByCode(@PathVariable("code") String code) {
        try {
            String innerParams=code;
            baseRoomService.deleteRoomByCode(code);
            baseService.senderMessage("DELETE","/house",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseRoomController.deleteByCode,参数：{},异常信息{}]", code, e.getMessage());
            return failRender(e);
        }
    }

    /*
    * 房间信息 更新 ygy
    * */
    @PutMapping("/house")
    public Map<String, Object> updateByCode(@RequestBody BaseRoom baseRoom) {
        try {
            String innerParams=JSON.toJSONString(baseRoom);
            baseRoomService.updateRoomByCode(baseRoom);
            baseService.senderMessage("PUT","/house",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseRoomController.updateByCode,参数：{},异常信息{}]",baseRoom, e.getMessage());
            return failRender(e);
        }
    }

    /*
    * 房间信息 查询列表 ygy
    * */
    @GetMapping("/house")
    public Map<String, Object> selectHouseList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map = baseRoomService.selectRoomList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[BaseRoomController.selectHouseList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 楼栋信息 新增 ygy
     * */
    @PostMapping("/building")
    public Map<String,Object> insertBuilding(@RequestBody Map<String,Object> baseRoom){
        try {
            String innerParams=JSON.toJSONString(baseRoom);
            baseRoomService.insertBuilding((List)baseRoom.get("buildings"));
            baseService.senderMessage("POST","/building",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseRoomController.insertBuilding,参数：{},异常信息{}]", baseRoom, e.getMessage());
            return failRender(e);
        }
    }


    /*
     * 楼栋信息 删除 ygy
     * */
    @DeleteMapping("/building/{buildingCode}")
    public Map<String, Object> deleteBuildingByKey(@PathVariable("buildingCode") String buildingCode) {
        try {
            String innerParams=buildingCode;
            baseRoomService.deleteBuildingByKey(buildingCode);
            baseService.senderMessage("DELETE","/building",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseRoomController.deleteBuildingByKey,参数：{},异常信息{}]", buildingCode, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 楼栋信息 更新 ygy
     * */
    @PutMapping("/building")
    public Map<String, Object> updateBuildingByKey(@RequestBody BaseRoom baseRoom) {
        try {
            String innerParams=JSON.toJSONString(baseRoom);
            baseRoomService.updateBuildingByKey(baseRoom);
            baseService.senderMessage("PUT","/building",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseRoomController.updateBuildingByKey,参数：{},异常信息{}]",baseRoom, e.getMessage());
            return failRender(e);
        }
    }

    /*
     * 楼栋信息 查询列表 ygy
     * */
    @GetMapping("/building")
    public Map<String, Object> selectBuildingList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map = baseRoomService.selectBuildingList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[BaseRoomController.selectBuildingList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 查询单元 dee
     * @param params
     * @return
     */
    @GetMapping("/unit")
    public Map<String, Object> selectUnitList(@RequestParam Map<String, Object> params) {
        try {
            Map<String, Object> map = baseRoomService.selectUnitList(params);
            return render(map);
        } catch (Exception e) {
            log.info("[BaseRoomController.selectUnitList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 添加单元 dee
     * @param params
     * @return
     */
    @PostMapping("/unit")
    public Map<String,Object> insertUnit(@RequestBody Map<String,Object> params){
        try {
            String innerParams=JSON.toJSONString(params);
            baseRoomService.insertUnit((List) params.get("units"));
            baseService.senderMessage("POST","/unit",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseRoomController.insertUnit,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 删除单元 dee
     * @param unitCode
     * @return
     */
    @DeleteMapping("/unit/{unitCode}")
    public Map<String, Object> deleteUnit(@PathVariable("unitCode") String unitCode) {
        try {
            String innerParams=unitCode;
            baseRoomService.deleteUnit(unitCode);
            baseService.senderMessage("DELETE","/unit",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseRoomController.deleteUnit,参数：{},异常信息{}]", unitCode, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 修改单元 dee
     * @param baseRoom
     * @return
     */
    @PutMapping("/unit")
    public Map<String, Object> updateUnit(@RequestBody BaseRoom baseRoom) {
        try {
            String innerParams=JSON.toJSONString(baseRoom);
            baseRoomService.updateUnit(baseRoom);
            baseService.senderMessage("PUT","/unit",innerParams);
            return render();
        } catch (Exception e) {
            log.info("[BaseRoomController.updateUnit,参数：{},异常信息{}]",baseRoom, e.getMessage());
            return failRender(e);
        }
    }

}
