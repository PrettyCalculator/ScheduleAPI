package com.example.scheduleparser.model;

import com.example.scheduleparser.model.enums.DayOfWeek;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

@Data
@Builder
@ToString
public class Lesson {
    private int number;
    private String time;
    private DayOfWeek dayOfWeek;
    private String subject;
    private String group;
}
