package com.feiniaojin.naaf.console.adapter.id;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:qinyujie@gingo.cn">Yujie</a>
 * @version 0.1
 */
@Component
public class IdGeneratorAdapter {

    @Resource
    private UidGenerator uidGenerator;

    public long getUid() {
        return uidGenerator.getUID();
    }
}
