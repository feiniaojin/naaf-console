package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.repository.SysUserRelRoleRepository;
import com.feiniaojin.naaf.console.repository.SysUserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SysUserAggregateRepository {

    @Resource
    private SysUserRepository userRepository;

    @Resource
    private SysUserRelRoleRepository relRoleRepository;

    public void saveUpdate(SysUserAggregate aggregate) {

        String uid = aggregate.getEntity().getUid();

    }
}
