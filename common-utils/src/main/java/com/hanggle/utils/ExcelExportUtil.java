package com.hanggle.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelExportUtil {

	public String exportXLS(String sheetName, List<?> list, String rootPath, Map<String, String> fieldMap,
							Map<String, String> formatMap)

			throws ClassNotFoundException,
			IllegalArgumentException,
			IllegalAccessException,
			IOException {

		/**
		 * 创建表头Map对象
		 */
		Map<String, String> headMap = new HashMap<>();

		/**
		 * 封装表头名称，同时封闭表头key的数组它的先后顺序决定了导出是每行各单元格顺序
		 */
		String[] keyAry = assembleKeyAry(fieldMap, headMap);

		/**
		 * 创建一个webbook，对应一个Excel文件
		 */
		HSSFWorkbook workbook = new HSSFWorkbook();

		/**
		 * 在webbook中添加一个sheet,对应Excel文件中的sheet
		 */

		int pageSize = 50000;

		Integer dataSize = list.size();
		int sheetNums = (dataSize / pageSize) + 1;

		for (int s = 0; s < sheetNums; s++) {

			int sheetIndex = s + 1;

			String sheetName1 = "(" + sheetIndex + ")" + sheetName;
			HSSFSheet sheet = workbook.createSheet(sheetName1);

			int beginNum = s * pageSize;

			// 写入标题行
			writeRow(headMap, keyAry, sheet, 0);

			// 写入实体数据
			int rowIndex = 1;
			for (int i = beginNum; i < beginNum + pageSize; i++) {

				if (list.size() < (i + 1)) {
					break;
				}

				// 将本行的数据封闭到map中
				Map<String, String> dataMap = getRowMap(list.get(i), keyAry, formatMap);

				writeRow(dataMap, keyAry, sheet, rowIndex);

				rowIndex++;
			}
		}

		/**
		 * 以下是保存EXCEL文件
		 */
		String filePath = saveExportFile(rootPath, workbook);

		return filePath;

	}

	private String saveExportFile(String rootPath, HSSFWorkbook workbook)
			throws IOException {
		String filePath = "";
		// 第六步，将文件存到指定位置
		String year = String.valueOf(DateUtil.getYear());
		String month = String.valueOf(DateUtil.getMonth());

		// 文件夹路径
		String folderPath = rootPath + File.separator + year + File.separator + month;
		File path = new File(folderPath);

		if (!path.exists()) {
			path.mkdirs();
		}

		String fileName = System.currentTimeMillis() + ".xls";
		filePath = folderPath + File.separator + fileName;

		path = new File(filePath);
		if (!path.exists()) {
			path.createNewFile();
		}

		FileOutputStream fileOutputStream = new FileOutputStream(filePath);
		workbook.write(fileOutputStream);
		fileOutputStream.close();
		return filePath;

	}

	private Map<String, String> getRowMap(Object obj, String[] keyAry, Map<String, String> formatMap)
			throws IllegalArgumentException,
			IllegalAccessException,
			ClassNotFoundException {

		Map<String, String> resultMap = new HashMap<>();

		// 利用反射取出数据值
		Class<?> c = obj.getClass();
		Field[] fields = c.getDeclaredFields();

		for (Field f : fields) {
			f.setAccessible(true);
		}

		for (Field f : fields) {

			String fieldName = f.toString().substring(f.toString().lastIndexOf(".") + 1);

			for (int column = 0; column < keyAry.length; column++) {
				String key = keyAry[column];
				// 如果字段为类对象时,例如 createUser.cnName
				if (key.contains(".")) {
					String[] temp = key.split("\\.");
					String fatherKey = temp[0];
					String childKey = temp[1];
					Object childObject = null;

					if (f.get(obj) != null) {
						childObject = f.get(obj);
						if (fatherKey.equals(fieldName)) {
							// 字段为类对象时,eg createUser.cnName
							child(childKey, key, childObject, resultMap, formatMap);
						}
					}
				}
				else {
					if (key.equals(fieldName)) {
						if (null != formatMap && formatMap.get(String.valueOf(f.get(obj))) != null) {
							resultMap.put(key, formatMap.get(String.valueOf(f.get(obj))));
						}
						else {
							if (f.get(obj) != null) {
								resultMap.put(key, (String) f.get(obj).toString());
							}
							else {
								resultMap.put(key, "");
							}
						}
					}
				}
			}
		}
		return resultMap;
	}

	private void child(String childKey, String key, Object chileObject, Map<String, String> dataMap,
					   Map<String, String> formatMap)
			throws ClassNotFoundException,
			IllegalAccessException {

		Class<?> c = chileObject.getClass();
		Field[] fields = c.getDeclaredFields();

		for (Field f : fields) {
			f.setAccessible(true);
		}

		for (Field f : fields) {
			String fieldName = f.toString().substring(f.toString().lastIndexOf(".") + 1);
			if (childKey.equals(fieldName)) {
				if (null != formatMap && formatMap.get(String.valueOf(f.get(chileObject))) != null) {
					dataMap.put(key, formatMap.get(String.valueOf(f.get(chileObject))));
				}
				else {
					if (f.get(chileObject) != null) {
						dataMap.put(key, f.get(chileObject).toString());
					}
					else {
						dataMap.put(key, "");
					}
				}
			}
		}
	}

	/**
	 * 封闭表头key的数组，先后顺序决定了导出的顺序
	 * @param map
	 * @param headMap
	 * @return
	 */
	private String[] assembleKeyAry(Map<String, String> map, Map<String, String> headMap) {

		// 封闭表头key的数组，先后顺序决定了导出的顺序
		String keyAry[] = new String[map.keySet().size()];
		int index = 0;
		for (String key : map.keySet()) {
			keyAry[index] = key;
			headMap.put(key, map.get(key));
			index++;
		}

		return keyAry;
	}

	private void writeRow(Map<String, String> dataMap, String[] keyAry, HSSFSheet sheet, int rowIndex) {

		// 在sheet中添加表头第0行
		HSSFRow row = sheet.createRow(rowIndex);

		for (int i = 0; i < keyAry.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(dataMap.get(keyAry[i]));
		}
	}
}
