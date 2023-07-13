package com.peaksoft.lms.utils;

import com.peaksoft.lms.dto.responses.excel.ExcelResponse;
import com.peaksoft.lms.enums.StudyFormat;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ExportToExcel {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<ExcelResponse> studentExcel;

    public ExportToExcel(List<ExcelResponse> studentExcel) {
        this.studentExcel = studentExcel;
        this.workbook = new XSSFWorkbook();
    }

    private void createCells(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof StudyFormat) {
            cell.setCellValue(String.valueOf(value));
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void createHeaderRow() {
        sheet = workbook.createSheet("Student Information");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCells(row, 0, "Student Information", style);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));
        font.setFontHeightInPoints((short) 10);

        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCells(row, 0, "ID", style);
        createCells(row, 1, "Full Name", style);
        createCells(row, 2, "Group", style);
        createCells(row, 3, "Study Format", style);
        createCells(row, 4, "Phone Number", style);
        createCells(row, 5, "Email", style);
    }

    private void writeStudentDateHear() {
        int rowCount = 2;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ExcelResponse student : studentExcel) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCells(row, columnCount++, student.getId(), style);
            createCells(row, columnCount++, student.getFullName(), style);
            createCells(row, columnCount++, student.getGroup(), style);
            createCells(row, columnCount++, student.getStudyFormat(), style);
            createCells(row, columnCount++, student.getPhoneNumber(), style);
            createCells(row, columnCount++, student.getEmail(), style);
        }
    }


    public void exportDataToExcel(HttpServletResponse response) throws IOException {
        createHeaderRow();
        writeStudentDateHear();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

}
