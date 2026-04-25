package com.example.scheduleparser.controller;

import com.example.scheduleparser.dto.ScheduleResponse;
import com.example.scheduleparser.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
@Tag(name = "Schedule API", description = "Расписание занятий университета")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{group}/today")
    @Operation(summary = "Расписание на сегодня")
    public ResponseEntity<ScheduleResponse> getToday(
            @Parameter(description = "Название группы, например: ИВТ-21")
            @PathVariable String group) {

        return ResponseEntity.ok(
                null
        );
    }

    @GetMapping("/{group}")
    @Operation(summary = "Расписание на конкретную дату")
    public ResponseEntity<ScheduleResponse> getByDate(
            @Parameter(description = "Название группы, например: ИВТ-21")
            @PathVariable String group,
            @Parameter(description = "Дата в формате yyyy-MM-dd")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(null
        );
    }

    @GetMapping("/groups")
    @Operation(summary = "Список всех доступных групп")
    public ResponseEntity<List<String>> getGroups() {
        return ResponseEntity.ok(null);
    }
}
