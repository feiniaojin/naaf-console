package com.feiniaojin.naaf.console.test.resource;

import com.feiniaojin.naaf.console.dto.SysResourceCmd;
import com.feiniaojin.naaf.console.service.SysResourceService;
import com.feiniaojin.naaf.console.test.AbstractBaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class SysResourceServiceTest extends AbstractBaseTest {

    @Resource
    private SysResourceService sysResourceService;

    @Test
    public void test0() {
        SysResourceCmd cmd = new SysResourceCmd();
        sysResourceService.create(cmd);
    }
}
