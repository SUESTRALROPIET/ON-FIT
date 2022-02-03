package com.ssafy.ssafit.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity(name="user")
public class User extends BaseEntity{
    private String user_email;

    private String full_name;

    private int food_log;

    // mysql : timestamp? date
    @CreatedDate
    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Trainer_id")
    private Trainer trainer_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HealthLog_id")
    private HealthLog healthLog_id;

    @OneToMany(mappedBy = "user_id")
    private List<ExerciseLog> ex_logList = new ArrayList<>();
}



