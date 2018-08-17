package com.airboard.api.ms.system;

import com.airboard.api.service.system.SysUserService;
import com.airboard.client.dto.system.SysUserDTO;
import com.airboard.client.service.web.system.SysUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author <a href="mailto:wangshuo@ebnew.com">Wangshuo</a>
 * @Date 2018/8/17
 */
@RequestMapping("")
@RestController
public class SysUserMicroService implements SysUserClient {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysUserDTO> getByUserName(String userName) {
        return sysUserService.getByUserName(userName);
    }
}
