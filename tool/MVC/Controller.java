package MVC;

import java.io.File;

import javax.swing.JOptionPane;

public class Controller {
	private View view;
	private Model model;

	public Controller(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}

	public void onCreatKey(String folder) throws Exception {
		model.genKey();
		if ("".equals(folder))
			folder = "D:";
		view.setPublicKey(model.getKey(folder, true));
		view.setPrivateKey(model.getKey(folder, false));
	}

	public void onSubmitFile(String input, String key) throws Exception {
		String re = "";
//		if (!checkFile(key, re)) {
//			view.setOutput("lỗi file key");
//			return;
//		}
//		if (!checkFile(input, re)) {
//			view.setOutput("lỗi file order");
//			return;
//		}
		re = model.EncryptionFile(input, key);
		view.setOutput(re);
	}

	public boolean checkFile(String input, String name) {
		File sFolder = new File(input);
		if (!sFolder.exists()) {
			JOptionPane.showMessageDialog(view, "Đường đẫn file " + name + "  không tồn tại.", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!sFolder.isFile()) {
			JOptionPane.showMessageDialog(view, "Đường dẫn file " + name + "  không phải file.", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
