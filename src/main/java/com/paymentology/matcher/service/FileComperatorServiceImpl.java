package com.paymentology.matcher.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.paymentology.matcher.model.Report;
import com.paymentology.matcher.model.Transaction;

@Service
public class FileComperatorServiceImpl implements FileComperatorService{

	@Autowired
	MatchChecker matchChecker;
	
	Report report;	
	
	@Override
	public Report compareFiles(MultipartFile file1, MultipartFile file2) {
		// TODO Auto-generated method stub
		report = new Report();
		if(!file1.getOriginalFilename().endsWith("csv") || !file2.getOriginalFilename().endsWith("csv")) {
			report.setError("File format should be csv!");
			return report;
		} else {
			report = matchChecker.checkTransactionMatch(loadFileIntoList(file1), loadFileIntoList(file2), "JaroWinkler");
		}
		
		return report;
	}
	
	private List<Transaction> loadFileIntoList(MultipartFile f) {
		List<Transaction> list = new ArrayList<Transaction>();
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(f.getInputStream()));
			br.readLine(); //First line discarded
			String line;
			while((line = br.readLine()) != null) {
				
				list.add(new Transaction(line));
			}
		} catch (IOException e) {
			report.setError("An error occured during file parsing. Check the file please.");
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public Report compareFiles(File file1, File file2) {
		report = new Report();
		report = matchChecker.checkTransactionMatch(loadFileIntoList(file1), loadFileIntoList(file2), "");
		return report;
	}
	
	private List<Transaction> loadFileIntoList(File f) {
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			br.readLine(); //First line discarded
			String line;
			while((line = br.readLine()) != null) {
				String[] tmp = line.split(",",-1);
				list.add(new Transaction(tmp[0], tmp[1], tmp[2], tmp[3], tmp[4], tmp[5], Integer.valueOf(tmp[6]), tmp[7]));
			}
		} catch (IOException e) {
			report.setError("An error occured during file parsing. Check the file please.");
			e.printStackTrace();
		}
		return list;
	}

}
