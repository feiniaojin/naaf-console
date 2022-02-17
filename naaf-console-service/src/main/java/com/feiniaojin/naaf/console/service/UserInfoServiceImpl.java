package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.*;
import com.feiniaojin.naaf.console.entity.UserInfo;
import com.feiniaojin.naaf.console.exception.UserInfoExceptions;
import com.feiniaojin.naaf.console.mapper.UserInfoMapper;
import com.feiniaojin.naaf.console.mapper.UserInfoMapperEx;
import com.feiniaojin.naaf.console.repository.UserInfoRepository;
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
 * UserInfo类Service实现类
 * 表名称：u_user_info
 * 表注释：用户信息表
 */
@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserInfoMapperEx userInfoMapperEx;

    @Resource
    private UserInfoRepository userInfoRepository;

    @Resource
    private UserInfoCmdAssembler cmdAssembler;

    @Resource
    private UserInfoViewAssembler viewAssembler;

    @Resource
    private UserInfoAggregateFactory factory;

    private Gson gson = new Gson();

    @Override
    public void create(UserInfoCmd cmd) {
        //根据cmd组装实体
        UserInfoAggregate aggregate = factory.newFromCmd(cmd);
        //执行创建的初始化逻辑
        aggregate.create();
        log.info("UserInfo create:cmd=[{}],aggregate=[{}]", gson.toJson(cmd), gson.toJson(aggregate));
        userInfoRepository.save(aggregate.getEntity());
        //TODO 发布事件
    }

    @Override
    public void update(UserInfoCmd cmd) {
        //查询数据
        Optional<UserInfo> byId = userInfoRepository.findById(cmd.getId());
        if (!byId.isPresent()) {
            log.error("查询不到数据,cmd=[{}]", gson.toJson(cmd));
            throw new UserInfoExceptions.NotFoundException();
        }
        //cmd转换为实体，作为输入
        UserInfo input = cmdAssembler.mapToEntity(cmd);
        //获取数据库对应实体
        UserInfo userInfo = byId.get();
        //执行业务更新
        UserInfoAggregate aggregate = factory.fromEntity(userInfo);
        aggregate.update(input);
        //保存
        log.info("UserInfo update:cmd=[{}],userInfo=[{}]", gson.toJson(cmd), gson.toJson(userInfo));
        userInfoRepository.save(aggregate.getEntity());
        //TODO 发布事件
    }

    @Override
    public UserInfoView get(UserInfoQuery query) {
        //查询数据
        Long id = query.getId();
        UserInfo userInfo = userInfoMapper.findOneById(id);
        if (userInfo == null) {
            log.error("查询不到数据,query=[{}]", gson.toJson(query));
            throw new UserInfoExceptions.NotFoundException();
        }
        //拼接为view
        UserInfoView view = viewAssembler.mapToView(userInfo);
        return view;
    }

    @Override
    public PageBean<UserInfoView> pageList(UserInfoQuery query) {

        PageBean<UserInfoView> pageBean = new PageBean<>();

        //TODO 填充查询参数
        Map<String, Object> paramMap = new HashMap<>();

        int total = userInfoMapperEx.pageListTotal(paramMap);

        pageBean.setTotal(total);
        if (total == 0) {
            return pageBean;
        }

        //填充分页参数
        paramMap.put("offset", PageRequest.of(query.getPage(), query.getPageSize()).getOffset());
        paramMap.put("limit", query.getPageSize());

        List<UserInfo> userInfoList = userInfoMapperEx.pageList(paramMap);

        List<UserInfoView> views = viewAssembler.mapToViewList(userInfoList);

        pageBean.setList(views);

        return pageBean;
    }
}
