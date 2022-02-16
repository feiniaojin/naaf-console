package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.console.adapter.id.IdGeneratorAdapter;
import com.feiniaojin.naaf.console.dto.SysUserCmd;
import com.feiniaojin.naaf.console.dto.SysUserCmdAssembler;
import com.feiniaojin.naaf.console.entity.SysUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 */
@Component
public class SysUserAggregateFactory {

    @Resource
    private SysUserCmdAssembler cmdAssembler;

    @Resource
    private IdGeneratorAdapter idGeneratorAdapter;

    /**
     * 根据cmd对象创建新的实体
     *
     * @param cmd
     * @return
     */
    public SysUserAggregate newFromCmd(SysUserCmd cmd) {
        //根据cmd组装实体
        SysUser mapToEntity = cmdAssembler.mapToEntity(cmd);
        mapToEntity.setUid(String.valueOf(idGeneratorAdapter.getUid()));
        SysUserAggregate aggregate = new SysUserAggregate();
        aggregate.setEntity(mapToEntity);
        return aggregate;
    }

    /**
     * 从实体创建聚合
     *
     * @param newEntity
     * @return
     */
    public SysUserAggregate fromEntity(SysUser newEntity) {
        SysUserAggregate aggregate = new SysUserAggregate();
        aggregate.setEntity(newEntity);
        return aggregate;
    }
}
