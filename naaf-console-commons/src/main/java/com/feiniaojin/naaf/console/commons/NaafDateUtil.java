package com.feiniaojin.naaf.console.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 请输入类描述
 *
 * @author qinyujie3
 */
public class NaafDateUtil {
    public static final Date END_DATE;
    static {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            END_DATE = dateFormat.parse("9999-12-31");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
