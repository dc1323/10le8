package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.SystemSecurity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemSecurityMapper {

    void insert(SystemSecurity systemSecurity);

    List<SystemSecurity> getSystemSecurityList(SystemSecurity systemSecurity);

}
