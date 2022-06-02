package com.feiniaojin.naaf.console.sys.role;

import com.feiniaojin.naaf.console.data.SysRole;
import com.feiniaojin.naaf.console.data.SysRoleRelResource;
import com.feiniaojin.naaf.console.mapper.SysRoleRelResourceMapperEx;
import com.feiniaojin.naaf.console.repository.SysRoleRelResourceRepository;
import com.feiniaojin.naaf.console.repository.SysRoleRepository;
import com.feiniaojin.naaf.console.sys.resource.ResourceId;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SysRoleAggregateRepository {

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysRoleRelResourceRepository roleRelResourceRepository;

    @Resource
    private SysRoleRelResourceMapperEx relResourceMapperEx;

    @Resource
    private SysRoleAggregateAssembler roleAggregateAssembler;

    @Transactional(rollbackFor = Exception.class)
    public void save(SysRoleAggregate aggregate) {

        SysRole sysRole = roleAggregateAssembler.mapToData(aggregate);
        sysRoleRepository.save(sysRole);
        String roleId = sysRole.getRoleId();
        List<ResourceId> resourceIds = aggregate.getResourceIds();
        if (CollectionUtils.isNotEmpty(resourceIds)) {
            List<SysRoleRelResource> relResources = resourceIds.stream().map(resourceId -> {
                SysRoleRelResource roleRelResource = new SysRoleRelResource();
                roleRelResource.setRoleId(roleId);
                roleRelResource.setResourceId(resourceId.getValue());
                roleRelResource.setDeleted(0);
                return roleRelResource;
            }).collect(Collectors.toList());
            roleRelResourceRepository.saveAll(relResources);
        }
    }

    public SysRoleAggregate load(RoleId roleId) {
        return null;
    }
}
