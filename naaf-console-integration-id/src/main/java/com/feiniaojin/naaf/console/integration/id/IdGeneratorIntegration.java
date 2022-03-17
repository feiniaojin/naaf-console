package com.feiniaojin.naaf.console.integration.id;

import com.baidu.fsg.uid.UidGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author <a href="mailto:qinyujie@gingo.cn">Yujie</a>
 * @version 0.1
 */
@Component
public class IdGeneratorIntegration {

    @Resource
    private UidGenerator uidGenerator;

    public long getLongUid() {
        return uidGenerator.getUID();
    }

    public String getStringUid() {
        return String.valueOf(uidGenerator.getUID());
    }
}
