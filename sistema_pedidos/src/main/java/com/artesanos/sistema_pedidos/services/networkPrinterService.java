package com.artesanos.sistema_pedidos.services;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.image.Bitonal;
import com.github.anastaciocintra.escpos.image.BitonalThreshold;
import com.github.anastaciocintra.escpos.image.CoffeeImageImpl;
import com.github.anastaciocintra.escpos.image.EscPosImage;
import com.github.anastaciocintra.escpos.image.RasterBitImageWrapper;
import com.github.anastaciocintra.output.TcpIpOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class networkPrinterService {

    public void printPdfToNetworkPrinter(byte[] pdfBytes, String printerIp) throws IOException {
       
        BufferedImage image = convertPdfToImage(pdfBytes);

        try (TcpIpOutputStream outputStream = new TcpIpOutputStream(printerIp, 9100)) {
            EscPos escpos = new EscPos(outputStream);

            Bitonal algorithm = new BitonalThreshold(127); 
            EscPosImage escposImage = new EscPosImage(new CoffeeImageImpl(image), algorithm);

            RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();
            
            escpos.write(imageWrapper, escposImage);
            
            escpos.feed(5); 
            escpos.cut(EscPos.CutMode.FULL);
            
            escpos.close();
        }
    }

    private BufferedImage convertPdfToImage(byte[] pdfBytes) throws IOException {
        try (PDDocument document = PDDocument.load(pdfBytes)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            return pdfRenderer.renderImageWithDPI(0, 203); 
        }
    }
}