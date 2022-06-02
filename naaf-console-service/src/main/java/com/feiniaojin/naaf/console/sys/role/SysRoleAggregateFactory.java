package com.feiniaojin.naaf.console.sys.role;

import com.feiniaojin.naaf.console.sys.resource.ResourceId;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 */
@Component
public class SysRoleAggregateFactory {

    public SysRoleAggregate newAggregate(String roleName, List<String> resourceIds) {
        SysRoleAggregate aggregate = new SysRoleAggregate();
        aggregate.setRoleName(roleName);
        //TODO 通过ID生成器生成
        aggregate.setRoleId(new RoleId("roleId"));
        if (!CollectionUtils.isEmpty(resourceIds)) {
            aggregate.setResourceIds(resourceIds.stream().map(resourceId -> new ResourceId(resourceId)).collect(Collectors.toList()));
        }
        return aggregate;
    }
}
