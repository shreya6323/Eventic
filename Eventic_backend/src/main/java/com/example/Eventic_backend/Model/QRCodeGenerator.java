package com.example.Eventic_backend.Model;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {
    public static byte[] generateQR(String ticketid) {
        // String text = "Hello, World!";
        // The content you want to encode into the QR code
      

        int width = 300;
        int height = 300;

        // Other options for encoding
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(ticketid, BarcodeFormat.QR_CODE, width, height, hints);

            // Convert the BitMatrix to a BufferedImage
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Save the BufferedImage to a file
            // File qrCodeFile = new File(filePath);
            // ImageIO.write(image, "png", qrCodeFile);
            // System.out.println(image);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            System.out.println("QR Code generated successfully!");
            return baos.toByteArray();
            
         

        } catch (WriterException | IOException e) {
            System.err.println("Could not generate QR Code: " + e.getMessage());
        }
        return null;
    }
}
