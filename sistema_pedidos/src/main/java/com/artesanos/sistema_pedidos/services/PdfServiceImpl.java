package com.artesanos.sistema_pedidos.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.itextpdf.html2pdf.HtmlConverter;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import java.util.Base64;

@Service
public class PdfServiceImpl implements PdfService {
    private TemplateEngine templateEngine;
    private final ResourceLoader resourceLoader;

    public PdfServiceImpl(TemplateEngine templateEngine, ResourceLoader resourceLoader) {
        this.templateEngine = templateEngine;
        this.resourceLoader = resourceLoader;
    }

    @Override
public byte[] crearFactura(Map<String, Object> data) {
    try {
        Resource resource = resourceLoader.getResource("classpath:static/images/artesanosFactura.jpg");
        
        byte[] imageBytes;
        try (InputStream is = resource.getInputStream()) {
            imageBytes = is.readAllBytes();
        }

        String base64 = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytes);
        data.put("logoBase64", base64);

        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process("Factura", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        
        HtmlConverter.convertToPdf(htmlContent, target);

        return target.toByteArray();
        
    } catch (IOException e) {
        throw new RuntimeException("Error al leer el logo o procesar la plantilla: " + e.getMessage());
    }
}
    @Override
    public byte[] crearComanda(Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process("Comanda", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(htmlContent, target);

        return target.toByteArray();
    }

}
