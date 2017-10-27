package caster.demo.codetest.base;

import org.junit.Test;
import caster.demo.code.base.LogUtils;

public class LogUtilsTest {

    @Test
    public void test() {
        LogUtils.severe("Hello, World! ");
        LogUtils.warning("Hello, World! ");
        LogUtils.info("Hello, World! ");
        LogUtils.config("Hello, World! ");
        LogUtils.fine("Hello, World! ");
    }

}
