package caster.demo.code.function.captcha;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class Generater {

    @Test
    public void test1() {
        StringCaptcha vCode = new StringCaptcha(160,40,5,150);
        try {
            String path="D:/"+new Date().getTime()+".png";
            System.out.println(vCode.getCode()+" >"+path);
            vCode.write(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
