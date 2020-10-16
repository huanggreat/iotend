package com.iotend.server;

import com.iotend.server.echo.EchoServer;
import com.iotend.utils.OsInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: Server Start
 * @author: huang
 * @create: 2020-10-14 16:23
 */
@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerApplication.class,args);

        /**
         * 开启Netty服务
         * */
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 1234;
        }
        new EchoServer(port).run();
    }
}
