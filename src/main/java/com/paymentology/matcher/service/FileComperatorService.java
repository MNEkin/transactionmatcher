package com.paymentology.matcher.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.paymentology.matcher.model.Report;

public interface FileComperatorService {
	
	public Report compareFiles(MultipartFile file1, MultipartFile file2);

	public Report compareFiles(File file, File file2);

}
