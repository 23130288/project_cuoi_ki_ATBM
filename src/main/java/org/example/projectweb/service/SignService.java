package org.example.projectweb.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;

public class SignService {
    public boolean verifySign(PublicKey pubKey, String sigPath, String inputPath) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        FileInputStream sigFis = new FileInputStream(sigPath);
        byte[] sigToVerify = new byte[sigFis.available()];
        sigFis.read(sigToVerify);
        sigFis.close();

        Signature sig = Signature.getInstance("...");
        sig.initVerify(pubKey);
        FileInputStream dataFis = new FileInputStream(inputPath);
        BufferedInputStream bufin = new BufferedInputStream(dataFis);

        byte[] buffer = new byte[1024];
        int len;
        while ((len = bufin.read(buffer)) != -1) {
            sig.update(buffer, 0, len);
        }
        return sig.verify(sigToVerify);
    }
}
