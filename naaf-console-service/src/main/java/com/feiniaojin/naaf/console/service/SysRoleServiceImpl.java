package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.*;
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
import java.util.Optional;

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
    private SysRoleCmdAssembler cmdAssembler;

    @Resource
    private SysRoleViewAssembler viewAssembler;

    private Gson gson = new Gson();

    @Override
    public void create(SysRoleCmd cmd) {
        //根据cmd组装实体
         SysRole mapToEntity = cmdAssembler.mapToEntity(cmd);
        //执行创建的初始化逻辑
        SysRole sysRole = SysRoleAggregate.from(mapToEntity).create();
        //只有service才能调用下层的adapter，所以SysRoleAggregate.create执行完成之后，在此处填充业务id

        log.info("SysRole create:cmd=[{}],sysRole=[{}]", gson.toJson(cmd), gson.toJson(sysRole));
        sysRoleRepository.save(sysRole);
    }

    @Override
    public void update(SysRoleCmd cmd) {
        //查询数据
        Optional<SysRole> byId = sysRoleRepository.findById(cmd.getId());
        if (!byId.isPresent()) {
            log.error("查询不到数据,cmd=[{}]", gson.toJson(cmd));
            throw new SysRoleExceptions.NotFoundException();
        }
        //cmd转换为实体，作为输入
        SysRole input = cmdAssembler.mapToEntity(cmd);
        //获取数据库对应实体
        SysRole sysRole = byId.get();
        //执行业务更新
        SysRoleAggregate.from(sysRole).update(input);
        //保存
        log.info("SysRole update:cmd=[{}],sysRole=[{}]", gson.toJson(cmd), gson.toJson(sysRole));
        sysRoleRepository.save(sysRole);
    }

    @Override
    public SysRoleView get(SysRoleQuery query) {
       //查询数据
       Long id = query.getId();
       SysRole sysRole = sysRoleMapper.findOneById(id);
       if (sysRole == null) {
           log.error("查询不到数据,query=[{}]", gson.toJson(query));
           throw new SysRoleExceptions.NotFoundException();
       }
       //拼接为view
       SysRoleView view = viewAssembler.mapToView(sysRole);
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
