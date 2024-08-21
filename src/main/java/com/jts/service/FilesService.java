package com.jts.service;

import java.io.File;
import java.io.IOException;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jts.entity.FileRepository;
import com.jts.entity.Files;

@Service
public class FilesService {

	private static final Logger log = LoggerFactory.getLogger(FilesService.class);

	@Autowired
	private FileRepository fileRepository;

	// Injecting the file path from application properties
	@Value("${file.storage.path}")
	private String filePath;

	@Transactional
	public String storeFile(MultipartFile file) throws IOException {

		// Construct the full file path by appending the file name to the storage path
		String file_Path = filePath + file.getOriginalFilename();

		// Create a new Files entity with the file's metadata and content
		Files files = Files
				.builder()
				.name(file.getOriginalFilename())
				.path(file_Path)
				.type(file.getContentType())
				.imageData(file.getBytes()).build();

		// Save the Files entity to the database
		files = fileRepository.save(files);

		// Log the successful upload
		log.info("File uploaded successfully into database with id {}", files.getId());

		// Transfer the file to the specified location on the file system
		file.transferTo(new File(filePath));

		// Return a success message if the file was successfully saved in the database
		if (files.getId() != null) {
			return "File uploaded successfully into database";
		}
		// Return null if the file was not successfully saved
		return null;
	}

	@Transactional(readOnly = true)
	public byte[] getFiles(String fileName) {
		// Retrieve the file's binary data from the database by its name
		return fileRepository.findByName(fileName).getImageData();
	}

	@Transactional(readOnly = true)
	public List<Files> getAllFiles() {
		// Retrieve a list of all files stored in the database
		return fileRepository.findAll();
	}

	@Transactional(readOnly = true)
	public byte[] downloadFilesFromFileSystem(String fileName) throws IOException {
		// Retrieve the file path from the database based on the file name
		String path = fileRepository.findByName(fileName).getPath();
		// Read the file's contents from the file system as a byte array
		return java.nio.file.Files.readAllBytes(new File(path).toPath());
	}
}
