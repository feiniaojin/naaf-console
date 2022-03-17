package com.feiniaojin.naaf.console.uinfo.query;

import com.feiniaojin.naaf.console.integration.id.IdGeneratorIntegration;
import com.feiniaojin.naaf.console.uinfo.query.dto.UserInfoCmd;
import com.feiniaojin.naaf.console.uinfo.query.dto.UserInfoCmdAssembler;
import com.feiniaojin.naaf.console.entity.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 */
@Component
public class UserInfoAggregateFactory {

    @Resource
    private UserInfoCmdAssembler cmdAssembler;

    @Resource
    private IdGeneratorIntegration idGeneratorIntegration;

    /**
     * 根据cmd对象创建新的实体
     *
     * @param cmd
     * @return
     */
    public UserInfoAggregate newFromCmd(UserInfoCmd cmd) {
        //根据cmd组装实体
        UserInfo mapToEntity = cmdAssembler.mapToEntity(cmd);
        mapToEntity.setUid(String.valueOf(idGeneratorIntegration.getUid()));
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

    /**
     * 开启一个新的快照
     *
     * @param aggregate
     * @return
     */
    public UserInfoAggregate newSnapshot(UserInfoAggregate aggregate) {
        UserInfoAggregate cloneAggregate = new UserInfoAggregate();
        UserInfo entity = aggregate.getEntity();
        UserInfo newEntity = new UserInfo();
        BeanUtils.copyProperties(entity, newEntity);
        //新的快照id和version都为空
        newEntity.setId(null);
        newEntity.setVersion(null);
        cloneAggregate.setEntity(newEntity);
        return cloneAggregate;
    }
}
