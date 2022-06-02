package com.feiniaojin.naaf.console.sys.role;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.data.SysRole;
import com.feiniaojin.naaf.console.mapper.SysRoleMapper;
import com.feiniaojin.naaf.console.mapper.SysRoleMapperEx;
import com.feiniaojin.naaf.console.repository.SysRoleRepository;
import com.feiniaojin.naaf.console.sys.role.dto.SysRoleCmd;
import com.feiniaojin.naaf.console.sys.role.dto.SysRoleQuery;
import com.feiniaojin.naaf.console.sys.role.dto.SysRoleView;
import com.feiniaojin.naaf.console.sys.role.dto.SysRoleViewAssembler;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysRole类Service实现类
 * 表名称：sys_role
 * 表注释：角色表
 */
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Resource
    private SysRoleMapperEx sysRoleMapperEx;

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private SysRoleAggregateFactory aggregateFactory;

    @Resource
    private SysRoleAggregateRepository aggregateRepository;

    @Resource
    private SysRoleViewAssembler viewAssembler;

    private Gson gson = new Gson();

    @Override
    public void create(SysRoleCmd cmd) {
        //根据cmd组装实体
        SysRoleAggregate aggregate = aggregateFactory.newAggregate(cmd.getRoleName(), cmd.getResourceIdList());
        //执行创建的初始化逻辑
        if (log.isInfoEnabled()) {
            log.info("SysRole create:cmd=[{}],aggregate=[{}]", gson.toJson(cmd), gson.toJson(aggregate));
        }
        aggregateRepository.save(aggregate);
    }

    @Override
    public SysRoleView get(SysRoleQuery query) {

        SysRoleAggregate aggregate = aggregateRepository.load(new RoleId(query.getRoleId()));
        if (aggregate == null) {
            throw new SysRoleExceptions.NotFoundException();
        }
        //拼接为view
        SysRoleView view = viewAssembler.mapToView(aggregate);
        return view;
    }

    @Override
    public PageBean<SysRoleView> pageList(SysRoleQuery query) {

        PageBean<SysRoleView> pageBean = new PageBean<>();

        //TODO 填充查询参数
        Map<String, Object> paramMap = new HashMap<>();

        int total = sysRoleMapperEx.pageListTotal(paramMap);

        pageBean.setTotal(total);
        if (total == 0) {
            return pageBean;
        }

        //填充分页参数
        paramMap.put("offset", PageRequest.of(query.getPage(), query.getPageSize()).getOffset());
        paramMap.put("limit", query.getPageSize());

        List<SysRole> sysRoleList = sysRoleMapperEx.pageList(paramMap);

        List<SysRoleView> views = viewAssembler.mapToViewList(sysRoleList);

        pageBean.setList(views);

        return pageBean;
    }
}
