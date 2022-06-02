package com.feiniaojin.naaf.console.test.role;

import com.feiniaojin.naaf.console.sys.role.dto.SysRoleCmd;
import com.feiniaojin.naaf.console.sys.role.SysRoleService;
import com.feiniaojin.naaf.console.test.AbstractBaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Arrays;

public class SysRoleServiceTest extends AbstractBaseTest {

    @Resource
    private SysRoleService sysRoleService;

    @Test
    public void test0() {
        SysRoleCmd cmd = new SysRoleCmd();
        cmd.setRoleName("测试角色");
        sysRoleService.create(cmd);
    }

    @Test
    public void test1() {
        SysRoleCmd cmd = new SysRoleCmd();
        cmd.setRoleName("测试角色");
        cmd.setResourceIdList(Arrays.asList("6233260301240967168", "6233251883105026048"));
        sysRoleService.create(cmd);
    }

    @Test
    public void test2() {
        SysRoleCmd cmd = new SysRoleCmd();
        cmd.setRoleName("测试修改角色");
        cmd.setRoleId(6233271708674138112L);
        cmd.setResourceIdList(Arrays.asList("6233260301240967168"));
        sysRoleService.update(cmd);
    }

    @Test
    public void test3() {
        SysRoleCmd cmd = new SysRoleCmd();
        cmd.setRoleName("测试修改角色2");
        cmd.setRoleId(6233271708674138112L);
        cmd.setResourceIdList(Arrays.asList("6233251883105026048"));
        sysRoleService.update(cmd);
    }

    @Test
    public void test4() {
        SysRoleCmd cmd = new SysRoleCmd();
        cmd.setRoleName("测试修改角色3");
        cmd.setRoleId(6233271708674138112L);
        sysRoleService.update(cmd);
    }

    @Test
    public void test5() {
        SysRoleCmd cmd = new SysRoleCmd();
        cmd.setRoleName("测试修改角色5");
        cmd.setRoleId(6233271708674138112L);
        cmd.setResourceIdList(Arrays.asList("6233263565416120320"));
        sysRoleService.update(cmd);
    }

    @Test
    public void test6() {
        SysRoleCmd cmd = new SysRoleCmd();
        cmd.setRoleName("测试修改角色6");
        cmd.setRoleId(6233271708674138112L);
        cmd.setResourceIdList(Arrays.asList("6233263565416120320","6233251883105026048","6233260301240967168"));
        sysRoleService.update(cmd);
    }
}
