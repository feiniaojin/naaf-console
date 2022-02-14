package com.feiniaojin.naaf.console.test.resource;

import com.feiniaojin.naaf.console.dto.SysResourceCmd;
import com.feiniaojin.naaf.console.dto.SysResourceQuery;
import com.feiniaojin.naaf.console.dto.SysResourceView;
import com.feiniaojin.naaf.console.service.SysResourceService;
import com.feiniaojin.naaf.console.test.AbstractBaseTest;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

@Slf4j
public class SysResourceServiceTest extends AbstractBaseTest {

    @Resource
    private SysResourceService sysResourceService;

    Gson gson = new Gson();

    @Test
    public void test0() {
        SysResourceCmd cmd = new SysResourceCmd();
        cmd.setCreatedBy("test");
        cmd.setHttpMethod("POST");
        cmd.setPath("/sysResource/create");
        sysResourceService.create(cmd);
    }

    @Test
    public void test1() {
        SysResourceQuery query = new SysResourceQuery();
        query.setId(1L);
        SysResourceView sysResourceView = sysResourceService.get(query);
        log.info("view=[{}]", gson.toJson(sysResourceView));
    }
}
