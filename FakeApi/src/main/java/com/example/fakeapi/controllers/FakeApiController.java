package com.example.fakeapi.controllers;

import com.example.fakeapi.service.ConverterToJson;
import com.opencsv.exceptions.CsvException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.fakeapi.service.ConverterToJson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class FakeApiController {
    private final ConverterToJson csvToJsonConverter;

    @Autowired
    public FakeApiController(ConverterToJson csvToJsonConverter) {
        this.csvToJsonConverter = csvToJsonConverter;
    }

    @RequestMapping(value="/convert", method= RequestMethod.GET, produces = "application/json")
    public String convertCsvToJson() throws IOException, CsvException {
        String csvFilePath = "/home/ronaldo/Downloads/modelo_relatorio_getnet.csv";

        return csvToJsonConverter.convertCsvToJson(csvFilePath).toString();

    }
}
