package MVC;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel TextFileP, stringPanel, inputP1, inputP2, outputP1, outputP2, privateKeyP1, privateKeyP2,
			privateKeyP3, privateKeyP4, publicKeyP1, publicKeyP2, folderP1, folderP2, btP1, btP2;
	private JLabel mtLb, inputLb, outputLb, privateKeyLb, privateKeyLb2, publicKeyLb, folderLb;
	private JTextField input, output, privateKey, publicKey, privateKey2, folder;
	private JButton submit, creatKey, inputFile, inputKey, copyOutout, copyPublicKey, copyPrivateKey, browseFolder, mt1,
			mt2, mt3, mt4, mt5, mt6;
	private JRadioButton text, file;
	private ButtonGroup grTextFile;
	private JPanel panal1, panal2, panal3, panal4, panal5;

	public View(Controller controller) {
		setTitle("Signature");
		setSize(750, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(2, 2));

		mt1 = new JButton();
		mt2 = new JButton();
		mt3 = new JButton();
		mt4 = new JButton();
		mt5 = new JButton();
		mt6 = new JButton();
		mt1.setVisible(false);
		mt2.setVisible(false);
		mt3.setVisible(false);
		mt4.setVisible(false);
		mt5.setVisible(false);
		mt6.setVisible(false);

		stringPanel = new JPanel(new GridLayout(5, 1));
		stringPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

		TextFileP = new JPanel(new GridLayout(1, 3));
		mtLb = new JLabel();
		text = new JRadioButton("Signature");
		file = new JRadioButton("Creat Key");
		grTextFile = new ButtonGroup();
		grTextFile.add(text);
		grTextFile.add(file);
		text.setSelected(true);
		TextFileP.add(mtLb);
		TextFileP.add(text);
		TextFileP.add(file);

		inputP1 = new JPanel(new GridLayout(1, 2));
		inputP2 = new JPanel(new GridLayout(1, 2));
		inputLb = new JLabel("Input: ");
		input = new JTextField(20);
		input.setEditable(false);
		inputFile = new JButton("File");
		inputP2.add(inputLb);
		inputP2.add(mt1);
		inputP2.add(inputFile);
		inputP1.add(inputP2);
		inputP1.add(input);

		privateKeyP1 = new JPanel(new GridLayout(1, 2));
		privateKeyP2 = new JPanel(new GridLayout(1, 2));
		privateKeyLb = new JLabel("Private key: ");
		privateKey = new JTextField(20);
		privateKey.setEditable(false);
		inputKey = new JButton("File");
		privateKeyP2.add(privateKeyLb);
		privateKeyP2.add(mt2);
		privateKeyP2.add(inputKey);
		privateKeyP1.add(privateKeyP2);
		privateKeyP1.add(privateKey);

		publicKeyP1 = new JPanel(new GridLayout(1, 2));
		publicKeyP2 = new JPanel(new GridLayout(1, 3));
		publicKeyLb = new JLabel("Public key: ");
		copyPublicKey = new JButton("Copy");
		publicKey = new JTextField(20);
		publicKey.setEditable(false);
		publicKeyP2.add(publicKeyLb);
		publicKeyP2.add(mt4);
		publicKeyP2.add(copyPublicKey);
		publicKeyP1.add(publicKeyP2);
		publicKeyP1.add(publicKey);

		privateKeyP3 = new JPanel(new GridLayout(1, 2));
		privateKeyP4 = new JPanel(new GridLayout(1, 3));
		privateKeyLb2 = new JLabel("Private key: ");
		copyPrivateKey = new JButton("Copy");
		privateKey2 = new JTextField(20);
		privateKey2.setEditable(false);
		privateKeyP4.add(privateKeyLb2);
		privateKeyP4.add(mt5);
		privateKeyP4.add(copyPrivateKey);
		privateKeyP3.add(privateKeyP4);
		privateKeyP3.add(privateKey2);

		folderP1 = new JPanel(new GridLayout(1, 2));
		folderP2 = new JPanel(new GridLayout(1, 2));
		folderLb = new JLabel("Output folder: ");
		browseFolder = new JButton("Browse");
		folder = new JTextField(20);
		folder.setEditable(false);
		folderP2.add(folderLb);
		folderP2.add(mt6);
		folderP2.add(browseFolder);
		folderP1.add(folderP2);
		folderP1.add(folder);

		outputP1 = new JPanel(new GridLayout(1, 2));
		outputP2 = new JPanel(new GridLayout(1, 2));
		outputLb = new JLabel("Output: ");
		output = new JTextField(20);
		output.setEditable(false);
		copyOutout = new JButton("Copy");
		outputP2.add(outputLb);
		outputP2.add(mt3);
		outputP2.add(copyOutout);
		outputP1.add(outputP2);
		outputP1.add(output);

		btP1 = new JPanel(new GridLayout(1, 21));
		btP1.setBorder(BorderFactory.createEmptyBorder(10, 150, 0, 150));
		submit = new JButton("Submit");
		btP1.add(submit);

		btP2 = new JPanel(new GridLayout(1, 21));
		btP2.setBorder(BorderFactory.createEmptyBorder(10, 150, 0, 150));
		creatKey = new JButton("Creat Key");
		btP2.add(creatKey);

		Font fontLabel = new Font("Arial", Font.BOLD, 16);
		text.setFont(fontLabel);
		file.setFont(fontLabel);
		inputLb.setFont(fontLabel);
		outputLb.setFont(fontLabel);
		publicKeyLb.setFont(fontLabel);
		privateKeyLb.setFont(fontLabel);
		privateKeyLb2.setFont(fontLabel);
		folderLb.setFont(fontLabel);

		Font fontBottom1 = new Font("Arial", Font.BOLD, 14);
		submit.setFont(fontBottom1);
		inputFile.setFont(fontBottom1);
		inputKey.setFont(fontBottom1);
		creatKey.setFont(fontBottom1);

		Font fontBottom2 = new Font("Arial", Font.BOLD, 12);
		copyOutout.setFont(fontBottom2);
		copyPublicKey.setFont(fontBottom2);
		copyPrivateKey.setFont(fontBottom2);

		Font fontTextField = new Font("Arial", Font.ITALIC, 16);
		input.setFont(fontTextField);
		output.setFont(fontTextField);
		privateKey.setFont(fontTextField);
		privateKey2.setFont(fontTextField);
		publicKey.setFont(fontTextField);
		folder.setFont(fontTextField);

		panal1 = new JPanel(new GridLayout(1, 1));
		panal2 = new JPanel(new GridLayout(1, 1));
		panal3 = new JPanel(new GridLayout(1, 1));
		panal4 = new JPanel(new GridLayout(1, 1));
		panal5 = new JPanel(new GridLayout(1, 1));

		panal1.add(TextFileP);
		panal2.add(privateKeyP1);
		panal3.add(inputP1);
		panal4.add(outputP1);
		panal5.add(btP1);

		stringPanel.add(panal1);
		stringPanel.add(panal2);
		stringPanel.add(panal3);
		stringPanel.add(panal4);
		stringPanel.add(panal5);

		add(stringPanel);
		setVisible(true);

		this.text.addActionListener(e -> updatePanal());
		this.file.addActionListener(e -> updatePanal());
		this.copyOutout.addActionListener(e -> copyOutput());
		this.inputFile.addActionListener(e -> {
			new DragAndDropFiles(input);
		});
		this.inputKey.addActionListener(e -> {
			new DragAndDropFiles(privateKey);
		});
		browseFolder.addActionListener(e -> {
		    JFileChooser chooser = new JFileChooser();
		    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    int result = chooser.showOpenDialog(null);

		    if (result == JFileChooser.APPROVE_OPTION) {
		        File selectedFolder = chooser.getSelectedFile();
		        folder.setText(selectedFolder.getAbsolutePath());
		    }
		});
		this.submit.addActionListener(e -> {
			if ("".equals(getInput())) {
				JOptionPane.showMessageDialog(this, "vui lòng nhập đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					controller.onSubmitFile(getInput(), getPrivateKey());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(this, e1, "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.creatKey.addActionListener(e -> {
			try {
				controller.onCreatKey(getFolder());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1, "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	public void updatePanal() {
		if (file.isSelected()) {
			panal2.remove(privateKeyP1);
			panal3.remove(inputP1);
			panal4.remove(outputP1);
			panal5.remove(btP1);

			panal2.add(privateKeyP3);
			panal3.add(publicKeyP1);
			panal4.add(folderP1);
			panal5.add(btP2);
		} else {
			panal2.remove(privateKeyP3);
			panal3.remove(publicKeyP1);
			panal4.remove(folderP1);
			panal5.remove(btP2);

			panal2.add(privateKeyP1);
			panal3.add(inputP1);
			panal4.add(outputP1);
			panal5.add(btP1);
		}

		stringPanel.revalidate();
		stringPanel.repaint();
	}

	public void copyOutput() {
		output.selectAll();
		output.copy();
	}

	public String getInput() {
		return input.getText();
	}

	public String getPrivateKey() {
		return privateKey.getText();
	}

	public String getFolder() {
		return folder.getText();
	}

	public void setInput(String re) {
		input.setText(re);
	}

	public void setOutput(String re) {
		output.setText(re);
	}

	public void setPublicKey(String re) {
		privateKey2.setText(re);
	}

	public void setPrivateKey(String re) {
		publicKey.setText(re);
	}
}

class DragAndDropFiles extends JFrame {
	JPanel panel;
	JLabel label;

	public DragAndDropFiles(JTextField input) {
		setTitle("Drag And Drop Files");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		panel = new JPanel(new BorderLayout());
		label = new JLabel("Drop File Here");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 24));
		panel.add(label, BorderLayout.CENTER);
		panel.setTransferHandler(new TransferHandler() {
			@Override
			public boolean canImport(TransferSupport support) {
				// TODO Auto-generated method stub
				return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
			}

			@Override
			public boolean importData(TransferSupport support) {
				try {
					List<File> files = (List<File>) support.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);
					if (files.isEmpty() || files.size() > 1) {
						JOptionPane.showMessageDialog(DragAndDropFiles.this, "vui lòng chỉ nhập 1 file", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
						return false;
					}
					File file = files.get(0);
					input.setText(file.getAbsolutePath());
					dispose();
					return true;
				} catch (Exception e) {
					e.printStackTrace();
				}
				return false;
			}
		});

		add(panel);
		setVisible(true);
	}
}
