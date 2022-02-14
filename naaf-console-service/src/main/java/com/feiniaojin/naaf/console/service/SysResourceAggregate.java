package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.entity.SysResource;
import org.apache.commons.lang.StringUtils;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
public class SysResourceAggregate {

    private SysResource sysResource;

    private SysResourceAggregate() {

    }

    private SysResourceAggregate(SysResource sysResource) {
        this();
        this.sysResource = sysResource;
    }

    public static SysResourceAggregate from(SysResource sysResource) {
        return new SysResourceAggregate(sysResource);
    }

    /**
     * 执行初始化逻辑
     *
     * @return
     */
    public SysResource create() {
        sysResource.setParentResourceId(0L);
        sysResource.setType(0);
        sysResource.setVisible(1);
        return sysResource;
    }

    /**
     * 执行更新逻辑
     *
     * @param newEntity
     * @return
     */
    public SysResource update(SysResource newEntity) {
        if (StringUtils.isNotBlank(newEntity.getMappingMethod())) {
            sysResource.setHttpMethod(newEntity.getHttpMethod());
        }
        if (newEntity.getVisible() != null) {
            sysResource.setVisible(newEntity.getVisible());
        }
        return sysResource;
    }
}
