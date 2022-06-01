package com.feiniaojin.naaf.console.sys.account;

import com.feiniaojin.naaf.console.data.SysAccount;
import com.feiniaojin.naaf.console.mapper.SysAccountMapper;
import com.feiniaojin.naaf.console.repository.SysAccountRepository;
import com.feiniaojin.naaf.console.sys.values.MobilePhone;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Component
public class AccountAggregateRepository {

    @Resource
    private SysAccountRepository sysAccountRepository;

    @Resource
    private SysAccountMapper sysAccountMapper;

    @Resource
    private AccountAggregateAssembler aggregateAssembler;

    @Transactional(rollbackFor = Exception.class)
    public void save(AccountAggregate aggregate) {

        sysAccountRepository.save(aggregateAssembler.mapToData(aggregate));
    }

    public AccountAggregate load(AccountId accountId) {

        SysAccount root = sysAccountMapper.findOneByBizId(accountId.getValue());
        if (root == null) {
            return null;
        }
        AccountAggregate aggregate = aggregateAssembler.mapToAggregate(root);

        return aggregate;
    }

    public AccountAggregate load(MobilePhone mobilePhone) {

        return null;
    }
}
