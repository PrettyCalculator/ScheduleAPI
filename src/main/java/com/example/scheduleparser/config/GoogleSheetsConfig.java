package com.example.scheduleparser.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class GoogleSheetsConfig {

    @Bean
    public Sheets sheetsService() throws GeneralSecurityException, IOException {
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                httpRequest -> {}  // auth не нужна — используем API Key
        )
                .setApplicationName("ScheduleParser")
                .build();
    }
}
