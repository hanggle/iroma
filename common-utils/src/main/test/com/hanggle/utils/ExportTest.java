package com.hanggle.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class ExportTest {

    @Test
    public void doExport() {

        List<TempDto> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new TempDto("name-" + i, i, i%2) );
        }

        Map<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("name", "名称");
        fieldMap.put("age", "年龄");
        fieldMap.put("gender", "性别");
        fieldMap.put("school.id", "学校");

        String rootPath = "d:/temp"; //rootPathConfig.getKeyValue();

        String sheetName = "教室数据tab";
        String exportType = "教室数据";

        Map<String, String> genderFormatMap = new HashMap<>();
        genderFormatMap.put("1", "男");
        genderFormatMap.put("0", "女");
        Map<String, Object> formatMap = new HashMap<>();
        formatMap.put("gender", genderFormatMap);

        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                long begin = System.currentTimeMillis();
                try {
                    ExportUtil.doExport(exportType, sheetName, list, rootPath, fieldMap, formatMap);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()-begin);
            }).start();
        }

    }
}