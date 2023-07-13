package com.practice.projectlibrary;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ProjectLibraryApplication implements CommandLineRunner {


	@Override
	public void run(String args[]) throws Exception
	{

		// Print statement when method is called
		System.out.println("HEllo world");
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectLibraryApplication.class, args);
	}


}
