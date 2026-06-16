package bao_mat;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class CreatKey {
	static KeyPair keyPair;
	static PrivateKey privateKey;
	static PublicKey publicKey;

	public static String newNameFile(String folder, String name) {
		return folder + "\\" + name + ".txt";
	}

	public static boolean makeFileKey(String keyStr, String keyFile) {
		try {
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(keyFile)));
			out.writeUTF(keyStr);
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void genKey(int keySize, String name) throws Exception {
		KeyPairGenerator gen = KeyPairGenerator.getInstance(name);
		gen.initialize(keySize);
		keyPair = gen.generateKeyPair();
		publicKey = keyPair.getPublic();
		privateKey = keyPair.getPrivate();
	}

	public static String getPrivateKey() throws Exception {
		String keyString = "";
		keyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
		return keyString;
	}

	public static String getPublicKey() throws Exception {
		String keyString = "";
		keyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
		return keyString;
	}

	public static String getKeyFile(String file, boolean ispublicKey) throws Exception {
		if (ispublicKey) {
			String keyFile = newNameFile(file, "PublicKey");
			System.out.println(keyFile);

			String PublicKeyStr = getPublicKey();
			boolean check = makeFileKey(PublicKeyStr, keyFile);
			if (check)
				return keyFile;
			else
				return "Lỗi khi kí tên.";
		} else {
			String keyFile = newNameFile(file, "PrivateKey");
			System.out.println(keyFile);
			String PrivateKeyStr = getPrivateKey();
			boolean check = makeFileKey(PrivateKeyStr, keyFile);
			if (check)
				return keyFile;
			else
				return "Lỗi khi kí tên.";
		}
	}
}
