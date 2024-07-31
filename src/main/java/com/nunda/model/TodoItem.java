package com.nunda.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import java.time.Duration; 
import java.lang.IllegalArgumentException;         

@Entity
@Table(name = "todo_items")
public class TodoItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    @Column(nullable = false)
    private String title;
    @Column
    private LocalDateTime startTime;
    @Column
    private LocalDateTime endTime;
    @Column
    private String category;
    @Column
    private boolean status = false; //false for not yet completed
    @CreationTimestamp
    private LocalDateTime createdDate;

    public TodoItem(){}
    public TodoItem(
        Long itemId, 
        String  title, 
        LocalDateTime s,
        LocalDateTime e,
        String category,
        boolean status
    ){
        this.itemId = itemId;
        this.title = title;
        this.startTime = s;
        this.endTime = e;
        this.category = category;
        this.status = status ;

    }

    //so duration will be got on the fly
    public Duration getDuration(){
        if (startTime != null && endTime != null){
            return Duration.between(startTime,endTime);  
        }
         return null;
 
     }

    @PrePersist
    @PreUpdate 
    public void validateTimes(){
       if (startTime != null && endTime != null && endTime.isBefore(startTime)){
         throw new IllegalArgumentException("Start time cannot be before endtime ");
       }

    }

    //getters and setters 
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "TodoItem [itemId=" + itemId + ", title=" + title + ", startTime=" + startTime + ", endTime=" + endTime
                + ", category=" + category + ", status=" + status + ", createdAt="
                + createdDate + "]";
    }




}