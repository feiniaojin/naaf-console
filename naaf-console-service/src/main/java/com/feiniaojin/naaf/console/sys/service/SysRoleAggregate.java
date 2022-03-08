package com.feiniaojin.naaf.console.sys.service;

import com.feiniaojin.naaf.console.entity.SysRole;
import com.feiniaojin.naaf.console.entity.SysRoleRelResource;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
@Data
public class SysRoleAggregate {

    private SysRole entity;

    private List<String> resourceIdList;

    /**
     * 角色关联的受限资源
     */
    private List<SysRoleRelResource> roleRelResourceList;

    /**
     * 执行初始化逻辑
     *
     * @return
     */
    public SysRole create() {
        //初始化方法
        entity.setDeleted(0);
        return entity;
    }

    /**
     * 执行更新逻辑
     *
     * @param newEntity
     * @return
     */
    public SysRole update(SysRole newEntity, List<String> resourceIdLis) {
        //更新的具体逻辑
        if (StringUtils.isNotBlank(newEntity.getRoleName())) {
            this.entity.setRoleName(newEntity.getRoleName());
        }
        this.resourceIdList = resourceIdLis;
        if (CollectionUtils.isEmpty(resourceIdLis)) {
            this.resourceIdList = new ArrayList<>();
        } else {
            List<SysRoleRelResource> list = new ArrayList<>();
            for (String resourceId : resourceIdLis) {
                SysRoleRelResource rel = new SysRoleRelResource();
                rel.setRoleId(this.entity.getRoleId());
                rel.setResourceId(resourceId);
                rel.setDeleted(0);
                list.add(rel);
            }
            this.roleRelResourceList = list;
        }
        return entity;
    }
}
