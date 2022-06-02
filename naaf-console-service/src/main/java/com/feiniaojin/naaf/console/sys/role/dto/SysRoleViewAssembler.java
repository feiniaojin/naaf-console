package com.feiniaojin.naaf.console.sys.role.dto;

import com.feiniaojin.naaf.console.data.SysRole;
import com.feiniaojin.naaf.console.sys.role.SysRoleAggregate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysRoleViewAssembler {

    public SysRoleView mapToView(SysRoleAggregate aggregate) {

        SysRoleView roleView = InnerMapper.INSTANCE.mapToView(aggregate);

        return roleView;
    }

    public List<SysRoleView> mapToViewList(List<SysRole> roleList) {

        List<SysRoleView> roleViews = InnerMapper.INSTANCE.mapToViewList(roleList);

        return roleViews;
    }

    @Mapper(componentModel = "spring")
    public interface InnerMapper {
        SysRoleViewAssembler.InnerMapper INSTANCE = Mappers.getMapper(SysRoleViewAssembler.InnerMapper.class);

        @Mapping(source = "aggregate.roleId.value",target = "roleId")
        SysRoleView mapToView(SysRoleAggregate aggregate);

        List<SysRoleView> mapToViewList(List<SysRole> roleList);
    }
}
