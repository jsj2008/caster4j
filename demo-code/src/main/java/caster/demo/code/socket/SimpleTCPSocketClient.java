package caster.demo.code.socket;

import java.io.*;
import java.net.Socket;

public class SimpleTCPSocketClient {

    // 客户端
    public static void main(String[] args) throws IOException {
        // 1、创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("localhost",10086);
        System.out.println("连接到服务器了...");
        // 2、获取输出流，向服务器端发送信息
        OutputStream out = socket.getOutputStream(); // 字节输出流
        byte[] data = "用户名：admin, 密码：123".getBytes();
        out.write(data);
        out.flush();
        socket.shutdownOutput();
        System.out.println("数据发送完毕...");

        // 3、获取输入流，并读取服务器端的响应信息
        InputStream in = socket.getInputStream();
        System.out.println("准备读取响应...");
        byte[] buffer = new byte[8192];
        for (int len; (len = in.read(buffer)) != -1;) {
            System.out.println("我是客户端，服务器说：" + new String(buffer, 0, len));
        }
        socket.shutdownInput();

        //4、关闭资源
        System.out.println("开始释放资源...");
        socket.close();
    }
}
