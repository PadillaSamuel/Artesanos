package com.artesanos.sistema_pedidos.services;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.EscPosConst;
import com.github.anastaciocintra.escpos.image.Bitonal;
import com.github.anastaciocintra.escpos.image.BitonalThreshold;
import com.github.anastaciocintra.escpos.image.CoffeeImageImpl;
import com.github.anastaciocintra.escpos.image.EscPosImage;
import com.github.anastaciocintra.escpos.image.RasterBitImageWrapper;
import com.github.anastaciocintra.output.TcpIpOutputStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class networkPrinterService {

    public void printPdfToNetworkPrinter(byte[] pdfBytes, String ipImpresora) throws IOException {
        try (TcpIpOutputStream outputStream = new TcpIpOutputStream(ipImpresora, 9100);
                EscPos escpos = new EscPos(outputStream)) {

            BufferedImage pageImage = convertPdfToImage(pdfBytes);

            Bitonal algorithm = new BitonalThreshold(127);
            EscPosImage escposImage = new EscPosImage(new CoffeeImageImpl(pageImage), algorithm);

            RasterBitImageWrapper imageWrapper = new RasterBitImageWrapper();
            imageWrapper.setJustification(EscPosConst.Justification.Center);

            escpos.write(imageWrapper, escposImage);

            escpos.feed(5);
            escpos.cut(EscPos.CutMode.FULL);

        } catch (Exception e) {
            throw new IOException("Fallo en la impresión: " + e.getMessage());
        }
    }

    private BufferedImage convertPdfToImage(byte[] pdfBytes) throws IOException {
        try (PDDocument document = PDDocument.load(pdfBytes)) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            return pdfRenderer.renderImageWithDPI(0, 203, ImageType.BINARY);
        }
    }
}