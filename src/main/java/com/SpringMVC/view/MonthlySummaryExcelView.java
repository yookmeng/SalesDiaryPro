package com.SpringMVC.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.SpringMVC.model.UserMonthlySummary;

@SuppressWarnings("deprecation")
public class MonthlySummaryExcelView extends AbstractExcelView {

	@SuppressWarnings("rawtypes")
	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HSSFSheet excelSheet = workbook.createSheet("Monthly Summary Report");
		setExcelHeader(excelSheet);
		
		@SuppressWarnings("unchecked")
		List<UserMonthlySummary> listMonthlySummary = (List) model.get("monthlySummaryList");
		setExcelRows(excelSheet,listMonthlySummary);
		
	}

	public void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelRow1 = excelSheet.createRow(0);
		excelRow1.createCell(0).setCellValue("SC Name :");
		excelRow1.createCell(1).setCellValue("Eric Ong");
		HSSFRow excelRow2 = excelSheet.createRow(1);
		excelRow2.createCell(0).setCellValue("Team Name :");
		excelRow2.createCell(1).setCellValue("Fortune");
		HSSFRow excelRow4 = excelSheet.createRow(3);
		excelRow4.createCell(0).setCellValue("No");
		excelRow4.createCell(1).setCellValue("Header Field 1");
		excelRow4.createCell(2).setCellValue("Header Field 2");
		excelRow4.createCell(3).setCellValue("Header Field 3");
	}
	
	@SuppressWarnings("rawtypes")
	public void setExcelRows(HSSFSheet excelSheet, List listMonthlySummary){
		int record = 4;
		for(int m=record;m<=14;m++) {
			HSSFRow excelRow = excelSheet.createRow(m);
			excelRow.createCell(0).setCellValue(m);
			excelRow.createCell(1).setCellValue("Field 1");
			excelRow.createCell(2).setCellValue("Field 2");
			excelRow.createCell(3).setCellValue("Field 3");
		}
	}
}