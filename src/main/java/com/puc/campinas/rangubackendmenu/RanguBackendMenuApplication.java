package com.puc.campinas.rangubackendmenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RanguBackendMenuApplication {

  public static void main(String[] args) {
    SpringApplication.run(RanguBackendMenuApplication.class, args);
  }

}
