package com.example.scheduleparser.dto;

import com.example.scheduleparser.model.Lesson;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "Расписание группы на день")
public class ScheduleResponse {

    @Schema(example = "ИВТ-21")
    private String group;

    @Schema(example = "2024-09-02")
    private String date;

    @Schema(example = "Понедельник")
    private String dayOfWeek;

    private List<Lesson> lessons;
}
