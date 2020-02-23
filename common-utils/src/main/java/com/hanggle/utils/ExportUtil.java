package com.hanggle.utils;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.*;

/**
 * @description:
 * @author: hanggle
 * @date: 2019/1/4
 */
public class ExportUtil {

    public static String doExport(String exportType, String sheetName, List<?> list, String rootPath,
                           Map<String, String> fieldMap, Map<String, Object> formatMap)

            throws IllegalArgumentException,
            ClassNotFoundException,
            IllegalAccessException,
            IOException {

        /**
         * 以下是导出数据到excel
         */
        ExcelExportUtil excelExportUtil = new ExcelExportUtil();
        //String filePath  = excelExportUtil.exportXLS(sheetName, list, rootPath, fieldMap, formatMap);
        //XSSFWorkbook workbook = new XSSFWorkbook();
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        String filePath  = excelExportUtil.exportXLSX(workbook, sheetName, list, rootPath, fieldMap, formatMap);
        return filePath;
    }
}
