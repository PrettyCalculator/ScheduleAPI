package com.example.scheduleparser.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

@Data
@Builder
public class Lesson {
    private int number;       // номер пары (1, 2, 3...)
    private String time;      // "08:00 – 09:30"
    private String subject;   // название предмета
    private String teacher;   // преподаватель
    private String room;      // аудитория
    private String type;      // Лекция / Практика / Лаб.работа
}
