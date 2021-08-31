package com.puc.campinas.rangubackendmenu.repository;

import com.puc.campinas.rangubackendmenu.domain.ClientTable;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTableRepository extends JpaRepository<ClientTable, String> {

}
