package com.puc.campinas.rangubackendmenu.domain;

import com.puc.campinas.rangubackendmenu.repository.StringListConverter;
import java.util.Date;
import java.util.List;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "HISTORY_TABLE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryTable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @ManyToOne
  @JoinColumn(name = "restaurant_table_id")
  private RestaurantTable restaurantTable;

  private String clientId;

  private Date startDateTime;

  private Date endDateTime;

  @Convert(converter = StringListConverter.class)
  private List<String> tableMembers;

  @Convert(converter = StringListConverter.class)
  private List<String> orders;

}
