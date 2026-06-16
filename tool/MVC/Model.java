package MVC;

public class Model {
	public String EncryptionFile(String input, String key) throws Exception {
		String re = bao_mat.Signature.signature(input, key);
		return re;
	}

	public void genKey() throws Exception {
		bao_mat.CreatKey.genKey(4096, "RSA");
	}

	public String getKey(String folder, boolean isPublicKey) throws Exception {
		return bao_mat.CreatKey.getKeyFile(folder, isPublicKey);
	}
}
