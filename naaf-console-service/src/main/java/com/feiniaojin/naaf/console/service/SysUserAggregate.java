package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.entity.SysUser;
import com.feiniaojin.naaf.console.entity.SysUserRelRole;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
@Data
public class SysUserAggregate {

    /**
     * 用户实体
     */
    private SysUser entity;

    /**
     * 用户拥有的角色
     */
    private List<String> roleIdList;

    /**
     * 用户拥有的角色
     */
    private List<SysUserRelRole> userRelRoleList;

    /**
     * 执行初始化逻辑
     *
     * @return
     */
    public SysUser create() {
        //初始化方法
        //32位的盐
        String salt = RandomStringUtils.randomAlphabetic(32);
        this.entity.setSalt(salt);
        String pswEncode = DigestUtils.md5Hex(this.entity.getPassword() + salt);
        this.entity.setPassword(pswEncode);
        return this.entity;
    }

    /**
     * 执行更新逻辑
     *
     * @param newEntity
     * @return
     */
    public SysUser update(SysUser newEntity) {
        //更新的具体逻辑
        if (StringUtils.isNotBlank(newEntity.getPassword())) {
            String salt = RandomStringUtils.randomAlphabetic(32);
            this.entity.setSalt(salt);
            String pswEncode = DigestUtils.md5Hex(newEntity.getPassword() + salt);
            this.entity.setPassword(pswEncode);
        }
        if (StringUtils.isNotBlank(newEntity.getUserName())) {
            this.entity.setUserName(newEntity.getUserName());
        }
        if (StringUtils.isNotBlank(newEntity.getEmail())) {
            this.entity.setEmail(newEntity.getEmail());
        }
        if (StringUtils.isNotBlank(newEntity.getMobilePhone())) {
            this.entity.setMobilePhone(newEntity.getMobilePhone());
        }
        return entity;
    }

    public void assignRoles(SysUser input) {

    }
}
