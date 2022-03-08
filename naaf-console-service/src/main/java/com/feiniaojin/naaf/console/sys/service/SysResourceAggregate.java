package com.feiniaojin.naaf.console.sys.service;

import com.feiniaojin.naaf.console.entity.SysResource;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
@Data
public class SysResourceAggregate {

    private SysResource entity;

    public SysResource getEntity() {
        return entity;
    }

    /**
     * 执行初始化逻辑,初始化的知识只有聚合本身才清楚
     *
     * @return
     */
    public SysResourceAggregate create() {
        entity.setParentResourceId("0");
        entity.setType(0);
        entity.setVisible(1);
        return this;
    }


    /**
     * 执行更新逻辑
     *
     * @param newEntity
     * @return
     */
    public SysResourceAggregate update(SysResource newEntity) {
        if (StringUtils.isNotBlank(newEntity.getMappingMethod())) {
            entity.setHttpMethod(newEntity.getHttpMethod());
        }
        if (newEntity.getVisible() != null) {
            entity.setVisible(newEntity.getVisible());
        }
        return this;
    }
}
