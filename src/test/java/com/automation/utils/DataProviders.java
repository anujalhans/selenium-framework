package com.automation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    @DataProvider(name = "csvUsers")
    public Object[][] csvUsers() throws IOException {
        List<Object[]> rows = new ArrayList<>();
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("src/test/resources/testdata/sample-users.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                rows.add(new Object[]{parts[0], parts[1]});
            }
        }
        return rows.toArray(new Object[0][]);
    }

    @DataProvider(name = "excelSample")
    public Object[][] excelSample() throws IOException {
        List<Object[]> rows = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("src/test/resources/testdata/sample.xlsx")) {
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> it = sheet.iterator();
            if (it.hasNext()) it.next();
            while (it.hasNext()) {
                Row r = it.next();
                rows.add(new Object[]{r.getCell(0).getStringCellValue(), r.getCell(1).getStringCellValue()});
            }
        } catch (java.io.FileNotFoundException e) {
            return new Object[][]{};
        }
        return rows.toArray(new Object[0][]);
    }

    @DataProvider(name = "jsonSample")
    public Object[][] jsonSample() throws IOException {
        File file = new File("src/test/resources/testdata/sample.json");
        if (!file.exists()) {
            return new Object[][]{};
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(file);
        List<Object[]> rows = new ArrayList<>();
        for (JsonNode n : root) {
            rows.add(new Object[]{n.get("name").asText(), n.get("email").asText()});
        }
        return rows.toArray(new Object[0][]);
    }
}



