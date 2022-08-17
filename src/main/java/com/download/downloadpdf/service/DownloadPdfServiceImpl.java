package com.download.downloadpdf.service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Component
public class DownloadPdfServiceImpl implements DownloadPdfService {
    private final Path fileStorageLocation = Paths.get("/home/nineleaps/Downloads/udemy certificate.pdf");

    @Override
    public Resource getPdf(String fileName) throws Exception {

        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

    }

