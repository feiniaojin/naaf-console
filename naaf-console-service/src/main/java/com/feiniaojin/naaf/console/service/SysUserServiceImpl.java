package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.SysUserAssembler;
import com.feiniaojin.naaf.console.dto.SysUserCmd;
import com.feiniaojin.naaf.console.dto.SysUserQuery;
import com.feiniaojin.naaf.console.dto.SysUserView;
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

    private Gson gson = new Gson();

    @Override
    public void create(SysUserCmd cmd) {
        SysUser sysUser = SysUserAssembler.INSTANCE.mapToEntity(cmd);
        sysUserMapper.insert(sysUser);
    }

    @Override
    public void update(SysUserCmd cmd) {

    }

    @Override
    public SysUserView get(SysUserQuery query) {

        Long id = query.getId();

        SysUser sysUser = sysUserMapper.findOneById(id);

        if (sysUser == null) {
            log.error("查询不到数据,query=[{}]", gson.toJson(query));
            throw new SysUserExceptions.NotFoundException();
        }

        SysUserView sysUserView = SysUserAssembler.INSTANCE.mapToView(sysUser);

        return sysUserView;
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

        List<SysUserView> views = SysUserAssembler.INSTANCE.mapToViewList(sysUserList);

        pageBean.setList(views);

        return pageBean;
    }
}
