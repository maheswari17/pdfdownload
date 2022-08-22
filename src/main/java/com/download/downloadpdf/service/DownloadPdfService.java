package com.download.downloadpdf.service;
import org.springframework.core.io.Resource;

public interface DownloadPdfService {
     Resource getPdf(String fileName) throws Exception;
}
