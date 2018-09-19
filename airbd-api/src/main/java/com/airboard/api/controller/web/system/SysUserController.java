package com.airboard.api.controller.web.system;

import com.airboard.api.model.system.SysUser;
import com.airboard.api.service.system.SysUserService;
import com.airboard.client.dto.system.SysUserDTO;
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
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "用户服务")
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    SysUserService sysUserService;

    @ApiOperation(value = "分页列表")
    @GetMapping("/listPage")
    public BaseResult listPage(@CurrentUser BaseUser baseUser, SysUserDTO sysUserVO, BasePage basePage) {
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
    @GetMapping("/getById")
    public BaseResult getById(Long id, Model model) {
        BaseResult result = new BaseResult(true, "操作成功！");
        try {
            SysUserDTO sysUserVO = new SysUserDTO();
            if (NumberUtils.isNotEmpty(id)) {
                SysUser sysUser = sysUserService.getById(id);
                BeanUtils.copyProperties(sysUser, sysUserVO);
            }
            result.setData(sysUserVO);
        } catch (Exception ex) {
            log.error("sysUserAdd -=- {}", ex.toString());
        }
        return result;
    }

    @ApiOperation(value = "新增/修改")
    @PostMapping("/addOrUpdate")
    public BaseResult addOrUpdate(SysUserDTO sysUserDTO) {
        BaseResult result = new BaseResult(true, "操作成功！");
        try {
            sysUserService.insertOrUpdate(sysUserDTO);
        } catch (Exception e) {
            log.error("addOrUpdate -=- {}", e.toString());
        }
        return result;
    }

    @ApiOperation(value = "删除")
    @PostMapping("/deleteByIds")
    public BaseResult deleteByIds(@RequestBody Long... ids) {
        BaseResult result = new BaseResult("删除成功！");
        try {
            if (ArrayUtils.isEmpty(ids)) {
                return new BaseResult("id can't be null！");
            }
            for (Long id : ids) {
                sysUserService.removeById(id);
            }
        } catch (Exception e) {
            log.error("sysUser delete -=- {}", e.toString());
            return new BaseResult("删除失败！请重试！");
        }
        return result;
    }

}
