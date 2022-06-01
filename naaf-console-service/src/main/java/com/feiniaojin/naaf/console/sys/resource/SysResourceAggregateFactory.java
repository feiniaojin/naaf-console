package com.feiniaojin.naaf.console.sys.resource;

import com.feiniaojin.naaf.console.integration.id.IdGeneratorIntegration;
import com.feiniaojin.naaf.console.sys.dto.SysResourceCmd;
import com.feiniaojin.naaf.console.sys.dto.SysResourceCmdAssembler;
import com.feiniaojin.naaf.console.data.SysResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 */
@Component
public class SysResourceAggregateFactory {

    @Resource
    private SysResourceCmdAssembler cmdAssembler;

    @Resource
    private IdGeneratorIntegration idGeneratorIntegration;

    /**
     * 根据cmd对象创建新的实体
     *
     * @param cmd
     * @return
     */
    public SysResourceAggregate newFromCmd(SysResourceCmd cmd) {
        //根据cmd组装实体
        SysResource mapToEntity = cmdAssembler.mapToEntity(cmd);
        mapToEntity.setResourceId(String.valueOf(idGeneratorIntegration.getStringUid()));
        SysResourceAggregate aggregate = new SysResourceAggregate();
        aggregate.setEntity(mapToEntity);
        return aggregate;
    }

    /**
     * 从实体创建聚合
     *
     * @param newEntity
     * @return
     */
    public SysResourceAggregate fromEntity(SysResource newEntity) {
        SysResourceAggregate aggregate = new SysResourceAggregate();
        aggregate.setEntity(newEntity);
        return aggregate;
    }
}