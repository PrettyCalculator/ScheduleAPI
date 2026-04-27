import java.io.*;

public class CsvProcessor {

    public static void removeFirstColumn(String inputFile, String outputFile) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;

        while ((line = reader.readLine()) != null) {

            String[] columns = line.split(";");

            StringBuilder newLine = new StringBuilder();

            for (int i = 1; i < columns.length; i++) {
                newLine.append(columns[i]);
                if (i < columns.length - 1) {
                    newLine.append(";");
                }
            }

            writer.write(newLine.toString());
            writer.newLine();
        }

        reader.close();
        writer.close();
    }
}