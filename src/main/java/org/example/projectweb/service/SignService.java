package org.example.projectweb.service;

import javax.crypto.Cipher;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignService {
    public boolean verifySign(String hash, String signature, String pubKeyStr) throws Exception {
        // hash signature
        PublicKey publicKey = readPublicKey(pubKeyStr);
        String hashSignature = decrypt(signature, publicKey);

        return hash.equals(hashSignature);
    }

    public String decrypt(String base64, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] in = Base64.getDecoder().decode(base64);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] out = cipher.doFinal(in);
        return new String(out, StandardCharsets.UTF_8);
    }

    private PublicKey readPublicKey(String pubKeyStr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decoded = Base64.getDecoder().decode(pubKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(keySpec);
    }
}
