package com.feiniaojin.naaf.console.sys.account;

import com.feiniaojin.naaf.console.sys.account.dto.LoginSuccessView;
import com.feiniaojin.naaf.console.sys.account.dto.LoginSuccessViewAssembler;
import com.feiniaojin.naaf.console.sys.account.exceptions.AccountException;
import com.feiniaojin.naaf.console.sys.values.MobilePhone;
import com.feiniaojin.naaf.console.sys.values.Password;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SysAccount类Service实现类
 * 表名称：sys_account
 * 表注释：用户账号表
 */
@Service
@Slf4j
public class SysAccountServiceImpl implements SysAccountService {

    @Resource
    private AccountAggregateFactory aggregateFactory;

    @Resource
    private AccountAggregateRepository aggregateRepository;

    @Resource
    private LoginSuccessViewAssembler loginSuccessViewAssembler;

    @Override
    public void createAccount(String mobilePhone) {
        AccountAggregate accountAggregate = aggregateFactory.newAccountAggregate(new MobilePhone(mobilePhone));
        aggregateRepository.save(accountAggregate);
    }

    @Override
    public LoginSuccessView login(String mobilePhone, String password) {

        AccountAggregate accountAggregate = aggregateRepository.load(new MobilePhone(mobilePhone));
        if (accountAggregate == null) {
            throw new AccountException.AccountNotExistException();
        }
        accountAggregate.login(new Password(password));
        LoginSuccessView loginSuccessView = loginSuccessViewAssembler.mapToView(accountAggregate);
        return loginSuccessView;
    }
}
