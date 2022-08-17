package com.download.downloadpdf.ControllerTest;

import com.download.downloadpdf.controller.DownloadPdfController;
import com.download.downloadpdf.service.DownloadPdfServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

@ContextConfiguration(classes = {DownloadPdfController.class})
@ExtendWith(SpringExtension.class)
public class DownloadPdfControllerTest {
    @Autowired
    private DownloadPdfController downloadPdfController;

    private MockMvc mockMvc;
    @MockBean
    private DownloadPdfServiceImpl downloadPdfService;
    private static  String LOCATION="/home/nineleaps/Downloads/udemy certificate.pdf";
    File file = new File(LOCATION );

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(downloadPdfController).build();
        InputStream is = downloadPdfController.getClass().getClassLoader().getResourceAsStream("udemy certificate.pdf");
    }
        @Test
        void testDownloadPdf () throws  Exception {
        Mockito.when(downloadPdfService.getPdf("")).thenReturn((Resource) file);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/download").contentType(MediaType.APPLICATION_OCTET_STREAM)).andExpect(MockMvcResultMatchers.status().is(200)).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
        Assert.assertEquals(2, result.getResponse().getContentAsByteArray().length);
        Assert.assertEquals("text/json", result.getResponse().getContentType());
        }
    }

