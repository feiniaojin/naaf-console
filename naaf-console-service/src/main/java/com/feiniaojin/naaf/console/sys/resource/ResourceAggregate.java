package com.feiniaojin.naaf.console.sys.resource;

import lombok.Data;

import java.util.Date;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
@Data
public class ResourceAggregate {
    /**
     * 自增主键,业务不用
     */
    private Long id;
    /**
     * 资源id
     */
    private ResourceId resourceId;
    /**
     * 父资源id
     */
    private ResourceId parentResourceId;
    /**
     * 资源路径
     */
    private String path;
    /**
     * java类中RequestMapping对应的方法
     */
    private String mappingMethod;
    /**
     * http请求的方法
     */
    private String httpMethod;
    /**
     * 资源类型,0请求路径,1菜单
     */
    private Integer type;
    /**
     * 是否展示,0不展示,1展示
     */
    private Integer visible;
    /**
     * 逻辑删除标记[0-正常;1-已删除]
     */
    private Integer deleted;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String modifiedBy;
    /**
     * 更新时间
     */
    private Date modifiedTime;
    /**
     * 乐观锁
     */
    private Long version;
}
