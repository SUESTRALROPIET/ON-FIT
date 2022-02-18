package com.ssafy.ssafit.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
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
public class User{
    @Id
    @GenericGenerator(name = "USER_GENERATOR", strategy = "uuid")
    private String userId;

    private String email;

    private String fullName;

    // mysql : timestamp? date
    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Trainer_id")
    private Trainer trainerId;

    @OneToMany(mappedBy = "userId")
    private List<ExerciseLog> exLogList = new ArrayList<>();
}



