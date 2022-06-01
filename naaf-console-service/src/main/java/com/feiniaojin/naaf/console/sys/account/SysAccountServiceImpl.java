package com.feiniaojin.naaf.console.sys.account;

import com.feiniaojin.naaf.console.sys.account.dto.LoginCmd;
import com.feiniaojin.naaf.console.sys.account.dto.LoginSuccessView;
import com.feiniaojin.naaf.console.sys.account.dto.LoginSuccessViewAssembler;
import com.feiniaojin.naaf.console.sys.account.dto.LogoutCmd;
import com.feiniaojin.naaf.console.sys.types.MobilePhone;
import com.feiniaojin.naaf.console.sys.types.Password;
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
        AccountAggregate accountAggregate = aggregateFactory.newAggregate(new MobilePhone(mobilePhone));
        aggregateRepository.save(accountAggregate);
    }

    @Override
    public LoginSuccessView login(LoginCmd cmd) {

        AccountAggregate aggregate = aggregateRepository.load(new MobilePhone(cmd.getMobilePhone()));
        if (aggregate == null) {
            throw new AccountExceptions.AccountNotExistException();
        }
        aggregate.login(new Password(cmd.getPassword()));
        aggregateRepository.save(aggregate);
        LoginSuccessView loginSuccessView = loginSuccessViewAssembler.mapToView(aggregate);
        //TODO 缓存
        return loginSuccessView;
    }

    @Override
    public void logout(LogoutCmd cmd) {
        AccountAggregate aggregate = aggregateRepository.load(new Token(cmd.getToken()));
        if (aggregate == null) {
            throw new AccountExceptions.AccountNotExistException();
        }
        aggregate.logout();
        aggregateRepository.save(aggregate);
        //TODO 清理缓存
    }
}
