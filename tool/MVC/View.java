package MVC;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class View extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel stringPanel, inputP1, inputP2, outputP1, outputP2, privateKeyP1, privateKeyP2, btP;
	private JPanel panal7, panal8, panal9, panal10;
	private JLabel inputLb, outputLb, privateKeyLb;
	private JTextField input, output, privateKey;
	private JButton submit, inputFile, inputKey, copyOutout, mt1, mt2, mt3;

	public View(Controller controller) {
		setTitle("Signature");
		setSize(700, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout(2, 2));

		mt1 = new JButton();
		mt2 = new JButton();
		mt3 = new JButton();
		mt1.setVisible(false);
		mt2.setVisible(false);
		mt3.setVisible(false);

		stringPanel = new JPanel(new GridLayout(4, 1));
		stringPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

		inputP1 = new JPanel(new GridLayout(1, 2));
		inputP2 = new JPanel(new GridLayout(1, 2));
		inputLb = new JLabel("Input: ");
		input = new JTextField(20);
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
		inputKey = new JButton("File");
		privateKeyP2.add(privateKeyLb);
		privateKeyP2.add(mt2);
		privateKeyP2.add(inputKey);
		privateKeyP1.add(privateKeyP2);
		privateKeyP1.add(privateKey);

		outputP1 = new JPanel(new GridLayout(1, 2));
		outputP2 = new JPanel(new GridLayout(1, 2));
		outputLb = new JLabel("Output: ");
		output = new JTextField(20);
		copyOutout = new JButton("Copy");
		output.setEditable(false);
		outputP2.add(outputLb);
		outputP2.add(mt3);
		outputP2.add(copyOutout);
		outputP1.add(outputP2);
		outputP1.add(output);

		btP = new JPanel(new GridLayout(1, 21));
		btP.setBorder(BorderFactory.createEmptyBorder(10, 150, 0, 150));
		submit = new JButton("Submit");
		btP.add(submit);

		Font fontLabel = new Font("Arial", Font.BOLD, 16);
		inputLb.setFont(fontLabel);
		outputLb.setFont(fontLabel);
		privateKeyLb.setFont(fontLabel);

		Font fontBottom = new Font("Arial", Font.BOLD, 14);
		submit.setFont(fontBottom);
		inputFile.setFont(fontBottom);
		copyOutout.setFont(fontBottom);

		Font fontTextField = new Font("Arial", Font.ITALIC, 16);
		input.setFont(fontTextField);
		output.setFont(fontTextField);
		privateKey.setFont(fontTextField);

		panal7 = new JPanel(new GridLayout(1, 1));
		panal8 = new JPanel(new GridLayout(1, 1));
		panal9 = new JPanel(new GridLayout(1, 1));
		panal10 = new JPanel(new GridLayout(1, 1));

		panal7.add(privateKeyP1);
		panal8.add(inputP1);
		panal9.add(outputP1);
		panal10.add(btP);

		stringPanel.add(panal7);
		stringPanel.add(panal8);
		stringPanel.add(panal9);
		stringPanel.add(panal10);

		add(stringPanel);
		setVisible(true);

		this.copyOutout.addActionListener(e -> copyOutput());
		this.inputFile.addActionListener(e -> {
			new DragAndDropFiles(input);
		});
		this.inputKey.addActionListener(e -> {
			new DragAndDropFiles(privateKey);
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

	public void setInput(String re) {
		input.setText(re);
	}

	public void setOutput(String re) {
		output.setText(re);
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
