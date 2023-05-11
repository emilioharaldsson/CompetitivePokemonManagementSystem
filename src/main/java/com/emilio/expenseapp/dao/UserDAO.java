package com.emilio.expenseapp.dao;

import com.emilio.expenseapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u")
    public List<User> getAllUsers();

    @Query("Select u from User u where u.id = :id")
    public User  findUserById(int id);

}
