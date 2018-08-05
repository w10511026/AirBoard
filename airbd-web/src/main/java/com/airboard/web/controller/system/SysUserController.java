package com.airboard.web.controller.system;


import com.airboard.core.base.BaseController;
import com.airboard.core.base.BasePage;
import com.airboard.core.base.BaseResult;
import com.airboard.core.model.system.SysUser;
import com.airboard.core.service.system.SysUserJPAService;
import com.airboard.core.service.system.SysUserService;
import com.airboard.core.vo.SysUserVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Slf4j
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    private final static String BASE_DIR = "system/";

    @Autowired
    SysUserService sysUserService;
    @Autowired
    public SysUserJPAService sysUserJPAService;

    /**
     * 跳转列表页面
     */
    @GetMapping("/index")
    public String index() {
        return BASE_DIR + "sysUserList";
    }

    /**
     * 分页查询数据
     */
    @ResponseBody
    @GetMapping("/listSysUserPage")
    public BaseResult listSysUserPage(BasePage basePage, SysUserVO sysUserVO) {
        BaseResult result = new BaseResult();
        try {
            IPage<SysUser> resultPage = sysUserService.listPageByCondition(basePage, sysUserVO);
            result.setData(resultPage);
        } catch (Exception e) {
            log.error("listSysUserPage -=- {}", e.toString());
        }
        return result;
    }

    /**
     * 跳转添加页面
     */
    @GetMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, Model model) {
        try {

        } catch (Exception ex) {
            log.error("sysUserAdd -=- {}", ex.toString());
        }
        return "sysUserAdd";
    }

    /**
     * 跳转修改页面
     */
    @GetMapping("/toUpdate")
    public String sysUserUpdate(HttpServletRequest request, Long id) {
        try {
            SysUser sysUser = sysUserJPAService.selectById(id);
            request.setAttribute("sysUser", sysUser);
        } catch (Exception ex) {
            log.error("sysUserUpdate -=- {}", ex.toString());
        }
        return "sysUserUpdate";
    }

    /**
     * 保存和修改公用的
     */
    @ResponseBody
    @PostMapping("/save")
    public int sysUserSave(SysUser sysUser) {
        int count = 0;
        try {
            sysUserJPAService.insertOrUpdate(sysUser);
        } catch (Exception e) {
            log.error("sysUserSave -=- {}", e.toString());
        }
        return count;
    }

    /**
     * 根据id删除对象
     */
    @ResponseBody
    @PostMapping("/delete")
    public int delete(Long... id) {
        int count = 0;
        try {
            count = sysUserJPAService.deleteById(id) ? 1 : 0;
        } catch (Exception e) {
            log.error("sysUserDelete -=- {}", e.toString());
        }
        return count;
    }

}