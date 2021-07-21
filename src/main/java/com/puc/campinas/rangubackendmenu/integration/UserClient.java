package com.puc.campinas.rangubackendmenu.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "userClient", url = "${user.url}")
public interface UserClient {

  @GetMapping("/api/rangu/v1/users/token/valid")
  void validateToken(@RequestHeader("Authorization") String token);


}
