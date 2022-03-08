package com.feiniaojin.naaf.console.sys.service;

import com.feiniaojin.naaf.console.adapter.id.IdGeneratorAdapter;
import com.feiniaojin.naaf.console.sys.dto.SysResourceCmd;
import com.feiniaojin.naaf.console.sys.dto.SysResourceCmdAssembler;
import com.feiniaojin.naaf.console.entity.SysResource;
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
    private IdGeneratorAdapter idGeneratorAdapter;

    /**
     * 根据cmd对象创建新的实体
     *
     * @param cmd
     * @return
     */
    public SysResourceAggregate newFromCmd(SysResourceCmd cmd) {
        //根据cmd组装实体
        SysResource mapToEntity = cmdAssembler.mapToEntity(cmd);
        mapToEntity.setResourceId(String.valueOf(idGeneratorAdapter.getUid()));
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
