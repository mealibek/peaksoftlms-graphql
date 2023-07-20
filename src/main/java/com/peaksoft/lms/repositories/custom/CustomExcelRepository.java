package com.peaksoft.lms.repositories.custom;

import com.peaksoft.lms.dto.responses.excel.ExcelResponse;

import java.util.List;

public interface CustomExcelRepository {
  List<ExcelResponse> getAllExportExcelStudents();
}
