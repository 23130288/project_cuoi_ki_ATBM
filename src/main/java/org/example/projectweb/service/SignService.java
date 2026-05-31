package org.example.projectweb.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;

public class SignService {
    public boolean verifySign(PublicKey pubKey, String sigPath, byte[] contentBytes) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        byte[] sigToVerify = Files.readAllBytes(Path.of(sigPath));
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(pubKey);
        sig.update(contentBytes);
        return sig.verify(sigToVerify);
    }
}
