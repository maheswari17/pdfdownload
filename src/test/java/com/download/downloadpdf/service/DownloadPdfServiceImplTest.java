package com.download.downloadpdf.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DownloadPdfServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DownloadPdfServiceImplTest {
    @Autowired
    private DownloadPdfServiceImpl downloadPdfServiceImpl;

    @Test
    void testGetPdf() throws Exception {
        assertThrows(FileNotFoundException.class, () -> this.downloadPdfServiceImpl.getPdf("udemy certificate.pdf"));
    }


    @Test
    void testGetPdf2() throws Exception {
        Resource actualPdf = this.downloadPdfServiceImpl.getPdf("");
        String expectedDescription = String.join("", "URL [file:",
                Paths.get(System.getProperty("user.home"), "Downloads", "udemy%20certificate.pdf]").toString());
        assertEquals(expectedDescription, actualPdf.getDescription());
        assertTrue(actualPdf.isFile());
    }
}

