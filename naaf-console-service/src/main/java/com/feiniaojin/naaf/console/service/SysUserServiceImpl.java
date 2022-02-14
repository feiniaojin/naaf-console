package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.*;
import com.feiniaojin.naaf.console.entity.SysUser;
import com.feiniaojin.naaf.console.exception.SysUserExceptions;
import com.feiniaojin.naaf.console.mapper.SysUserMapper;
import com.feiniaojin.naaf.console.mapper.SysUserMapperEx;
import com.feiniaojin.naaf.console.repository.SysUserRepository;
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
 * SysUser类Service实现类
 * 表名称：sys_user
 * 表注释：用户账号表
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserMapperEx sysUserMapperEx;

    @Resource
    private SysUserRepository sysUserRepository;

    @Resource
    private SysUserCmdAssembler cmdAssembler;

    @Resource
    private SysUserViewAssembler viewAssembler;

    private Gson gson = new Gson();

    @Override
    public void create(SysUserCmd cmd) {
        //根据cmd组装实体
         SysUser mapToEntity = cmdAssembler.mapToEntity(cmd);
        //执行创建的初始化逻辑
        SysUser sysUser = SysUserAggregate.from(mapToEntity).create();
        //只有service才能调用下层的adapter，所以SysUserAggregate.create执行完成之后，在此处填充业务id

        log.info("SysUser create:cmd=[{}],sysUser=[{}]", gson.toJson(cmd), gson.toJson(sysUser));
        sysUserRepository.save(sysUser);
    }

    @Override
    public void update(SysUserCmd cmd) {
        //查询数据
        Optional<SysUser> byId = sysUserRepository.findById(cmd.getId());
        if (!byId.isPresent()) {
            log.error("查询不到数据,cmd=[{}]", gson.toJson(cmd));
            throw new SysUserExceptions.NotFoundException();
        }
        //cmd转换为实体，作为输入
        SysUser input = cmdAssembler.mapToEntity(cmd);
        //获取数据库对应实体
        SysUser sysUser = byId.get();
        //执行业务更新
        SysUserAggregate.from(sysUser).update(input);
        //保存
        log.info("SysUser update:cmd=[{}],sysUser=[{}]", gson.toJson(cmd), gson.toJson(sysUser));
        sysUserRepository.save(sysUser);
    }

    @Override
    public SysUserView get(SysUserQuery query) {
       //查询数据
       Long id = query.getId();
       SysUser sysUser = sysUserMapper.findOneById(id);
       if (sysUser == null) {
           log.error("查询不到数据,query=[{}]", gson.toJson(query));
           throw new SysUserExceptions.NotFoundException();
       }
       //拼接为view
       SysUserView view = viewAssembler.mapToView(sysUser);
       return view;
    }

    @Override
    public PageBean<SysUserView> pageList(SysUserQuery query) {

        PageBean<SysUserView> pageBean = new PageBean<>();

        //TODO 填充查询参数
        Map<String, Object> paramMap = new HashMap<>();

        int total = sysUserMapperEx.pageListTotal(paramMap);

        pageBean.setTotal(total);
        if (total == 0) {
            return pageBean;
        }

        //填充分页参数
        paramMap.put("offset", PageRequest.of(query.getPage(), query.getPageSize()).getOffset());
        paramMap.put("limit", query.getPageSize());

        List<SysUser> sysUserList = sysUserMapperEx.pageList(paramMap);

        List<SysUserView> views = viewAssembler.mapToViewList(sysUserList);

        pageBean.setList(views);

        return pageBean;
    }
}
