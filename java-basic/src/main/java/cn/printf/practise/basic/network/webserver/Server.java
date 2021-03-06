package cn.printf.practise.basic.network.webserver;

import cn.printf.practise.basic.network.webserver.dispatch.Dispatcher;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8080;

    private static ServerSocket serverSocket;
    private static boolean isRunning = true;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    /**
     * 启动方法
     */
    private void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务已启动成功，端口号:" + PORT);
            this.receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收客户端
     */
    public void receive() {
        while (isRunning) {
            Socket client = null;
            try {
                client = serverSocket.accept();
                System.out.println("开始接受请求:" + client.getInetAddress());
                new Thread(new Dispatcher(client)).start();
            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
            }
        }
    }

    /**
     * 停止方法
     */
    public void stop() {
        System.exit(-1);
    }
}
