package com.feiniaojin.naaf.console.data;

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
 * 表名称：sys_account
 * 表注释：用户账号表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Data
@Table("sys_account")
@Generated("generator")
public class SysAccount implements Serializable {
    /**
     * 自增主键,业务不用
     */
    @Id
    private Long id;
    /**
     * 账号,业务使用
     */
    private String accountId;
    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * token
     */
    private String token;
    /**
     * 加密使用的盐
     */
    private String salt;
    /**
     * 账号状态[0-正常;1-已冻结]
     */
    private Integer status;
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
     * 乐观锁
     */
    @Version
    private Long version;
}
