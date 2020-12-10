package com.ruoyi.games.service.impl;

import com.ruoyi.games.domain.SystemSecurity;
import com.ruoyi.games.mapper.SystemSecurityMapper;
import com.ruoyi.games.service.SystemSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuyang17
 * @description:
 * @date 2020/12/10 21:11
 */
@Service
public class SystemSecurityServiceImpl implements SystemSecurityService {

    @Autowired
    private SystemSecurityMapper systemSecurityMapper;

    @Override
    public List<SystemSecurity> getSystemSecurityList(SystemSecurity systemSecurity) {
        return systemSecurityMapper.getSystemSecurityList(systemSecurity);
    }
}
