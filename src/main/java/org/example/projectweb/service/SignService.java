package org.example.projectweb.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignService {
    public void genSig(String filePath, String privateKeyPath) throws NoSuchAlgorithmException, InvalidKeyException,
            InvalidKeySpecException, IOException, SignatureException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(readPrivateKey(privateKeyPath));

        FileInputStream fis = new FileInputStream(filePath);
        BufferedInputStream bufin = new BufferedInputStream(fis);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bufin.read(buffer)) != -1) {
            sig.update(buffer, 0, len);
        }
        bufin.close();
        byte[] realSig = sig.sign();

        // save sig
        FileOutputStream sigFos = new FileOutputStream(filePath + ".sig");
        sigFos.write(realSig);
        sigFos.close();
    }

    public boolean verifySign(String filePath, String sigPath, String pubKey) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(readPublicKey(pubKey));
        FileInputStream fileFis = new FileInputStream(filePath);
        BufferedInputStream bufin = new BufferedInputStream(fileFis);

        byte[] buffer = new byte[1024];
        int len;
        while ((len = bufin.read(buffer)) != -1) {
            sig.update(buffer, 0, len);
        }
        bufin.close();

        FileInputStream sigFis = new FileInputStream(sigPath);
        byte[] sigToVerify = new byte[sigFis.available()];
        sigFis.read(sigToVerify);
        sigFis.close();

        return sig.verify(sigToVerify);
    }

    public PrivateKey readPrivateKey(String path)
            throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String str = new String(bytes);
        byte[] decoded = Base64.getDecoder().decode(str);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }

    public PublicKey readPublicKey(String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String str = new String(bytes);
        byte[] decoded = Base64.getDecoder().decode(str);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(keySpec);
    }
}
