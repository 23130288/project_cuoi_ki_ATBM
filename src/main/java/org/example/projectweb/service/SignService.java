package org.example.projectweb.service;

import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SignService {
    public boolean verifySign(String filePath, String signature, String pubKeyStr) throws Exception {
        // original file
        String hashFile = hashFile(filePath);
        // hash signature
        PublicKey publicKey = readPublicKey(pubKeyStr);
        String hashSignature = decrypt(signature, publicKey);

        return hashFile.equals(hashSignature);
    }

    public String decrypt(String base64, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        byte[] in = Base64.getDecoder().decode(base64);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] out = cipher.doFinal(in);
        String dataOutBase64 = new String(out, StandardCharsets.UTF_8);
        return dataOutBase64;
    }

    public String hashFile(String filePath) throws NoSuchAlgorithmException, FileNotFoundException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        InputStream is = new BufferedInputStream(new FileInputStream(filePath));
        try (DigestInputStream dis = new DigestInputStream(is, digest)) {
            byte[] buff = new byte[1024];
            int read;
            do {
                read = dis.read(buff);
            } while (read != -1);
            BigInteger number = new BigInteger(1, dis.getMessageDigest().digest());
            return number.toString(16);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PublicKey readPublicKey(String pubKeyStr) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decoded = Base64.getDecoder().decode(pubKeyStr);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(keySpec);
    }
}
