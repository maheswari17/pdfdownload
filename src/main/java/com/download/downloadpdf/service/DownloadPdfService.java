package com.download.downloadpdf.service;
import org.springframework.core.io.Resource;

public interface DownloadPdfService {
    public Resource getPdf(String fileName) throws Exception;
}
