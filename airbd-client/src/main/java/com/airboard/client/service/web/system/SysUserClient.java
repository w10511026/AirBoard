package com.airboard.client.service.web.system;

import com.airboard.client.dto.system.SysUserDTO;
import io.swagger.annotations.Api;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/8/17
 */
@Api(tags = "系统用户服务")
@FeignClient(name = "AIRBD-API", path = "api")
public interface SysUserClient {

    @GetMapping("/ms/sysUser/getByUserName/v1")
    List<SysUserDTO> getByUserName(@RequestParam(value = "userName") String userName);

}
