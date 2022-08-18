package com.download.downloadpdf.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.download.downloadpdf.service.DownloadPdfService;
import com.download.downloadpdf.service.DownloadPdfServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.InputStream;
@ContextConfiguration(classes = {DownloadPdfController.class})
@ExtendWith(SpringExtension.class)
class DownloadPdfControllerTest {
    @Autowired
    private DownloadPdfController downloadPdfController;

    private MockMvc mockMvc;
    @MockBean
    private DownloadPdfServiceImpl downloadPdfService;
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(downloadPdfController).build();
        InputStream is = downloadPdfController.getClass().getClassLoader().getResourceAsStream("udemy certificate.pdf");
    }

    @Test
    void testDownloadPdf() throws Exception {

        ResponseEntity<Resource> actualDownloadPdfResult = (new DownloadPdfController(mock(DownloadPdfService.class)))
                .downloadPdf();
        assertTrue(actualDownloadPdfResult.hasBody());
        assertEquals(6, actualDownloadPdfResult.getHeaders().size());
        assertEquals(HttpStatus.OK, actualDownloadPdfResult.getStatusCode());
        Resource body = actualDownloadPdfResult.getBody();
        assertEquals(225008, ((ByteArrayResource) body).getByteArray().length);
        assertEquals("Byte array resource [resource loaded from byte array]", body.getDescription());
    }
}

