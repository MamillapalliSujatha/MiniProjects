package com.suji.service;

import java.util.List;

import com.suji.binding.SearchRequest;
import com.suji.binding.SearchResponse;

import jakarta.servlet.http.HttpServletResponse;


public interface ReportService {
	
	public List<SearchResponse> SearchResponse(SearchRequest request);
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public void generatedPdf(HttpServletResponse response ) throws Exception;  //openpdf
	
	public void generatedExcel(HttpServletResponse response ) throws Exception ;//apache-poi
}
