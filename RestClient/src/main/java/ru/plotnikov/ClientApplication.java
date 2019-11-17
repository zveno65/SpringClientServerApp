package ru.plotnikov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.plotnikov.service.UserService;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
//        ApplicationContext ctx = SpringApplication.run(ClientApplication.class, args);
//        UserService userService = ctx.getBean(UserService.class);
//        userService.findAll().forEach(user -> System.out.println(user.getName()));
//        while (true) {
//
//        }
    }
}
