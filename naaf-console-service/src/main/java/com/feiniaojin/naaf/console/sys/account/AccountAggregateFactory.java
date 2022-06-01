package com.feiniaojin.naaf.console.sys.account;

import com.feiniaojin.naaf.console.sys.values.MobilePhone;
import org.springframework.stereotype.Component;

@Component
public class AccountAggregateFactory {

    public AccountAggregate newAccountAggregate(MobilePhone mobilePhone) {
        AccountAggregate accountAggregate = new AccountAggregate();
        accountAggregate.setAccountId(new AccountId("accountId"));
        accountAggregate.setMobilePhone(mobilePhone);
        return accountAggregate;
    }
}
