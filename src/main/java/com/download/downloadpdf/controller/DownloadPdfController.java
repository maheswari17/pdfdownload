package com.download.downloadpdf.controller;
import com.download.downloadpdf.service.DownloadPdfService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class DownloadPdfController {

   // private static final String EXTENSION=".pdf";
    private static final String LOCATION="/home/nineleaps/Downloads/udemy certificate.pdf";

    private DownloadPdfService downloadPdfService;
    public DownloadPdfController(DownloadPdfService downloadPdfService){
        this.downloadPdfService=downloadPdfService;
    }

        @GetMapping("/download")
    public ResponseEntity<Resource> downloadPdf() throws Exception {
        File file = new File(LOCATION );

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=udemycertificate.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

}