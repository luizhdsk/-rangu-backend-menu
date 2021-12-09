package com.puc.campinas.rangubackendmenu.domain;

import com.puc.campinas.rangubackendmenu.domain.data.ClientTableResponse;
import com.puc.campinas.rangubackendmenu.domain.data.PublicClientResponse;
import com.puc.campinas.rangubackendmenu.repository.StringSetConverter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "CLIENT_TABLE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientTable implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  private String clientId;

  private String number;

  private String restaurantId;

  private LocalDateTime startDateTime;

  private LocalDateTime endDateTime;

  @Convert(converter = StringSetConverter.class)
  private Set<String> tableMembers;


  public ClientTableResponse toClientTableResponse(Collection<PublicClientResponse> members) {
    return ClientTableResponse.builder()
        .id(id)
        .clientId(clientId)
        .number(number)
        .restaurantId(restaurantId)
        .startDateTime(startDateTime.toString())
        .tableMembers(members)
        .build();
  }
}
