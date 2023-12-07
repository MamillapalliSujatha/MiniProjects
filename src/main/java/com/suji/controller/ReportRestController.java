package com.suji.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suji.binding.SearchRequest;
import com.suji.binding.SearchResponse;
import com.suji.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportRestController {

	@Autowired
	private ReportService reportService;

	@GetMapping("/plannames")
	public ResponseEntity<List<String>> getUniquePlans() {
		List<String> planNames = reportService.getPlanNames();
		return new ResponseEntity<List<String>>(planNames, HttpStatus.OK);
	}

	@GetMapping("/planstatus")
	public ResponseEntity<List<String>> getUniquePlanStatus() {
		List<String> planStatus = reportService.getPlanStatus();
		return new ResponseEntity<List<String>>(planStatus, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<SearchResponse>> getSearchResponse(SearchRequest request) {
		List<SearchResponse> searchResponse = reportService.SearchResponse(request);
		return new ResponseEntity<List<SearchResponse>>(searchResponse, HttpStatus.OK);
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=SearchResponse" + ".xls";
		response.setHeader(headerkey, headervalue);
		reportService.generatedExcel(response);
	}

	@GetMapping("/pdf")
	public void gerenetePdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=SearchResponse" + ".pdf";
		response.setHeader(headerkey, headervalue);
		reportService.generatedPdf(response);
	}

}
