package com.suji.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.suji.binding.SearchRequest;
import com.suji.binding.SearchResponse;
import com.suji.entity.ReportEntity;
import com.suji.repo.ReportRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ServiceImp implements ReportService {

	@Autowired
	private ReportRepo repo;

	@Override
	public List<SearchResponse> SearchResponse(SearchRequest request) {
		ReportEntity queryBuilder = new ReportEntity();
		String planName = request.getPlanName();

		if (planName != null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}
		String planStatus = request.getPlanStatus();

		if (planStatus != null && !planStatus.equals("")) {
			queryBuilder.setPlanName(planStatus);
		}

		LocalDate startDate = request.getStartDate();
		if (startDate != null) {
			queryBuilder.setStartDate(startDate);
		}
		LocalDate endDate = request.getEndDate();
		if (endDate != null) {
			queryBuilder.setEndDate(endDate);
		}
		
		Example<ReportEntity> example = Example.of(queryBuilder);

		List<SearchResponse> response = new ArrayList<>();
		List<ReportEntity> findAll = repo.findAll(example);
		for (ReportEntity entity : findAll) {
			SearchResponse searchResponse = new SearchResponse();
			BeanUtils.copyProperties(entity, searchResponse);
			response.add(searchResponse);
		}
		return response;

	}

	@Override
	public List<String> getPlanNames() {
		List<String> findByPlanName = repo.findByPlanName();
		return findByPlanName;
	}

	@Override
	public List<String> getPlanStatus() {
		List<String> findByPlanStatus = repo.findByPlanStatus();
		return findByPlanStatus;
	}

	@Override
	public void generatedPdf(HttpServletResponse response) throws Exception{
		
		List<ReportEntity> findAll = repo.findAll();
		
		Document document = new Document(PageSize.A4);
	   
	    PdfWriter.getInstance(document, response.getOutputStream());

	    document.open();
	   
	    Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    fontTiltle.setSize(20);
	    
	    Paragraph paragraph1 = new Paragraph("Search Report", fontTiltle);
	    
	    paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

	    document.add(paragraph1);
	   
	    PdfPTable table = new PdfPTable(8);
	    
	    table.setWidthPercentage(100f);
	    //table.setWidths(new float[] {1.5f,1.5f,3.5f,1.5f,1.5f,1.5f,1.5f,});
	    table.setSpacingBefore(5);
	   
	    PdfPCell cell = new PdfPCell();
	    
	    cell.setBackgroundColor(CMYKColor.BLUE);
	    cell.setPadding(5);
	     
	    Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
	    font.setColor(CMYKColor.BLACK);
	     
	    cell.setPhrase(new Phrase("S.NO", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("NAME", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("EMAIL", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("GENDER", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("MOBILE", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("SSN", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("PLAN NAME", font));
	    table.addCell(cell);
	    cell.setPhrase(new Phrase("PLAN STATUS", font));
	    table.addCell(cell);
		
	    
	    for(ReportEntity entity:findAll) {
	    	table.addCell(String.valueOf(entity.getSno()));
	    	table.addCell(entity.getName());
	    	table.addCell(entity.getEmail());
	    	table.addCell(entity.getGender());
	    	table.addCell(String.valueOf(entity.getMobile()));
	    	table.addCell(String.valueOf(entity.getSsn()));
	    	table.addCell(entity.getPlanName());
	    	table.addCell(entity.getPlanStatus());
	    }
		document.add(table);
		document.close();
	}

	@Override
	public void generatedExcel(HttpServletResponse response) throws Exception {
		List<ReportEntity> findAll = repo.findAll();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet createSheet = workbook.createSheet();
		HSSFRow headerRow = createSheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("S.No");
		headerRow.createCell(1).setCellValue("NAME");
		headerRow.createCell(2).setCellValue("EMAIL");
		headerRow.createCell(3).setCellValue("GENDER");
		headerRow.createCell(4).setCellValue("MOBILE");
		headerRow.createCell(5).setCellValue("SSN");
		headerRow.createCell(6).setCellValue("PLAN NAME");
		
		int i=1;
		for(ReportEntity entity: findAll) {
			HSSFRow dataRow = createSheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getSno());
			dataRow.createCell(1).setCellValue(entity.getName());
			dataRow.createCell(2).setCellValue(entity.getEmail());
			dataRow.createCell(3).setCellValue(entity.getGender());
			dataRow.createCell(4).setCellValue(entity.getMobile());
			dataRow.createCell(5).setCellValue(entity.getSsn());
			dataRow.createCell(6).setCellValue(entity.getPlanName());
			i++;
		}
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.close();
	}

}
