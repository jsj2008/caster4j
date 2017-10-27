package caster.demo.code._common.other;

import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Excel {
	private Workbook workBook;
	private ExcelType excelType;
	private Sheet sheet;

	Excel(Workbook workBook, ExcelType excelType) {
		this.excelType = excelType;
        this.workBook = workBook;
	}

	public Workbook getWorkbook() {
		return workBook;
	}

	public String getSuffix() {
		return excelType.getSuffix();
	}

	public Integer getLastRowNum() {
		// get the last row number on the now select sheet
		// based 0, contained n
		return currentSheet().getLastRowNum();
	}

	public Excel createSheet() {
		this.sheet = workBook.createSheet();
		return this;
	}

	public Excel createSheet(String sheetname) {
		this.sheet = workBook.createSheet(sheetname);
		return this;
	}

	public Excel selectSheet(int index) {
		this.sheet = workBook.getSheetAt(index);
		return this;
	}

	public Excel selectSheet(String sheetname) {
		this.sheet = workBook.getSheet(sheetname);
		return this;
	}

	public Sheet currentSheet() {
		if (sheet == null) {
			throw new RuntimeException("Create or select sheet first.");
		}
		return sheet;
	}

	public List<?> readRow(Integer rowNum) {
		Row row = currentSheet().getRow(rowNum);
		return readRow(rowNum, (int)row.getFirstCellNum(), (int)row.getLastCellNum());
	}

	public List<?> readRow(Integer rowNum, Integer colNum) {
		// get 0 to colNum cell (not contain colNum)
		return readRow(rowNum, 0, colNum);
	}

	public List<?> readRow(Integer rowNum, Integer firstCellNum, Integer lastCellNum) {
		// get now select sheet a line, rowNum begin 0
		// firstCellNum begin 0, not contain lastCellNum
		List<Object> rowContent = new ArrayList<>();
		Row row = currentSheet().getRow(rowNum);
		for (int i = firstCellNum; i < lastCellNum; i++) {
			rowContent.add(getCellValue(row.getCell(i)));
		}
		return rowContent;
	}

	public Excel writeRow(Integer rowNum, List<?> rowContent) {
		int len = rowContent.size();
		Row row = currentSheet().createRow(rowNum);
		for (int i = 0; i < len; ++i) {
			setCellValue(row.createCell(i), rowContent.get(i));
		}
		return this;
	}

	public Excel write(OutputStream out) throws IOException {
		workBook.write(out);
		out.flush();
		return this;
	}

	private Object getCellValue(Cell cell) {
		Object cellValue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
				case Cell.CELL_TYPE_FORMULA: {
					if (DateUtil.isCellDateFormatted(cell)) {
						cellValue = cell.getDateCellValue();
					} else {
						cellValue = cell.getNumericCellValue();
					} break;
				}
				case Cell.CELL_TYPE_BOOLEAN:
					cellValue = cell.getBooleanCellValue(); break;
				default :
					cellValue = cell.getStringCellValue();
			}
		}
		return cellValue;
	}

	private void setCellValue(Cell cell, Object value) {
		if (value == null) {
			cell.setCellValue("");
		} else if (value instanceof String) {
			cell.setCellValue((String)value);
		} else if (value instanceof Number) {
			cell.setCellValue(((Number)value).doubleValue());
		} else if (value instanceof RichTextString) {
			cell.setCellValue((RichTextString)value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean)value);
		} else {
			cell.setCellValue(value.toString());
		}
	}

}
