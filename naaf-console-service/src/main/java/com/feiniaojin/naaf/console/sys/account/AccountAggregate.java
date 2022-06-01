package com.feiniaojin.naaf.console.sys.account;

import com.feiniaojin.naaf.console.sys.account.exceptions.AccountException;
import com.feiniaojin.naaf.console.sys.values.Email;
import com.feiniaojin.naaf.console.sys.values.MobilePhone;
import com.feiniaojin.naaf.console.sys.values.Password;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class AccountAggregate {

    /**
     * 自增主键,业务不用,仅用于判断创建还是更新
     */
    private Long id;

    /**
     * 自增主键,业务不用
     */
    private AccountId accountId;

    /**
     * 手机号
     */
    private MobilePhone mobilePhone;
    /**
     * 邮箱
     */
    private Email email;
    /**
     * 密码
     */
    private Password password;
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

    public void login(Password password) {
        String value = password.getValue();
        if (value.equals(this.password.getValue())) {
            refreshToken();
            return;
        }
        throw new AccountException.PasswordIncorrectException();
    }

    private void refreshToken() {
        this.token = UUID.randomUUID().toString();
    }
}
