package net.novaborn;

import net.novaborn.comm.SpringContextHolder;
import net.novaborn.pop.server.PopServer;
import net.novaborn.smtp.server.SmtpServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("net.novaborn.entity")
@MapperScan("net.novaborn.dao")//Mybatis的DAO所在包,这是必要的，因为SpringBootApplication，只负责扫描Service
@ComponentScan({"net.novaborn.controller"})
public class SmtpServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SmtpServerApplication.class, args);
        new SpringContextHolder().setApplicationContext(context);
        //启动服务器，需要开线程，否则会阻塞
        new Thread(SmtpServer::startupServer).start();
        new Thread(PopServer::startupServer).start();
    }
}
