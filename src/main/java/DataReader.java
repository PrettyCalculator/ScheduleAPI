import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import java.io.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataReader {
    public static List<List<Object>> getFromCsv() throws IOException {
        List<List<Object>> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Object[] splittedLine = line.split(";");
                List<Object> list = Arrays.stream(splittedLine).toList();
                result.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<List<Object>> getFromSheets() throws GeneralSecurityException, IOException {
        String api_key = "AIzaSyBtXCAztc16UFYGK0VVKmL9qLX2qz5WnrY";
        String spreadsheet_id = "13CqvyFsOa5Z5LYCfMCz4IyAnuTIcjYqI0ARgt8-5MpQ";
        String range = "B2:BF44";

        Sheets sheetsService = new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                httpRequest -> {
                }
        )
                .setApplicationName("ScheduleParser")
                .build();
        return sheetsService.spreadsheets().values().get(spreadsheet_id, range)
                .setKey(api_key).execute().getValues()  ;
    }

}
