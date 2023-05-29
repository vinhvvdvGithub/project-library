package com.practice.projectlibrary.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.practice.projectlibrary.service.ICloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements ICloudinaryService {

  private final Cloudinary cloudinary;

  @Override
  public String uploadFile(MultipartFile multipartFile) {
    try {
      File uploadedFile = convertMultiPartToFile(multipartFile);
      Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.asMap("folder", "library_image"));
      return uploadResult.get("url").toString();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }

  }

  @Override
  public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
    File convFile = new File(multipartFile.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(multipartFile.getBytes());
    fos.close();

    return convFile;
  }
}
