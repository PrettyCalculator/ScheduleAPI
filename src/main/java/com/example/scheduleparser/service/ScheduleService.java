package com.example.scheduleparser.service;

import com.example.scheduleparser.dto.ScheduleResponse;
import com.example.scheduleparser.exception.GroupNotFoundException;
import com.example.scheduleparser.exception.ScheduleParseException;
import com.example.scheduleparser.model.Lesson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final GoogleSheetsService googleSheetsService;

    // Индекс в строке таблицы: A=0 (пара), B=1 (пн), C=2 (вт), ... G=6 (сб)
    private static final String[] DAY_NAMES = {
            "", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"
    };

    // Время начала/конца пар (настрой под свой институт)
    private static final String[] TIME_SLOTS = {
            "", "08:00–09:30", "09:40–11:10", "11:20–12:50",
            "13:30–15:00", "15:10–16:40", "16:50–18:20", "18:30–20:00"
    };





    private Lesson parseLesson(int number, String time, String cell) {
        // Разбиваем по переводу строки внутри ячейки
        String[] lines = cell.split("\n");

        String rawSubject = lines[0].trim();
        String teacher = lines.length > 1 ? lines[1].trim() : "";
        String room     = lines.length > 2 ? lines[2].trim() : "";

        // Извлекаем тип из скобок: "Математика (Лекция)" → subject="Математика", type="Лекция"
        String type = "";
        String subject = rawSubject;
        int brStart = rawSubject.lastIndexOf('(');
        int brEnd   = rawSubject.lastIndexOf(')');
        if (brStart != -1 && brEnd > brStart) {
            type    = rawSubject.substring(brStart + 1, brEnd).trim();
            subject = rawSubject.substring(0, brStart).trim();
        }

        return Lesson.builder()
                .number(number)
                .time(time)
                .subject(subject)
                .build();
    }

    private int extractNumber(String value, int fallback) {
        try {
            return Integer.parseInt(value.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    private String resolveTime(String cellValue, int lessonNum) {
        // Если в ячейке уже написано время — используем его
        if (cellValue.contains(":")) return cellValue;
        // Иначе берём из предзаданного массива
        return (lessonNum > 0 && lessonNum < TIME_SLOTS.length) ? TIME_SLOTS[lessonNum] : "";
    }
}
