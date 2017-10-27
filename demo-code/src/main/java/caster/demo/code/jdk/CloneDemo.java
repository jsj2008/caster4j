package caster.demo.code.jdk;

import caster.demo.code.entity.UserSmall;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CloneDemo {

    /**
     * 通过序列化 来克隆对象
     */
    @Test
    public void test1() throws Exception {
        UserSmall user1 = new UserSmall();
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(user1);//写对象，序列化

        // 修改对象
        user1.setName("zs");
        user1.setPass("123");

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        UserSmall user2 = (UserSmall)in.readObject(); //读对象，反序列化

        System.out.println(user1.toString());
        System.out.println(user2.toString());
        System.out.println(user1 == user2);
    }

    /**
     * 通过 object 的 clone 方法
     */
    @Test
    public void test2() throws Exception {
        UserSmall user1 = new UserSmall();
        user1.setName("zs");
        user1.setPass("123");

        UserSmall clone = user1.clone();
        clone.setName("ls");

        System.out.println(user1.toString());
        System.out.println(clone.toString());
        System.out.println(user1 == clone);
    }
}