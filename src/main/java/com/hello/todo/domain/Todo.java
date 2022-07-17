package com.hello.todo.domain;

import com.hello.todo.dto.todo.TodoRegisterForm;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @EntityListeners(AuditingEntityListener.class)
@Entity @Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Range(min = 0, max = 1000000)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean completed;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false)
    private User user;

    public void completeTodo(LocalDateTime now){
        this.completedAt = now;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void update(TodoRegisterForm todoDTO){
        this.name = todoDTO.getName();
        this.completed = todoDTO.isCompleted();
    }
}
