package com.emilio.expenseapp.dao;

import com.emilio.expenseapp.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TypeDAO extends JpaRepository<Type, Long> {
    @Query("SELECT t FROM Type t where t.id = :id")
    public Type findTypeById(Integer id);
}
