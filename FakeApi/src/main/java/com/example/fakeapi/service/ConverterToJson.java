package com.example.fakeapi.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.text.CaseUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Component
public class ConverterToJson {
    public JSONArray convertCsvToJson(String csvFilePath) throws IOException, CsvException {
        JSONArray jsonArray = new JSONArray();

        try (Reader reader = new FileReader(csvFilePath)) {
            CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(0)
                    .withCSVParser(csvParser)
                    .build();

            List<String[]> csvRecords = csvReader.readAll();
            String[] headers = csvRecords.get(0);

            for (int i = 1; i < csvRecords.size(); i++) {
                String[] rowData = csvRecords.get(i);
                JSONObject jsonObject = new JSONObject();

                for (int j = 0; j < headers.length; j++) {
                    String header = headers[j];
                    header = CaseUtils.toCamelCase(header, false, ' ');
                    String value = rowData[j];
                    jsonObject.put(header, value);
                }

                jsonArray.put(jsonObject.toMap());
            }


        }

        return jsonArray;
    }
}
