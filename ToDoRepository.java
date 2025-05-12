package com.example.demo.repository;

import com.example.demo.model.ToDo;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    // Lama - global (tidak aman untuk user biasa)
    List<ToDo> findByCompleted(boolean completed);

    // Baru - berdasarkan user login
    List<ToDo> findByUser(User user);
    List<ToDo> findByUserAndCompleted(User user, boolean completed);
}
