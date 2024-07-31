package com.nunda.service;
import com.nunda.model.*;
import java.util.*;


public interface TodoService {
     List<TodoItem> getAllItems();
     TodoItem getItemById(Long Id);
     TodoItem saveItem(TodoItem item);
     void deleteItem(Long Id);
     List<TodoItem> getAllItemsByDate();
     

}
