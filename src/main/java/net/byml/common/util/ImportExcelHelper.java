package net.byml.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ImportExcelHelper {
	private static ImportExcelHelper instance;

	private ImportExcelHelper() {

	}

	public static ImportExcelHelper getInstance() {
		if (instance == null) {
			instance = new ImportExcelHelper();
		}
		return instance;
	}

	public List<Map<String, Object>> toMapList(InputStream is,
			DataDefinitions dataDefinitions) {
		return toMapList(is, null, dataDefinitions);
	}

	public List<Map<String, Object>> toMapList(InputStream is,
			String sheetName, DataDefinitions definitions) {
		List<Map<String, Object>> mapList = null;
		if (is != null) {
			try {
				Workbook wb = WorkbookFactory.create(is);
				Sheet sheet;
				if (sheetName == null) {
					sheet = wb.getSheetAt(0);
				} else {
					sheet = wb.getSheet(sheetName);
				}
				if (sheet != null) {
					mapList = toMapList(sheet, definitions);
				}
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}
		return mapList;
	}

	public List<Map<String, Object>> toMapList(String fileName,
			String sheetName, DataDefinitions definitions) {
		List<Map<String, Object>> mapList = null;
		InputStream is = null;
		try {
			is = new FileInputStream(fileName);
			mapList = toMapList(is, sheetName, definitions);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return mapList;
	}

	public List<Map<String, Object>> toMapList(String fileName,
			DataDefinitions definitions) {
		return toMapList(fileName, null, definitions);
	}

	public List<Map<String, Object>> toMapList(Sheet sheet,
			DataDefinitions definitions) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 1; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				Map<String, Object> map = toMap(row, definitions);
				list.add(map);
			}
		}
		return list;
	}

	public Map<String, Object> toMap(Row row, DataDefinitions definitions) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Iterator<DataDefinition> it = definitions.getDataDefinitionList()
				.iterator(); it.hasNext();) {
			DataDefinition definition = it.next();
			Cell cell = row.getCell(definition.getPosition());
			if (cell != null) {
				map.put(definition.getKey(),
						toValue(cell, definition.getDataType()));
			}
		}
		return map;
	}

	public Object toValue(Cell cell, int dataType) {
		Object value = null;
		if (cell != null) {
			int cellType = cell.getCellType();
			if (Cell.CELL_TYPE_BLANK == cellType) {
				value = null;
			} else {
				if (DataType.STRING == dataType) {
					if (Cell.CELL_TYPE_BOOLEAN == cellType) {
						value = "" + cell.getBooleanCellValue();
					} else if (Cell.CELL_TYPE_ERROR == cellType) {
						value = "" + cell.getErrorCellValue();
					} else if (Cell.CELL_TYPE_FORMULA == cellType) {
						value = cell.getCellFormula();
					} else if (Cell.CELL_TYPE_NUMERIC == cellType) {
						value = cell.getNumericCellValue();
					} else if (Cell.CELL_TYPE_STRING == cellType) {
						value = cell.getStringCellValue();
					}
				} else if (DataType.DOUBLE == dataType) {
					if (Cell.CELL_TYPE_NUMERIC == cellType) {
						value = new Double(cell.getNumericCellValue());
					} else if (Cell.CELL_TYPE_STRING == cellType) {
						value = new Double(NumberUtils.toDouble(cell
								.getStringCellValue()));
					} else if (Cell.CELL_TYPE_FORMULA == cellType) {
						// TODO
					} else {

					}
				} else if (DataType.LONG == dataType) {
					if (Cell.CELL_TYPE_NUMERIC == cellType) {
						value = new Long(
								new Double(cell.getNumericCellValue())
										.longValue());
					} else if (Cell.CELL_TYPE_STRING == cellType) {
						value = new Long(new Double(NumberUtils.toDouble(cell
								.getStringCellValue())).longValue());
					} else if (Cell.CELL_TYPE_FORMULA == cellType) {
						// TODO
					} else {

					}
				} else if (DataType.INT == dataType) {
					if (Cell.CELL_TYPE_NUMERIC == cellType) {
						value = new Integer(new Double(
								cell.getNumericCellValue()).intValue());
					} else if (Cell.CELL_TYPE_STRING == cellType) {
						value = new Integer(
								new Double(NumberUtils.toDouble(cell
										.getStringCellValue())).intValue());
					} else if (Cell.CELL_TYPE_FORMULA == cellType) {
						// TODO
					} else {

					}
				} else if (DataType.DATE == dataType) {
					if (Cell.CELL_TYPE_NUMERIC == cellType) {
						value = new Date(
								new Double(cell.getNumericCellValue())
										.longValue());
					} else if (Cell.CELL_TYPE_STRING == cellType) {
						try {
							value = DateUtils.parseDate(
									cell.getStringCellValue(), new String[] {
											"yyyy-MM-dd", "yyyy/MM/dd" });
						} catch (Exception e) {
							e.printStackTrace();
						}

					} else if (Cell.CELL_TYPE_FORMULA == cellType) {
						// TODO
					} else {

					}
				} else if (DataType.BOOLEAN == dataType) {
					if (Cell.CELL_TYPE_BOOLEAN == cellType) {
						value = Boolean.valueOf(cell.getBooleanCellValue());
					} else if (Cell.CELL_TYPE_STRING == cellType) {
						String cellValue = cell.getStringCellValue();
						if ("T".equalsIgnoreCase(cellValue)
								|| "Y".equalsIgnoreCase(cellValue)) {
							value = Boolean.TRUE;
						} else {
							value = Boolean.FALSE;
						}
					} else {

					}
				} else {
					value = cell.toString();
				}
			}
		}
		return value;
	}
}
