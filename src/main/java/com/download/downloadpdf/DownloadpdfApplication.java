package com.download.downloadpdf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.download.downloadpdf"})
public class DownloadpdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(DownloadpdfApplication.class, args);
	}

}
