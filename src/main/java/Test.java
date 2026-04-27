import com.example.scheduleparser.model.Lesson;
import com.example.scheduleparser.model.enums.DayOfWeek;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        List<List<Object>> list = DataReader.getFromCsv();
        fetchSchedule(list);
    }

    public static List<Lesson> fetchSchedule(List<List<Object>> schedule) {
        List<Lesson> result = new ArrayList<>();
        Map<Integer, String> map = getGroupColumnMapping(schedule);
        int count = 0;
        for (int i = 1; i < schedule.size(); ++i) {
            String time = (String) schedule.get(i).get(0);
            count++;
            for (int j = 1; j < schedule.get(i).size(); ++j) {
                String subject= (String) schedule.get(i).get(j);
                if (subject != null && !subject.isEmpty()) {
                    String group = map.get(j);
                    DayOfWeek dayOfWeek = DayOfWeek.fromCode(count % 7);
                    result.add(Lesson.builder()
                            .group(group)
                            .dayOfWeek(dayOfWeek)
                            .time(time)
                            .subject(subject)
                            .number(count % 7)
                            .build());
                }
            }
        }
        return result;
    }

    public static Map<Integer, String> getGroupColumnMapping(List<List<Object>> schedule) {
        List<Object> row = schedule.get(0);
        Map<Integer, String> result = new HashMap<>();
        for (int i = 0; i < row.size(); ++i) {
            String value = ((String) row.get(i)).strip();
            if (!value.isEmpty()) {
                result.put(i, value);
            }
        }
        return result;
    }
}
