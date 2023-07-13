package com.practice.projectlibrary.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ICloudinaryService {
  String uploadFile(MultipartFile multipartFile,String slug);

  File convertMultiPartToFile(MultipartFile multipartFile) throws IOException;

	String updateImage(MultipartFile file,String slug,String urlImage) ;
	String updateImage(MultipartFile file,String slug) ;

}
