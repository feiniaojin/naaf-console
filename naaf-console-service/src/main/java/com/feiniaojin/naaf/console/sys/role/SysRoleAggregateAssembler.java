package com.feiniaojin.naaf.console.sys.role;

import com.feiniaojin.naaf.console.data.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class SysRoleAggregateAssembler {

    public SysRole mapToData(SysRoleAggregate aggregate) {

        SysRole sysRole = InnerMapper.INSTANCE.mapToData(aggregate);

        return sysRole;
    }

    public SysRoleAggregate mapToAggregate(SysRole data) {

        return null;
    }

    @Mapper(componentModel = "spring")
    public interface InnerMapper {
        SysRoleAggregateAssembler.InnerMapper INSTANCE = Mappers.getMapper(SysRoleAggregateAssembler.InnerMapper.class);

        @Mapping(source = "aggregate.roleId.value",target = "roleId")
        SysRole mapToData(SysRoleAggregate aggregate);
    }
}
