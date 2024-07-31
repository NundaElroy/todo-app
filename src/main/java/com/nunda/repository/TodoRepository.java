package com.nunda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nunda.model.TodoItem;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoItem,Long> {
    List<TodoItem> findAllByOrderByCreatedDateAsc();

}
