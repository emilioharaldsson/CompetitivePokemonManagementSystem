package com.emilio.expenseapp.dao;

import com.emilio.expenseapp.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamDAO extends JpaRepository<Team, Long> {

}
