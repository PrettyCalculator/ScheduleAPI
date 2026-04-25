package com.example.scheduleparser.service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleSheetsService {

    private final Sheets sheetsService;

    @Value("${google.sheets.spreadsheet-id}")
    private String spreadsheetId;

    @Value("${google.sheets.api-key}")
    private String apiKey;

    @Value("${google.sheets.data-range}")
    private String dataRange;


    public List<List<Object>> getAllSchedule(String groupName) throws IOException {
        ValueRange response = sheetsService.spreadsheets().values()
                .get(spreadsheetId, dataRange)
                .setKey(apiKey)
                .execute();

        List<List<Object>> values = response.getValues();
        return values != null ? values : new ArrayList<>();
    }

    /**
     * Возвращает список всех листов (= список доступных групп).
     */
    public List<String> getAvailableGroups() throws IOException {
        return sheetsService.spreadsheets()
                .get(spreadsheetId)
                .setKey(apiKey)
                .execute()
                .getSheets()
                .stream()
                .map(sheet -> sheet.getProperties().getTitle())
                .toList();
    }
}
