package caster.demo.code.demo.jdk.timer;


import caster.demo.code.common.Time;
import caster.demo.code.common.ThreadUtils;

public class TimerTaskBean extends java.util.TimerTask {

    private String msg;

    public TimerTaskBean() {
        this.msg = "Hello, World!";
    }

    public TimerTaskBean(String msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        System.out.println("-----------------------Task[" + Thread.currentThread().getName() + "] Start-----------------------");
        for(int i=0;i<2;i++){
            System.out.println(msg);
            ThreadUtils.sleepQuietly(1000);
            System.out.println("已执行【"+(i+1)+"】秒钟，at: " + Time.on());
        }
        System.out.println("本次任务调度结束，at: " + Time.on());
        System.out.println("-----------------------Task[" + Thread.currentThread().getName() + "] End-----------------------");
    }
}
