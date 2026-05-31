package bao_mat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class Signature {
	public static String hashFile(String file) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		InputStream is = new BufferedInputStream(new FileInputStream(file));
		try (DigestInputStream dis = new DigestInputStream(is, digest)) {
			byte[] buff = new byte[1024];
			int read;
			do {
				read = dis.read(buff);
			} while (read != -1);
			BigInteger number = new BigInteger(1, dis.getMessageDigest().digest());
			return number.toString(16);
		}
	}

	public static PrivateKey loadPrivateKey(String keyStr, String name) throws Exception {
		byte[] decodedKey = Base64.getDecoder().decode(keyStr);
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);
		KeyFactory kf = KeyFactory.getInstance(name);
		return kf.generatePrivate(spec);
	}

	public static String encrypt(String data, String KeyStr) throws Exception {
		PrivateKey key = loadPrivateKey(KeyStr, "RSA");
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		byte[] in = data.getBytes(StandardCharsets.UTF_8);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] out = cipher.doFinal(in);
		String dataOutBase64 = Base64.getEncoder().encodeToString(out);
		return dataOutBase64;
	}

	public static PublicKey loadPublicKey(String keyStr, String name) throws Exception {
		byte[] decodedKey = Base64.getDecoder().decode(keyStr);
		X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKey);
		KeyFactory kf = KeyFactory.getInstance(name);
		return kf.generatePublic(spec);
	}

	public static String decrypt(String base64, String KeyStr) throws Exception {
		PublicKey key = loadPublicKey(KeyStr, "RSA");
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		byte[] in = Base64.getDecoder().decode(base64);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] out = cipher.doFinal(in);
		String dataOutBase64 = new String(out, StandardCharsets.UTF_8);
		return dataOutBase64;
	}

	public static boolean makeFileSignature(String signature, String signatureFile) {
		try {
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(signatureFile)));
			out.writeUTF(signature);
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static String newNameFile(String nameBefor) {
		String nameAfter = "";
		String[] words = nameBefor.split("\\\\");

		for (int i = 0; i < words.length - 1; i++) {
			nameAfter += words[i];
			nameAfter += "\\";
		}
		nameAfter += "Signature.txt";
		return nameAfter;
	}

	public static String readKey(String fKey) throws Exception {
		BufferedReader buff = new BufferedReader(
				new InputStreamReader(new FileInputStream(fKey), StandardCharsets.UTF_8));
		String key = buff.readLine();
		buff.close();
		return key;
	}

	public static String signature(String file, String fKey) throws Exception {
		String tmp = "";
		tmp = hashFile(file);
		String KeyStr = readKey(fKey);
		String signature = encrypt(tmp, KeyStr);
		String signatureFile = newNameFile(file);
		boolean check = makeFileSignature(signature, signatureFile);
		if (check)
			return signatureFile;
		else
			return "Lỗi khi kí tên.";
	}
}
