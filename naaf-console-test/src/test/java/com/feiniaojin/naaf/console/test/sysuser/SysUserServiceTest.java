package com.feiniaojin.naaf.console.test.sysuser;

import com.feiniaojin.naaf.console.sys.dto.SysUserCmd;
import com.feiniaojin.naaf.console.sys.user.SysUserService;
import com.feiniaojin.naaf.console.test.AbstractBaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class SysUserServiceTest extends AbstractBaseTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void test0() {
        SysUserCmd cmd = new SysUserCmd();
        cmd.setEmail("943868899@qq.com");
        cmd.setUserName("测试用户");
        cmd.setMobilePhone("15801423000");
        cmd.setPassword("12345678");
        cmd.setStatus(0);
        sysUserService.create(cmd);
    }

    @Test
    public void test1() {
        SysUserCmd cmd = new SysUserCmd();
        cmd.setUid("6233511986324750336");
        cmd.setEmail("99943868899@qq.com");
        cmd.setUserName("测试用户1");
        cmd.setMobilePhone("15801423000");
        cmd.setPassword("012345678");
        cmd.setStatus(0);
        sysUserService.update(cmd);
    }
}
