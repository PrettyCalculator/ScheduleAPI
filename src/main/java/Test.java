import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;


public class Test {
    public static void main(String[] args) throws GeneralSecurityException, IOException {
        String api_key = "AIzaSyBtXCAztc16UFYGK0VVKmL9qLX2qz5WnrY";
        String spreadsheet_id = "13CqvyFsOa5Z5LYCfMCz4IyAnuTIcjYqI0ARgt8-5MpQ";
        String range = "A2:BF44";

        Sheets sheetsService = new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                httpRequest -> {
                }
        )
                .setApplicationName("ScheduleParser")
                .build();
        List<List<Object>> values = sheetsService.spreadsheets().values().get(spreadsheet_id, range)
                .setKey(api_key).execute().getValues();
        if (values == null || values.isEmpty()) {
            System.out.println("Нет данных");
            return;
        }
        try( BufferedWriter writer = new BufferedWriter(new FileWriter("data.csv"))) {
            for (List<Object> row : values) {
                for (int j = 0; j < row.size(); j++) {
                    writer.write(String.valueOf(row.get(j)));

                    // Добавляем разделитель только МЕЖДУ элементами
                    if (j < row.size() - 1) {
                        writer.write(";");
                    }
                }
                writer.newLine(); // Переход на новую строку после каждой записи
            }
        } catch (IOException e) {
        }
    }
}
