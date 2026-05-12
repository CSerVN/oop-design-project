package com.group3.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonLibraryDatabase implements DataConnection<List<Exercise>> {
    private final String filePath = "exerciseLibrary.json";
    private final Gson gson;

    public JsonLibraryDatabase() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public List<Exercise> loadData() {
        try (Reader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Exercise>>() {}.getType();
            List<Exercise> lib = gson.fromJson(reader, listType);
            return lib != null ? lib : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Chưa có dữ liệu thư viện bài tập.");
            return new ArrayList<>();
        }
    }

    @Override
    public boolean saveData(List<Exercise> data) {
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}