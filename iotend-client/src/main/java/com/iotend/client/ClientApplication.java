package com.iotend.client;

import com.iotend.client.echo.ClientConn;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: 客户端启动程序
 * @author: huang
 * @create: 2020-10-15 13:46
 */
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class,args);
        ClientConn clientConn = new ClientConn();
        clientConn.NettyConnention();
    }
}
