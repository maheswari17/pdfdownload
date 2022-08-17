package com.download.downloadpdf.service;

//import com.download.downloadpdf.model.DownloadPdf;
import org.springframework.core.io.Resource;

public interface DownloadPdfService {
    public Resource getPdf(String fileName) throws Exception;
}
