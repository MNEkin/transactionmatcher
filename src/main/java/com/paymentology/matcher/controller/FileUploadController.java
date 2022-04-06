package com.paymentology.matcher.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.paymentology.matcher.model.Report;
import com.paymentology.matcher.service.FileComperatorService;
import com.paymentology.matcher.service.FileComperatorServiceImpl;

@RestController
@CrossOrigin("http://localhost:4200")
public class FileUploadController {
	
	@Autowired
	FileComperatorService comperatorService;
	
	@PostMapping("/upload")
	public Report uploadFiles(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2) {
		System.out.println("Geldi");
		return comperatorService.compareFiles(file1, file2);
	}
	
	@GetMapping("/upload")
	public Report uploadFiles() throws IOException {
		return comperatorService.compareFiles(new ClassPathResource("ClientMarkoffFile20140113.csv").getFile(), new ClassPathResource("PaymentologyMarkoffFile20140113.csv").getFile());
	}

}
