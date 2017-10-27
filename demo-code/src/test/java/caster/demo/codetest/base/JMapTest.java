package caster.demo.codetest.base;

import org.junit.Test;
import caster.demo.code.base.JMap;

import java.util.HashMap;
import java.util.Map;

public class JMapTest {

    @Test
    public void test1() {
        Map<String, Integer> map = new HashMap<>();
        JMap.create(map);
    }

}
