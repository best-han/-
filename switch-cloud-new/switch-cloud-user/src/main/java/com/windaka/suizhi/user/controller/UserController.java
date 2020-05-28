package com.windaka.suizhi.user.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.windaka.suizhi.api.common.Page;
import com.windaka.suizhi.api.common.ReturnConstants;
import com.windaka.suizhi.api.oss.sys.HtMenu;
import com.windaka.suizhi.api.oss.sys.HtPermission;
import com.windaka.suizhi.api.user.AppUser;
import com.windaka.suizhi.api.user.LoginAppUser;
import com.windaka.suizhi.common.controller.BaseController;
import com.windaka.suizhi.common.exception.OssRenderException;
import com.windaka.suizhi.common.utils.AppUserUtil;
import com.windaka.suizhi.user.feign.OauthClient;
import com.windaka.suizhi.user.model.BaseCommunity;
import com.windaka.suizhi.user.model.ext.ExtHtUser;
import com.windaka.suizhi.user.service.AppUserService;
import com.windaka.suizhi.user.util.Paginator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户管理
 *
 * @author hjt
 * @version 1.0
 */
@Slf4j
@RestController
public class UserController extends BaseController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private OauthClient oauthClient;

    @Autowired
    private HttpServletRequest request;

    /**
     * 当前登录用户 hjt
     *
     * @return LoginAppUser
     */
    @GetMapping("/users/current")
    public Map<String, Object> getLoginAppUser() throws Exception {
        try {
            Map<String, Object> map = null;
            LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
            if (loginAppUser == null) {
                throw new Exception(ReturnConstants.STATUS_TOKEN_FAILED);
            } else {
                loginAppUser.setPassword("");
            }
            return render(loginAppUser);
        } catch (Exception e) {
            log.info("[UserController.getLoginAppUser,异常信息{}]", e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 获取用户基本信息 hjt
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/me")
    public Map<String, Object> getLoginAppUserBasicInfo() throws Exception {
        try {
            Map<String, Object> map = null;
            LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
            map = appUserService.queryByUserId(loginAppUser.getUserId());
            if (MapUtil.isEmpty(map)) {
                throw new Exception(ReturnConstants.STATUS_TOKEN_FAILED);
            }
            return render(map);
        } catch (Exception e) {
            log.info("[UserController.getLoginAppUser,异常信息{}]", e.getMessage());
            return failRender(e);
        }
    }

    /*@Autowired
    WebapiClient webapiClient;*/
    /**
     * 通过username查询用户信息
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/users-anon/internal", params = {"username", "password"})
    public Map<String, Object> queryByUsername(String username, String password) {

        try {
            //查询登录用户
            LoginAppUser loginAppUser = appUserService.queryByUsername(username, password);//用于获取token前查看是否有此用户
            if (loginAppUser != null) {
                //new Thread(()->webapiClient.preQueryCellByBuilding(loginAppUser)).start();
                return render(loginAppUser);
            } else {
                return failRender(ReturnConstants.CODE_LOGIN_FAILEED_USER_OR_PWD_BAD, ReturnConstants.MSG_LOGIN_FAILEED_USER_OR_PWD_BAD);
            }
        } catch (Exception e) {
            log.info("[UserController.queryByUsername,参数：{},{},异常信息{}]", username, password, e.getMessage());
            return failRender(e);
        }

    }

    /**
     * 用户查询-列表  hjt
     *
     * @param params
     */
//    @GetMapping("/users/list")
    public Map<String, Object> queryList(@RequestParam Map<String, Object> params) {
        try {
            Page page = appUserService.queryList(params);

            return render(page);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("[UserController.queryList,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 功能描述: 获取用户列表
     *
     * @auther: lixianhua
     * @date: 2020/4/22 15:05
     * @param:
     * @return:
     */
    @GetMapping("/users/list")
    public Map<String, Object> getUserList(HttpServletRequest request) {
        try {
            Map<String, Object> object = new HashMap<>();
            // 分页
            Paginator.pageHelper(request);
            ExtHtUser extHtUser = new ExtHtUser();
            extHtUser.setStartTime(request.getParameter("startTime"));
            extHtUser.setEndTime(request.getParameter("endTime"));
            extHtUser.setUsername(request.getParameter("username"));
           List<ExtHtUser> list  = appUserService.getUserList(extHtUser);
            if (null != list && list.size() > 0) {
                PageInfo paginator = new PageInfo(list);
                object.put("list", paginator.getList());
                object.put("total", paginator.getTotal());
                object.put("currentPage",paginator.getPageNum());
            } else {
                object.put("total", 0);
                object.put("list", new ArrayList<ExtHtUser>());
            }
            return render(object);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("[UserController.getUserList,参数：{},异常信息{}]", null, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 根据userId查询用户-内部调用
     *
     * @param userId
     * @return
     */
    @GetMapping("/users-anon/internal/{userId}")
    public Map<String, Object> queryByUserIdAnon(@PathVariable String userId) {
        try {
            Map map = appUserService.queryByUserId(userId);
            return render(map);
        } catch (Exception e) {
            log.info("[UserController.queryByUserIdAnon,参数：{},异常信息{}]", userId, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 根据userId查询用户
     *
     * @param userId
     * @return
     */
    @GetMapping("/users/{userId}")
    public Map<String, Object> queryByUserId(@PathVariable String userId) {
        try {
//            Map map = appUserService.queryByUserId(userId);
            ExtHtUser htUser = this.appUserService.getUserByUserId(userId);
            return render(htUser);
        } catch (Exception e) {
            log.info("[UserController.queryByUserId,参数：{},异常信息{}]", userId, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 添加用户  hjt 并绑定小区
     *
     * @param map
     */
//    @PostMapping("/users/add")
    public Map<String, Object> saveAppUser(@RequestBody Map<String, Object> map) {
        try {
            AppUser appUser = JSONObject.parseObject(JSONObject.toJSONString(map), AppUser.class);
            /*if(ObjectUtil.isNull(map.get("roleIds"))){
                throw new OssRenderException(ReturnConstants.CODE_FAILED,"请传入roleIds字段");
            }
            String str=(String) map.get("roleIds");
            String[] roleArray = str.split(",");
            if(roleArray.length!=1){
            	throw new OssRenderException(ReturnConstants.CODE_FAILED,"请只传入一个角色");
            }
            Set<String> roleIds = new HashSet<String>((List)Arrays.asList(roleArray));*/
            Set<String> roleIds = new HashSet<String>();
            String userId = appUserService.saveAppUser(appUser, roleIds);
            if (ObjectUtil.isNotNull(map.get("xqCodes"))) {
                String xqStr = (String) map.get("xqCodes");
                String[] xqArray = xqStr.split(",");
                Set<String> xqCodes = new HashSet<String>((List) Arrays.asList(xqArray));
                appUserService.saveXqToUser(userId, xqCodes);
            }
            return render();
        } catch (Exception e) {
            log.info("[UserController.saveAppUser,参数：{},异常信息{}]", map, e.getMessage());
            return failRender(e);
        }
    }

    @PostMapping("/users/add")
    public Map<String, Object> saveAppUser(@RequestBody ExtHtUser user) {
        try {
            int num = this.appUserService.insertUser(user);
            return render();
        } catch (Exception e) {
            log.info("[UserController.saveAppUser,参数：{},异常信息{}]", null, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 删除用户（伪删除）
     *
     * @param userId
     * @return
     */
    @DeleteMapping("/users/{userId}")
    public Map<String, Object> deleteAppUser(@PathVariable String userId) {
        try {
            appUserService.deleteAppUser(userId);
            return render();
        } catch (Exception e) {
            log.info("[UserController.deleteAppUser,参数：{},异常信息{}]", userId, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 添加用户-微服务内部调用
     *
     * @param appUser
     */
    @PostMapping("/users-anon/internal/register")
    public Map<String, Object> saveAppUsersAnon(@RequestBody AppUser appUser) {
        try {
            appUserService.saveAppUser(appUser, null);
            return render();
        } catch (Exception e) {
            log.info("[UserController.saveAppUsersAnon,参数：{},异常信息{}]", appUser, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 修改自己的个人信息  hjt
     */
    @PutMapping("/me")
    public Map<String, Object> updateMe(@RequestBody Map<String, Object> map) {
        try {
            LoginAppUser loginAppUser = appUserService.updateMe(map);
            if (loginAppUser != null) {
                Map<String, Object> updMap = new HashMap<>();
                updMap.put("cname", loginAppUser.getCname());
                updMap.put("phone", loginAppUser.getPhone());
                oauthClient.updateLoginInfo(updMap);
            }
            return render();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("[UserController.updateMe,参数：{},异常信息{}]", map, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 修改OSS用户
     */
    //@PreAuthorize("hasAuthority('back:user:update')")
    @PostMapping("/users/update")
    public Map<String, Object> updateAppUser(@RequestBody Map<String, Object> map) {
        System.out.println("update...");
        try {
            AppUser appUser = JSONObject.parseObject(JSONObject.toJSONString(map), AppUser.class);
            /*if(ObjectUtil.isNull(map.get("roleIds"))){
                throw new OssRenderException(ReturnConstants.CODE_FAILED,"请传入roleIds字段");
            }
            String str=(String) map.get("roleIds");
            String[] roleArray = str.split(",");
            if(roleArray.length!=1){
            	throw new OssRenderException(ReturnConstants.CODE_FAILED,"请只传入一个角色");
            }
            Set<String> roleIds = new HashSet<String>((List)Arrays.asList(roleArray));*/
            Set<String> roleIds = new HashSet<String>();
            String userId = appUserService.updateAppUser(appUser, roleIds);
            //String userId=appUser.getUserId();
            if (ObjectUtil.isNotNull(map.get("xqCodes"))) {
                String xqStr = (String) map.get("xqCodes");
                String[] xqArray = xqStr.split(",");
                Set<String> xqCodes = new HashSet<String>((List) Arrays.asList(xqArray));
                appUserService.saveXqToUser(userId, xqCodes);
            }
            return render();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("[UserController.updateAppUser,参数：{},异常信息{}]", map, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 修改OSS用户
     *
     * @param appUser
     */
    @PutMapping("/users-anon/internal/users")
    public Map<String, Object> updateAppUserAnon(@RequestBody AppUser appUser) {
        try {
            appUserService.updateAppUser(appUser, null);
            return render();
        } catch (Exception e) {
            log.info("[UserController.updateAppUserAnon,参数：{},异常信息{}]", appUser, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 修改密码 hjt
     */
    @PutMapping("/password")
    public Map<String, Object> updatePassword(@RequestBody Map<String, Object> map) {
        try {
            String oldPassword = (String) map.get("oldPassword");
            String newPassword = (String) map.get("newPassword");
            if (StringUtils.isBlank(oldPassword)) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "旧密码不能为空");
            }
            if (StringUtils.isBlank(newPassword)) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "新密码不能为空");
            }
            AppUser user = AppUserUtil.getLoginAppUser();
            appUserService.updatePassword(user.getUserId(), oldPassword, newPassword);
            return render();
        } catch (Exception e) {
            log.info("[UserController.updatePassword,参数：{},{},异常信息{}]", map, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 修改密码-内部调用
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    @PutMapping(value = "/users-anon/internal/password", params = {"oldPassword", "newPassword"})
    public Map<String, Object> updatePasswordAnon(String oldPassword, String newPassword) {
        try {
            if (StringUtils.isBlank(oldPassword)) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "旧密码不能为空");
            }
            if (StringUtils.isBlank(newPassword)) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "新密码不能为空");
            }
            AppUser user = AppUserUtil.getLoginAppUser();
            appUserService.updatePassword(user.getUserId(), oldPassword, newPassword);
            return render();
        } catch (Exception e) {
            log.info("[UserController.updatePasswordAnon,参数：{},{},异常信息{}]", oldPassword, newPassword, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * OSS用户-重置密码
     *
     * @param userId 用户id
     * @param map    新密码
     */
    //@PreAuthorize("hasAuthority('back:user:password')")
    @PutMapping(value = "/users/{userId}/password")
    public Map<String, Object> resetPassword(@PathVariable String userId, @RequestBody Map<String, String> map) {
        try {
            String newPassword = map.get("newPassword");
            if (StringUtils.isBlank(newPassword)) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "新密码不能为空");
            }
            appUserService.updatePassword(userId, null, newPassword);
            return render();
        } catch (Exception e) {
            log.info("[UserController.resetPassword,参数：{},{},异常信息{}]", userId, map, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * @Author: hjt
     * @Date: 2
     * @Description: OSS用户-重置密码-内部调用
     */
    @PutMapping(value = "/users-anon/internal/{userId}/password", params = {"newPassword"})
    public Map<String, Object> resetPasswordAnon(@PathVariable String userId, String newPassword) {
        try {
            if (StringUtils.isBlank(newPassword)) {
                throw new OssRenderException(ReturnConstants.CODE_FAILED, "新密码不能为空");
            }
            appUserService.updatePassword(userId, null, newPassword);
            return render();
        } catch (Exception e) {
            log.info("[UserController.resetPasswordAnon,参数：{},{},异常信息{}]", userId, newPassword, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 给用户分配角色
     *
     * @param userId  用户id
     * @param roleIds 角色ids
     */
    @PreAuthorize("hasAuthority('back:user:role:set')")
    @PostMapping("/users/{userId}/roles")
    public Map<String, Object> setRoleToUser(@PathVariable String userId, @RequestBody Set<String> roleIds) {
        try {
            appUserService.saveRoleToUser(userId, roleIds);
            return render();
        } catch (Exception e) {
            log.info("[UserController.setRoleToUser,参数：{},{},异常信息{}]", userId, roleIds, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 获取当前用户的功能菜单  hjt
     *
     * @return
     */
    @GetMapping("/users/userMenus")
    public Map<String, Object> queryUserMenus() {
        LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
        try {
            List<HtMenu> list = null;
            //超级管理员标识 true/false
            if (loginAppUser.getSysAdmin()) {
                list = appUserService.queryAdminMenus();
            } else {
                list = appUserService.queryUserRoleMenus(loginAppUser.getUserId());
            }
            Map<String, Object> listMap = new HashMap<>();
            listMap.put("list", list);
            return render(listMap);
        } catch (Exception e) {
            log.info("[UserController.queryUserMenus,异常信息{}]", e.getMessage());
            return failRender(e);
        }
    }

    /**
     * @Author: hjt
     * @Date: 2
     * @Description: 获取当前登录用户操作按钮
     */
    @GetMapping("/users/userPermissions")
    public Map<String, Object> queryUserPermissions() {
        try {
            Set<HtPermission> set = null;
            set = appUserService.queryLoginUserPermissions();
            Map<String, Object> listMap = new HashMap<>();
            listMap.put("list", set);
            return render(listMap);
        } catch (Exception e) {
            log.info("[UserController.queryUserPermissions,异常信息{}]", e.getMessage());
            e.printStackTrace();
            return failRender(e);
        }
    }

    /**
     * 按地图查询当前下属区域
     *
     * @return
     */
    @GetMapping("/area/{parentId}")
    public Map<String, Object> queryAreaInfo(@PathVariable String parentId) {
        try {
            List<Map<String, Object>> list = appUserService.queryAreaInfoByPid(parentId);
            //Map<String, Object> listMap = new HashMap<>();
            return render(list);
        } catch (Exception e) {
            log.info("[UserController.queryAreaInfo,异常信息{}]", e.getMessage());
            e.printStackTrace();
            return failRender(e);
        }
    }

    /**
     * 用户小区关系绑定
     *
     * @param userId
     * @param map
     * @return
     */
    @PostMapping("/users/{userId}/xq")
    public Map<String, Object> setXqToUser(@PathVariable String userId, @RequestBody Map<String, Object> map) {
        try {
            Set<String> xqCodes = new HashSet<String>((List) map.get("xqCodes"));
            appUserService.saveXqToUser(userId, xqCodes);
            return render();
        } catch (Exception e) {
            log.info("[UserController.setXqToUser,参数：{},{},异常信息{}]", userId, map, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 用户小区关系绑定(根据username):单独提供给安保端，出现一个安保人员在多个小区工作，小区管理员给其分配本小区的情况
     *
     * @param username
     * @param map
     * @return
     */
    @PostMapping("/users/{username}/userXq")
    public Map<String, Object> setXqToUserByUsername(@PathVariable String username, @RequestBody Map<String, Object> map) {
        try {
            Set<String> xqCodes = new HashSet<String>((List) map.get("xqCodes"));
            appUserService.saveXqToUserByUsername(username, xqCodes);
            return render();
        } catch (Exception e) {
            log.info("[UserController.setXqToUserByUsername,参数：{},{},异常信息{}]", username, map, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 用户小区关系解除绑定(根据username):单独提供给安保端，出现一个安保人员在多个小区工作，小区管理员给其分配本小区的情况
     *
     * @param username
     * @param map
     * @return
     */
    @DeleteMapping("/users/{username}/userXq")
    public Map<String, Object> deleteXqUserByUsername(@PathVariable String username, @RequestBody Map<String, Object> map) {
        try {
            Set<String> xqCodes = new HashSet<String>((List) map.get("xqCodes"));
            appUserService.deleteXqUserByUsername(username, xqCodes);
            return render();
        } catch (Exception e) {
            log.info("[UserController.deleteXqUserByUsername,参数：{},{},异常信息{}]", username, map, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 用户街道关系绑定
     *
     * @param params
     * @return
     */
    @PostMapping("/users/subdistrict")
    public Map<String, Object> saveUserSubdistrict(@RequestBody Map<String, Object> params) {
        try {
            appUserService.saveUserSubdistrict(params);
            return render();
        } catch (Exception e) {
            log.info("[UserController.saveUserSubdistrict,参数：{},异常信息{}]", params, e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 当前用户下区、街道以及小区列表 hjt
     *
     * @return
     */
    @GetMapping("/user/areaToXq")
    public Map<String, Object> queryAreaToXq() {
        try {
            List<Map<String, Object>> list = appUserService.queryAreaToXq();
            return render(list);
        } catch (Exception e) {
            log.info("[UserController.queryAreaToXq,异常信息{}]", e.getMessage());
            e.printStackTrace();
            return failRender(e);
        }
    }

    /**
     * 当前区域下的街道列表 hjt
     *
     * @return
     */
    @GetMapping("/subdistricts/{areaId}")
    public Map<String, Object> querySubdistrictByAreaId(@PathVariable String areaId) {
        try {
            List<Map<String, Object>> list = appUserService.querySubdistrictByAreaId(areaId);
            return render(list);
        } catch (Exception e) {
            log.info("[UserController.querySubdistrictByAreaId,异常信息{}]", e.getMessage());
            e.printStackTrace();
            return failRender(e);
        }
    }

    /**
     * 当前街道下的小区列表 hjt
     *
     * @return
     */
    @GetMapping("/xqs/{subdistrictId}")
    public Map<String, Object> queryXqBySubdistrictId(@PathVariable String subdistrictId) {
        try {
            List<Map<String, Object>> list = appUserService.queryXqBySubdistrictId(subdistrictId);
            return render(list);
        } catch (Exception e) {
            log.info("[UserController.queryXqBySubdistrictId,异常信息{}]", e.getMessage());
            e.printStackTrace();
            return failRender(e);
        }
    }
    /**
     * 功能描述: 获取街道下的小区
     * @auther: lixianhua
     * @date: 2020/4/22 16:56
     * @param:
     * @return:
     */
//    @GetMapping("/xqs/{subdistrictId}")
    public Map<String, Object> getXqBySub(@PathVariable String subdistrictId) {
        try {
            List<BaseCommunity> list = appUserService.getXqBySub(subdistrictId);
            return render(list);
        } catch (Exception e) {
            log.info("[UserController.queryXqBySubdistrictId,异常信息{}]", e.getMessage());
            e.printStackTrace();
            return failRender(e);
        }
    }

}
