package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.SysRoleAssembler;
import com.feiniaojin.naaf.console.dto.SysRoleCmd;
import com.feiniaojin.naaf.console.dto.SysRoleQuery;
import com.feiniaojin.naaf.console.dto.SysRoleView;
import com.feiniaojin.naaf.console.entity.SysRole;
import com.feiniaojin.naaf.console.exception.SysRoleExceptions;
import com.feiniaojin.naaf.console.mapper.SysRoleMapper;
import com.feiniaojin.naaf.console.mapper.SysRoleMapperEx;
import com.feiniaojin.naaf.console.repository.SysRoleRepository;
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

    private Gson gson = new Gson();

    @Override
    public void create(SysRoleCmd cmd) {
        SysRole sysRole = SysRoleAssembler.INSTANCE.mapToEntity(cmd);
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public void update(SysRoleCmd cmd) {

    }

    @Override
    public SysRoleView get(SysRoleQuery query) {

        Long id = query.getId();

        SysRole sysRole = sysRoleMapper.findOneById(id);

        if (sysRole == null) {
            log.error("查询不到数据,query=[{}]", gson.toJson(query));
            throw new SysRoleExceptions.NotFoundException();
        }

        SysRoleView sysRoleView = SysRoleAssembler.INSTANCE.mapToView(sysRole);

        return sysRoleView;
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

        List<SysRoleView> views = SysRoleAssembler.INSTANCE.mapToViewList(sysRoleList);

        pageBean.setList(views);

        return pageBean;
    }
}
