package com.puc.campinas.rangubackendmenu.domain;

import com.puc.campinas.rangubackendmenu.domain.data.ClientTableResponse;
import com.puc.campinas.rangubackendmenu.repository.StringListConverter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

  private Date startDateTime;

  private Date endDateTime;

  @Convert(converter = StringListConverter.class)
  private List<String> tableMembers;

  @Convert(converter = StringListConverter.class)
  private List<String> orders;

  public ClientTableResponse toClientTableResponse() {
    return ClientTableResponse.builder()
        .id(id)
        .clientId(clientId)
        .number(number)
        .restaurantId(restaurantId)
        .startDateTime(startDateTime)
        .tableMembers(tableMembers)
        .orders(orders)
        .build();
  }
}
