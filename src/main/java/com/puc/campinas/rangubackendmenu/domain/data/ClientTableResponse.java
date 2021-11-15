package com.puc.campinas.rangubackendmenu.domain.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientTableResponse {

  private String id;

  private String clientId;

  private String number;

  private String restaurantId;

  private String startDateTime;

  private Collection<PublicClientResponse> tableMembers;

}
