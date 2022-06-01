package com.feiniaojin.naaf.console.sys.resource;

import com.feiniaojin.naaf.console.data.SysResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class ResourceAggregateAssembler {

    public SysResource mapToData(ResourceAggregate aggregate) {

        SysResource sysResource = InnerMapper.INSTANCE.mapToData(aggregate);

        return sysResource;
    }

    public ResourceAggregate mapToAggregate(SysResource sysResource) {

        return null;
    }

    @Mapper(componentModel = "spring")
    public interface InnerMapper {
        ResourceAggregateAssembler.InnerMapper INSTANCE = Mappers.getMapper(ResourceAggregateAssembler.InnerMapper.class);

        SysResource mapToData(ResourceAggregate aggregate);
    }
}
