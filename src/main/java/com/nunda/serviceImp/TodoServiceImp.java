package com.nunda.serviceImp;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.nunda.model.TodoItem;
import com.nunda.repository.TodoRepository;
import com.nunda.service.TodoService;

@Service
public class TodoServiceImp implements TodoService{
    @Autowired
    private TodoRepository todoRepository;
    

    @Override
    public List<TodoItem> getAllItems(){
       return todoRepository.findAll();
    }

    @Override
    public TodoItem getItemById(Long Id){
        return todoRepository.findById(Id).orElse(null);
    }

    @Override
    public TodoItem saveItem(TodoItem item){
        return todoRepository.save(item);

    }
    @Override
    public void deleteItem(Long Id){
        todoRepository.deleteById(Id);
    }
    @Override
    public List<TodoItem> getAllItemsByDate(){
        return todoRepository.findAllByOrderByCreatedDateAsc();
    }


}
