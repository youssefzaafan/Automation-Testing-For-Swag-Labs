package com.swaglabs.utils;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonUtils {

    private static final String JSON_FILE_PATH = "src/test/resources/";
    private String jsonReader;
    private String jsonFileName;

    public JsonUtils(String jsonFileName) {
        this.jsonFileName = jsonFileName;

        try {
            Object data = new JSONParser().parse(
                    new FileReader(JSON_FILE_PATH + jsonFileName + ".json")
            );
            jsonReader = data.toString();

        } catch (Exception e) {
            LogsUtil.error("Error reading JSON file: " + e.getMessage());
        }
    }

    public String getJsonData(String jsonPath){
        try {
            return JsonPath.read(jsonReader, jsonPath);
        } catch (Exception e) {
            LogsUtil.error(e.getMessage(),
                    "No results for json path: '" + jsonPath + "' in file: '" + jsonFileName + "'");
            return "";
        }
    }
}