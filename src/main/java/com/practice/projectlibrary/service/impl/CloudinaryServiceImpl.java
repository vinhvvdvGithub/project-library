package com.practice.projectlibrary.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.practice.projectlibrary.service.ICloudinaryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements ICloudinaryService {

  private static Logger logger = LoggerFactory.getLogger(CloudinaryServiceImpl.class);


  private final Cloudinary cloudinary;

  @Override
  public String uploadFile(MultipartFile file,String slug) {
    String resultURL = "";
    try {
      var uploadResult = cloudinary
          .uploader()
          .upload(file.getBytes(), ObjectUtils.asMap("folder", "library_image","resource_type", "auto",
              "public_id",slug +"-image"));
      resultURL = uploadResult.get("secure_url").toString();
      logger.info("Upload file: " + resultURL + " successfully!");
    } catch (IOException e) {
      logger.error("Upload file : " + file.getOriginalFilename() + " failed!");
      logger.error(e.getMessage());
    }
    return resultURL;
  }
  @Override
  public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
    File convFile = new File(multipartFile.getOriginalFilename());
    FileOutputStream fos = new FileOutputStream(convFile);
//    fos.write(multipartFile.getBytes());
    fos.close();

    return convFile;
  }
}
