package com.feiniaojin.naaf.console.sys.user;

import com.feiniaojin.naaf.console.sys.role.RoleId;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 */
@Component
public class SysUserAggregateFactory {

    public SysUserAggregate newAggregate(String userName,
                                         String profileImgUrl,
                                         List<String> roleIdList) {
        SysUserAggregate aggregate = new SysUserAggregate();
        aggregate.setUserName(userName);
        aggregate.setProfileImgUrl(profileImgUrl);
        aggregate.setDeleted(0);
        if (CollectionUtils.isNotEmpty(roleIdList)) {
            aggregate.setRoleIdList(roleIdList.stream().map(r -> new RoleId(r)).collect(Collectors.toList()));
        }
        return aggregate;
    }

}
