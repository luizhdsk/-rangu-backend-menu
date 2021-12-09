package com.puc.campinas.rangubackendmenu.controller;

import com.puc.campinas.rangubackendmenu.domain.ClientTable;
import com.puc.campinas.rangubackendmenu.domain.data.ClientTableRequest;
import com.puc.campinas.rangubackendmenu.domain.data.ClientTableResponse;
import com.puc.campinas.rangubackendmenu.integration.users.UsersClient;
import com.puc.campinas.rangubackendmenu.service.ClientTableService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/rangu/v1/clientTables")
@Slf4j
public class ClientTableController {

  private ClientTableService clientTableService;
  private UsersClient usersClient;

  @PostMapping
  public ResponseEntity<ClientTableResponse> startTable(
      @RequestHeader @Valid String clientId,
      @RequestBody @Valid ClientTableRequest request) {
    var clientTable = clientTableService.startTable(clientId, request.getTableId());
    var members = usersClient.getClients(clientTable.getTableMembers());
    return ResponseEntity.status(HttpStatus.CREATED).body(clientTable.toClientTableResponse(members));
  }

  @GetMapping("/{tableId}")
  public ResponseEntity<ClientTable> getTable(@PathVariable String tableId) {
    return ResponseEntity.status(HttpStatus.OK).body(clientTableService.getClientTable(tableId));
  }

  @PatchMapping("/{tableId}")
  public ResponseEntity<ClientTableResponse> leaveTable(@PathVariable String tableId,
      @RequestHeader @Valid String clientId) {
    var clientTable = clientTableService.leaveTable(tableId, clientId);
    var members = usersClient.getClients(clientTable.getTableMembers());
    return ResponseEntity.ok(clientTable.toClientTableResponse(members));
  }

  @PatchMapping("/{tableId}/all")
  public ResponseEntity<ClientTableResponse> leaveAllTable(@PathVariable String tableId) {
    var clientTable = clientTableService.leaveAllTable(tableId);
    var members = usersClient.getClients(clientTable.getTableMembers());
    return ResponseEntity.ok(clientTable.toClientTableResponse(members));
  }

}
