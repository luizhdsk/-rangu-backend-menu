package com.puc.campinas.rangubackendmenu.controller;

import com.puc.campinas.rangubackendmenu.domain.data.ClientTableRequest;
import com.puc.campinas.rangubackendmenu.domain.data.ClientTableResponse;
import com.puc.campinas.rangubackendmenu.service.ClientTableService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping()
  public ResponseEntity<ClientTableResponse> startTable(
      @RequestHeader @Valid String clientId,
      @RequestBody @Valid ClientTableRequest request) {
    //TODO verificar se o cliente existe
    var clientTable = clientTableService.startTable(clientId, request.getTableId());
    return ResponseEntity.status(HttpStatus.CREATED).body(clientTable.toClientTableResponse());
  }

  @GetMapping("/{tableId}")
  public ResponseEntity<ClientTableResponse> getTable(@PathVariable String tableId) {
    var clientTable = clientTableService.getClientTable(tableId);
    return ResponseEntity.status(HttpStatus.OK).body(clientTable.toClientTableResponse());
  }

}
