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
 * 表名称：sys_resource
 * 表注释：受限资源表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/naaf-generator
 */
@Data
@Table("sys_resource")
@Generated("generator")
public class SysResource implements Serializable {
    /**
     * 自增主键,业务不用
     */
    @Id
    private Long id;
    /**
     * 资源id
     */
    private String resourceId;
    /**
     * 父资源id
     */
    private String parentResourceId;
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
