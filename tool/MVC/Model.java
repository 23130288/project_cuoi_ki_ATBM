package MVC;

public class Model {
	public String EncryptionFile(String input, String key) throws Exception {
		String re = bao_mat.Signature.signature(input, key);
		return re;
	}
}
