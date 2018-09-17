package com.airboard.api.controller.web.system;

import com.airboard.api.model.system.SysUser;
import com.airboard.api.service.system.SysUserService;
import com.airboard.client.dto.system.SysUserDTO;
import com.airboard.client.enums.SysUserSexEnum;
import com.airboard.client.enums.SysUserStatusEnum;
import com.airboard.client.enums.SysUserTypeEnum;
import com.airboard.core.annotation.CurrentUser;
import com.airboard.core.base.BaseController;
import com.airboard.core.base.BasePage;
import com.airboard.core.base.BaseResult;
import com.airboard.core.base.BaseUser;
import com.airboard.core.util.NumberUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "用户服务")
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    @ApiOperation(value = "分页列表")
    @GetMapping("/listSysUserPage")
    public BaseResult listSysUserPage(@CurrentUser BaseUser baseUser, SysUserDTO sysUserVO, BasePage basePage) {
        BaseResult result = new BaseResult();
        try {
            IPage<SysUserDTO> resultPage = sysUserService.listIPageByCondition(basePage, sysUserVO);
            result.setData(resultPage);
        } catch (Exception e) {
            log.error("listSysUserPage -=- {}", e.toString());
        }
        return result;
    }

    @ApiOperation(value = "查询明细")
    @GetMapping("/selectById")
    public BaseResult selectById(Long id, Model model) {
        BaseResult result = new BaseResult(true, "操作成功！");
        try {
            SysUserDTO sysUserVO = new SysUserDTO();
            if (NumberUtils.isNotEmpty(id)) {
                SysUser sysUser = sysUserService.selectById(id);
                BeanUtils.copyProperties(sysUser, sysUserVO);
            }
            result.setData(sysUserVO);
            model.addAttribute("sysUserVO", sysUserVO);
            model.addAttribute("sysUserTypeEnum", SysUserTypeEnum.values());
            model.addAttribute("sysUserSexEnum", SysUserSexEnum.values());
            model.addAttribute("sysUserStatusEnum", SysUserStatusEnum.values());
        } catch (Exception ex) {
            log.error("sysUserAdd -=- {}", ex.toString());
        }
        return result;
    }

    @ApiOperation(value = "新增/修改")
    @PostMapping("/addOrUpdate")
    public BaseResult addOrUpdate(SysUserDTO sysUserVO) {
        BaseResult result = new BaseResult(true, "操作成功！");
        try {
            sysUserService.insertOrUpdate(sysUserVO);
        } catch (Exception e) {
            log.error("addOrUpdate -=- {}", e.toString());
        }
        return result;
    }

    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public BaseResult delete(Long... id) {
        BaseResult result = new BaseResult(true, "删除成功！");
        try {
            sysUserService.deleteById(id);
        } catch (Exception e) {
            log.error("sysUser delete -=- {}", e.toString());
        }
        return result;
    }

}
