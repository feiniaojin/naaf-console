package com.feiniaojin.naaf.console.sys.role;

import com.feiniaojin.naaf.console.sys.resource.ResourceId;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
@Data
public class SysRoleAggregate {

    /**
     * 自增主键,业务不用
     */
    private Long id;
    /**
     * 角色id
     */
    private RoleId roleId;
    /**
     * 角色名称
     */
    private String roleName;
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

    private List<ResourceId> resourceIds;
}
