package com.feiniaojin.naaf.console.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名称：u_user_info
 * 表注释：用户信息表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Data
@Table("u_user_info")
@Generated("generator")
public class UserInfo implements Serializable {
    /**
     * 自增主键,业务不用
     */
    @Id
    private Long id;
    /**
     * 账号,业务使用
     */
    private String uid;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 邮箱
     */
    private String email;
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
     @CreatedDate
    private Date createdTime;
    /**
     * 更新人
     */
    private String modifiedBy;
    /**
     * 更新时间
     */
    @LastModifiedDate
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
    private String evsOprate;
    /**
     * 拉链表记录对应的事件id
     */
    private String evsEventId;
    /**
     * 乐观锁
     */
    @Version
    private Long version;
}
