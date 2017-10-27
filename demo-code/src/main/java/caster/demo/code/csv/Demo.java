package caster.demo.code.csv;

import kit4j.StrKit;
import kit4j.poi.CsvKit;
import caster.demo.code.pojo.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    @Test
    public void test1(){
        List<List<String>> data = StrKit.list(StrKit.list("11", "22", "33", "44"),
                StrKit.list("11", "22", "33", "44"),
                StrKit.list("11", "22", "33", "44"),
                StrKit.list("11", "22", "33", "44"));
        System.out.println(CsvKit.make(data));
    }

    @Test
    public void test2(){
        List<User> data = new ArrayList<>();
        data.add(new User("zhangsan", "123", "男"));
        data.add(new User("lisi", "123", "男"));
        data.add(new User("wangwu", "123", "男"));
        data.add(new User("zhaoliu", "123", "男"));
        data.add(new User("asdfas", "123", "男"));
//        System.out.println(CsvKit.format(data));
    }
}
