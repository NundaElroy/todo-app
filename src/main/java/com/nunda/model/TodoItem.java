package com.nunda.model;
import jakarta.persistence.*;
import java.time.*;


import org.hibernate.annotations.CreationTimestamp;

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
    private LocalTime startTime;
    @Column
    private LocalTime endTime;
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
        LocalTime s,
        LocalTime e,
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
    public LocalTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public LocalTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalTime endTime) {
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
public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    @Override
    public String toString() {
        return "TodoItem [itemId=" + itemId + ", title=" + title + ", startTime=" + startTime + ", endTime=" + endTime
                + ", category=" + category + ", status=" + status + ", createdAt="
                + createdDate + "]";
    }




}