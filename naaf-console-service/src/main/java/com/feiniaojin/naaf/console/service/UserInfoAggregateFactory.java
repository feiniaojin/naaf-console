package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.adapter.id.IdGeneratorAdapter;
import com.feiniaojin.naaf.console.dto.UserInfoCmd;
import com.feiniaojin.naaf.console.dto.UserInfoCmdAssembler;
import com.feiniaojin.naaf.console.entity.UserInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 */
@Component
public class UserInfoAggregateFactory {

    @Resource
    private UserInfoCmdAssembler cmdAssembler;

    @Resource
    private IdGeneratorAdapter idGeneratorAdapter;

    /**
     * 根据cmd对象创建新的实体
     *
     * @param cmd
     * @return
     */
    public UserInfoAggregate newFromCmd(UserInfoCmd cmd) {
        //根据cmd组装实体
        UserInfo mapToEntity = cmdAssembler.mapToEntity(cmd);
        mapToEntity.setUid(String.valueOf(idGeneratorAdapter.getUid()));
        UserInfoAggregate aggregate = new UserInfoAggregate();
        aggregate.setEntity(mapToEntity);
        return aggregate;
    }

    /**
     * 从实体创建聚合
     *
     * @param newEntity
     * @return
     */
    public UserInfoAggregate fromEntity(UserInfo newEntity) {
        UserInfoAggregate aggregate = new UserInfoAggregate();
        aggregate.setEntity(newEntity);
        return aggregate;
    }
}
