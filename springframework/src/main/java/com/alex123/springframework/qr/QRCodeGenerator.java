package com.alex123.springframework.qr;


import com.alex123.springframework.shopcart.entity.Customer;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class QRCodeGenerator {

    public static void generateQRCode(Customer customer) throws WriterException, IOException {
        String qrCodePathFile = "C:\\Users\\Alex\\Desktop\\Spring  Customer ShopCart\\qrCode";
        String qrCodeName = qrCodePathFile + customer.getName() + "-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode("Name - " + customer.getName() + '\n' +
                " Email - " + customer.getEmail() + '\n' + " Gender - " + customer.getGender() + '\n' +
                " PIN - " + customer.getId()+ '\n', BarcodeFormat.QR_CODE, 300, 300);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

}
