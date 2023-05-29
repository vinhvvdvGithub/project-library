package com.practice.projectlibrary.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ICloudinaryService {
  String uploadFile(MultipartFile multipartFile);

  File convertMultiPartToFile(MultipartFile multipartFile) throws IOException;
}
