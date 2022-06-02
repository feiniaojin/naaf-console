package com.feiniaojin.naaf.console.sys.user;

import com.feiniaojin.naaf.console.mapper.SysUserRelRoleMapperEx;
import com.feiniaojin.naaf.console.repository.SysUserRelRoleRepository;
import com.feiniaojin.naaf.console.repository.SysUserRepository;
import com.feiniaojin.naaf.console.sys.types.Uid;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class SysUserAggregateRepository {

    @Resource
    private SysUserRepository userRepository;

    @Resource
    private SysUserRelRoleRepository relRoleRepository;

    @Resource
    private SysUserRelRoleMapperEx relRoleMapperEx;

    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserAggregate aggregate) {

    }

    public SysUserAggregate load(Uid uid) {
        return null;
    }
}
