package com.nunda.repository;


import com.nunda.model.TodoItem;
import com.nunda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepo extends JpaRepository<User,Long> {

    User findByEmail(String email);
//    List<TodoItem>  findAllTodoItemsByEmail(String email);
    User findByUsername(String Username);

}
