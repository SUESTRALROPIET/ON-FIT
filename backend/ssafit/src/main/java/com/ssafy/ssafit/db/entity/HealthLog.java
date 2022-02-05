package com.ssafy.ssafit.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class HealthLog extends BaseEntity{
    private int monthlyCal;

    private LocalDateTime monthlyTime;
}
