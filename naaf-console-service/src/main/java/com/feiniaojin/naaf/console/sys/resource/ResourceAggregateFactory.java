package com.feiniaojin.naaf.console.sys.resource;

import org.springframework.stereotype.Component;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 */
@Component
public class ResourceAggregateFactory {

    public ResourceAggregate newAggregate(ResourceId parentResourceId,
                                          String path,
                                          Integer type) {
        ResourceAggregate aggregate = new ResourceAggregate();
        aggregate.setResourceId(new ResourceId("1"));
        aggregate.setParentResourceId(parentResourceId);
        aggregate.setPath(path);
        aggregate.setType(type);
        return aggregate;
    }
}
