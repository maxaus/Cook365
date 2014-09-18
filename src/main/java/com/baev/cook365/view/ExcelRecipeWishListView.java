package com.baev.cook365.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * Для представления продуктов, требуемых для приготовления блюд из списка, в виде Excel файла.
 *
 * @author Maxim Baev
 */
public class ExcelRecipeWishListView extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> productMap = (Map<String, String>) model.get("productMap");
		//create a wordsheet
		HSSFSheet sheet = workbook.createSheet("Список продуктов");

		HSSFRow header = sheet.createRow(0);
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);
		Cell headerCell1 = header.createCell(0);
		headerCell1.setCellStyle(cellStyle);
		headerCell1.setCellValue("Продукт");
		Cell headerCell2 = header.createCell(1);
		headerCell2.setCellStyle(cellStyle);
		headerCell2.setCellValue("Кол-во");

		int rowNum = 1;
		for (Map.Entry<String, String> entry : productMap.entrySet()) {
			//create the row data
			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(entry.getKey());
			row.createCell(1).setCellValue(entry.getValue());
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"product_list.xls\"");
	}
}
