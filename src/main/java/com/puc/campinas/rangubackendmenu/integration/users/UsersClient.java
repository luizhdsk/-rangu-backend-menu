package com.puc.campinas.rangubackendmenu.integration.users;

import com.puc.campinas.rangubackendmenu.domain.data.PublicClientResponse;
import java.util.Collection;
import java.util.Set;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "users", url = "${rangu.users.url}")
public interface UsersClient {

  @RequestMapping(method = RequestMethod.GET)
  Collection<PublicClientResponse> getClients(@RequestParam("clients") Set<String> clientsId);

}
