package caster.server.socket.tcp.show.bio;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.*;
import java.net.Socket;

public class SocketHandleThread extends Thread {
    private Socket socket;

    public SocketHandleThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream in = null;
        try {
            in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder builder = new StringBuilder();
            builder.append(">>>>>>>>>>>>>>>>").append("\r\n");
            for (String line; StringUtils.isNotBlank((line = reader.readLine())); ) {
                builder.append(line).append("\r\n");
            }
            builder.append("<<<<<<<<<<<<<<<<").append("\r\n");
            System.out.println(builder);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(socket);
        }
    }

}
