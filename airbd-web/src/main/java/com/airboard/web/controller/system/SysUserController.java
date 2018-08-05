package com.airboard.web.controller.system;


import com.airboard.core.base.BaseController;
import com.airboard.core.base.BaseResult;
import com.airboard.core.base.DataSourceContextHolder;
import com.airboard.core.enums.DataSourceEnum;
import com.airboard.core.model.system.SysUser;
import com.airboard.core.service.system.SysUserService;
import com.airboard.core.service.system.SysUserJPAService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;

/**
 * @author Wangshuo
 * @since 2018-08-01
 */
@Controller
@Slf4j
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    private final static String BASE_DIR = "system/";

    @Autowired
    SysUserService sysUserService;
    @Autowired
    public SysUserJPAService sysUserJPAService;

    @ApiOperation(value = "查询学生")
    @GetMapping("/list")
    @ResponseBody
    public IPage list(){
        DataSourceContextHolder.setDataSource(DataSourceEnum.DB1.getValue());
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        IPage<SysUser> iPage = new Page<>(1, 10);
        IPage<SysUser> sysUserIPage = sysUserService.selectPage(iPage, wrapper);
        return sysUserIPage;
    }

    /**
     * 跳转列表页面
     */
    @GetMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        return BASE_DIR + "sysUserList";
    }

    /**
     * 分页查询数据
     */
    @ResponseBody
    @GetMapping("/listSysUserPage")
    public BaseResult listSysUserPage(Integer pageIndex, Integer pageSize, String sortField, String sortOrder) {
        BaseResult result = new BaseResult();
        try {
            IPage<SysUser> page = new Page<>(null != pageIndex ? pageIndex : 1, null != pageSize ? pageSize : 10);
            SysUser sysUser = new SysUser();
            sysUser.setUserName("laowang1");
            QueryWrapper<SysUser> wrapper = new QueryWrapper<>(sysUser);
            IPage<SysUser> sysUserIPage = sysUserService.selectPage(page, wrapper);
            result.setRows(sysUserIPage.getRecords());
            result.setTotal(sysUserIPage.getTotal().intValue());
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