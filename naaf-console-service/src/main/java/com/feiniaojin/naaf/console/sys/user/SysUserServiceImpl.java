package com.feiniaojin.naaf.console.sys.user;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.data.SysUser;
import com.feiniaojin.naaf.console.mapper.SysUserMapper;
import com.feiniaojin.naaf.console.mapper.SysUserMapperEx;
import com.feiniaojin.naaf.console.repository.SysUserRepository;
import com.feiniaojin.naaf.console.sys.types.Uid;
import com.feiniaojin.naaf.console.sys.user.dto.SysUserCmd;
import com.feiniaojin.naaf.console.sys.user.dto.SysUserQuery;
import com.feiniaojin.naaf.console.sys.user.dto.SysUserView;
import com.feiniaojin.naaf.console.sys.user.dto.SysUserViewAssembler;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    private SysUserViewAssembler viewAssembler;

    @Resource
    private SysUserAggregateFactory aggregateFactory;

    @Resource
    private SysUserAggregateRepository aggregateRepository;

    private Gson gson = new Gson();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysUserCmd cmd) {
        //根据cmd组装实体
        SysUserAggregate aggregate = aggregateFactory.newAggregate(cmd.getUserName(),
                cmd.getProfileImgUrl(),
                cmd.getRoleIdList());
        //执行创建的初始化逻辑
        if (log.isInfoEnabled()) {
            log.info("SysUser create:cmd=[{}],aggregate=[{}]", gson.toJson(cmd), gson.toJson(aggregate));
        }
        aggregateRepository.save(aggregate);
    }

    @Override
    public SysUserView get(SysUserQuery query) {
        //查询数据
        String uid = query.getUid();
        SysUser sysUser = sysUserMapperEx.findOneByBizId(uid);
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

    @Override
    public void assignRoles(SysUserCmd cmd) {

        SysUserAggregate aggregate = aggregateRepository.load(new Uid(cmd.getUid()));
        aggregate.assignRoles(cmd.getRoleIdList());
        if (log.isInfoEnabled()) {
            log.info("SysUser assignRoles:cmd=[{}],aggregate=[{}]", gson.toJson(cmd), gson.toJson(aggregate));
        }
        aggregateRepository.save(aggregate);
    }
}
