package com.feiniaojin.naaf.console.test;

import com.feiniaojin.naaf.console.app.AppStarter;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AppStarter.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public abstract class AbstractBaseTest {
}
