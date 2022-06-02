package com.feiniaojin.naaf.console.sys.account;

import com.feiniaojin.naaf.console.data.SysAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class AccountAggregateAssembler {

    public SysAccount mapToData(AccountAggregate aggregate) {

        SysAccount sysAccount = InnerMapper.INSTANCE.mapToData(aggregate);

        return sysAccount;
    }

    public AccountAggregate mapToAggregate(SysAccount sysAccount) {

        return null;
    }

    @Mapper(componentModel = "spring")
    public interface InnerMapper {
        AccountAggregateAssembler.InnerMapper INSTANCE = Mappers.getMapper(AccountAggregateAssembler.InnerMapper.class);

        @Mapping(source = "aggregate.accountId.value",target = "accountId")
        @Mapping(source = "aggregate.mobilePhone.value",target = "mobilePhone")
        @Mapping(source = "aggregate.email.value",target = "email")
        @Mapping(source = "aggregate.password.value",target = "password")
        @Mapping(source = "aggregate.token.value",target = "token")
        SysAccount mapToData(AccountAggregate aggregate);
    }
}
