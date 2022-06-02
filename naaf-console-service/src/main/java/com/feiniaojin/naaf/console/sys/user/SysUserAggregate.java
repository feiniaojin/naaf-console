package com.feiniaojin.naaf.console.sys.user;

import com.feiniaojin.naaf.console.sys.role.RoleId;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
@Data
public class SysUserAggregate {

    /**
     * 自增主键,业务不用
     */
    private Long id;
    /**
     * 用户id,业务使用
     */
    private String uid;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 头像
     */
    private String profileImgUrl;
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
     * 拉链表记录生效时间
     */
    private Date evsStartTime;
    /**
     * 拉链表记录失效时间
     */
    private Date evsEndTime;
    /**
     * 拉链表记录生成操作标识,用于记录该记录生成的原因,insert/update/delete
     */
    private String evsMark;
    /**
     * 拉链表当前记录,默认为1,非当前则为0
     */
    private Integer evsCurrent;
    /**
     * 拉链表记录对应的事件id
     */
    private String evsEventId;
    /**
     * 乐观锁
     */
    private Long version;

    /**
     * 用户被授予的角色
     */
    List<RoleId> roleIdList;

    public void assignRoles(List<String> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) {
            return;
        }
        this.roleIdList = roleIdList.stream().map(r -> new RoleId(r)).collect(Collectors.toList());
    }
}
