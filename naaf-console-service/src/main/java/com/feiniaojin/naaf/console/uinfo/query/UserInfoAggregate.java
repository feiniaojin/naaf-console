package com.feiniaojin.naaf.console.uinfo.query;

import com.feiniaojin.naaf.console.commons.NaafDateUtil;
import com.feiniaojin.naaf.console.entity.UserInfo;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * SysResourceModel的定位是承接业务逻辑，model中不允许调用数据库、缓存
 */
@Data
public class UserInfoAggregate {

    private UserInfo entity;

    /**
     * 执行初始化逻辑
     *
     * @return
     */
    public UserInfo create() {
        //初始化方法
        Date date = new Date();
        entity.setId(null);
        entity.setVersion(null);
        entity.setEvsStartTime(date);
        entity.setEvsMark("create");
        entity.setEvsEndTime(NaafDateUtil.END_DATE);
        entity.setEvsCurrent(1);
        return entity;
    }

    /**
     * 执行更新逻辑
     *
     * @param newEntity
     * @param nowTime
     * @return
     */
    public UserInfo update(UserInfo newEntity, Date nowTime) {
        //更新的具体逻辑
        String mobilePhone = newEntity.getMobilePhone();
        if (StringUtils.isNotBlank(mobilePhone)) {
            this.entity.setMobilePhone(mobilePhone);
        }

        String profileImgUrl = newEntity.getProfileImgUrl();
        if (StringUtils.isNotBlank(profileImgUrl)) {
            this.entity.setProfileImgUrl(profileImgUrl);
        }

        String userName = newEntity.getUserName();
        if (StringUtils.isNotBlank(userName)) {
            this.entity.setUserName(userName);
        }

        this.entity.setEvsEventId(newEntity.getEvsEventId());
        this.entity.setEvsMark(newEntity.getEvsMark());
        this.entity.setEvsEndTime(NaafDateUtil.END_DATE);
        this.entity.setEvsStartTime(nowTime);
        this.entity.setEvsCurrent(1);
        return entity;
    }

    public void closeSnapshot(Date nowTime) {
        this.entity.setEvsCurrent(0);
        this.entity.setEvsEndTime(nowTime);
    }
}
