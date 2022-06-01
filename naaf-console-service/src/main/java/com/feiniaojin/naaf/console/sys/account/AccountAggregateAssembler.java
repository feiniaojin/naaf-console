package com.feiniaojin.naaf.console.sys.account;

import com.feiniaojin.naaf.console.data.SysAccount;
import org.mapstruct.Mapper;
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

        SysAccount mapToData(AccountAggregate aggregate);
    }
}
