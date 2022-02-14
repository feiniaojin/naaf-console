package com.feiniaojin.naaf.console.service;

import com.feiniaojin.naaf.commons.data.PageBean;
import com.feiniaojin.naaf.console.dto.UserInfoAssembler;
import com.feiniaojin.naaf.console.dto.UserInfoCmd;
import com.feiniaojin.naaf.console.dto.UserInfoQuery;
import com.feiniaojin.naaf.console.dto.UserInfoView;
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

    private Gson gson = new Gson();

    @Override
    public void create(UserInfoCmd cmd) {
        UserInfo userInfo = UserInfoAssembler.INSTANCE.mapToEntity(cmd);
        userInfoMapper.insert(userInfo);
    }

    @Override
    public void update(UserInfoCmd cmd) {

    }

    @Override
    public UserInfoView get(UserInfoQuery query) {

        Long id = query.getId();

        UserInfo userInfo = userInfoMapper.findOneById(id);

        if (userInfo == null) {
            log.error("查询不到数据,query=[{}]", gson.toJson(query));
            throw new UserInfoExceptions.NotFoundException();
        }

        UserInfoView userInfoView = UserInfoAssembler.INSTANCE.mapToView(userInfo);

        return userInfoView;
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

        List<UserInfoView> views = UserInfoAssembler.INSTANCE.mapToViewList(userInfoList);

        pageBean.setList(views);

        return pageBean;
    }
}
