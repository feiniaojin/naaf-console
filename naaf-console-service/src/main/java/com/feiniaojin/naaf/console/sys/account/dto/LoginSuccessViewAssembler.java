package com.feiniaojin.naaf.console.sys.account.dto;

import com.feiniaojin.naaf.console.sys.account.AccountAggregate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessViewAssembler {

    public LoginSuccessView mapToView(AccountAggregate aggregate) {
        LoginSuccessView view = InnerMapper.INSTANCE.mapToView(aggregate);
        return view;
    }

    @Mapper(componentModel = "spring")
    public interface InnerMapper {
        LoginSuccessViewAssembler.InnerMapper INSTANCE = Mappers.getMapper(LoginSuccessViewAssembler.InnerMapper.class);

        LoginSuccessView mapToView(AccountAggregate aggregate);
    }
}
