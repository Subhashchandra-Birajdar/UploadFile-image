package com.jts.controller;

import java.io.IOException;
import java.util.List;

import com.jts.response.ImageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jts.entity.Files;
import com.jts.service.FilesService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class FilesController {
	
	private static final Logger log = LoggerFactory.getLogger(FilesService.class);
	
	@Autowired
	private FilesService filesService;

	// Send the Image into db
	@PostMapping("/uploadFilesIntoDB")
	public ResponseEntity<String> storeFilesIntoDB(
			@RequestParam("file") MultipartFile file) throws IOException {
		log.info("Enter into uploadFilesIntoDB method");
		String response = filesService.storeFile(file);
		log.info("Completed uploadFilesIntoDB method with response {}", response);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	// get all images from db
	@GetMapping("/files")
	public ResponseEntity<List<ImageResponse>> getAllFiles() {
		List<Files> imagelist = filesService.getAllFiles();

		// Convert Files entity list to ImageResponse list
		List<ImageResponse> responseList = imagelist.stream().map(file -> {
				String url = "/api/getFilesFromFileSystem/" + file.getName();
			return new ImageResponse(file.getName(), file.getType(), url);
		}).toList();

		return ResponseEntity.status(HttpStatus.OK).body(responseList);
	}

	// get image file using imagename contains
	@GetMapping("/getFilesFromFileSystem/{fileName}")
	public ResponseEntity<byte[]> downloadImageFromFileSystem(@PathVariable String fileName) {
		byte[] imageData = filesService.getFiles(fileName);
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);
	}

}
