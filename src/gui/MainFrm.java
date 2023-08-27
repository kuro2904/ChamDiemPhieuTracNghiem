package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import custom.*;

public class MainFrm {
	private JFrame frame;
	private JList<String> list_file = new JList<String>();
	private DefaultListModel<String> list_model = new DefaultListModel<String>();
	private JTextField tfSoMaDe;
	private JTextField tfSoCauHoi;
	private JTextField tfThangDiem;
	private JTextField tfMauGiayPath;
	private JTextField tfDapAnPath;
	private JTable table_FormAndData = new JTable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm window = new MainFrm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Chấm trắc nghiệm");
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setResizable(false);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1392, 1012);
		frame.getContentPane().add(tabbedPane);

		JPanel panel_DsChamBai = new JPanel();
		tabbedPane.addTab("Chấm bài", null, panel_DsChamBai, null);
		panel_DsChamBai.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 575, 722);
		panel_DsChamBai.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new TitledBorder(
						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
				"File", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 11, 555, 81);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JButton btnThemBaiCham = new JButton("Thêm bài chấm");
		btnThemBaiCham.setBounds(10, 29, 140, 40);
		panel_1.add(btnThemBaiCham);
		btnThemBaiCham.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JFileChooser dialog = new JFileChooser();
				custom.CustomFileFilter filter = new custom.CustomFileFilter(new String[] { "png", "pdf" }, "File pdf and png");
				dialog.setMultiSelectionEnabled(true);
				dialog.setFileFilter(filter);
				int result = dialog.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File[] selectedFiles = dialog.getSelectedFiles();
					if (selectedFiles.length > 0) {
						for (File file : selectedFiles) {
							list_model.addElement(file.getAbsolutePath());
						}
					}
					list_file.setModel(list_model);
				}
			}
		});

		JButton btnBoBaiCham = new JButton("Bỏ bài chấm");
		btnBoBaiCham.setBounds(190, 29, 140, 40);
		panel_1.add(btnBoBaiCham);
		btnBoBaiCham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list_file.getSelectedIndex();
				if (index < 0)
					return;
				list_model.remove(index);
				list_file.setModel(list_model);
			}
		});

		JButton btnDanhSachMoi = new JButton("Danh sách mới");
		btnDanhSachMoi.setBounds(380, 29, 140, 40);
		panel_1.add(btnDanhSachMoi);
		btnDanhSachMoi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				list_model = new DefaultListModel<String>();
				list_file.setModel(list_model);
			}
		});

		JLabel lblNewLabel = new JLabel("Tổng số bài chấm:");
		lblNewLabel.setBounds(20, 102, 391, 14);
		panel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 127, 565, 595);
		panel.add(panel_2);
		panel_2.setLayout(null);

		list_file.setBounds(0, 0, 565, 595);
		panel_2.add(list_file);

		list_file.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String selectedItem = list_file.getSelectedValue();
				CustomImagePanel imagePanel = new CustomImagePanel(selectedItem);
				imagePanel.setBounds(585, 11, 584, 711);
				imagePanel.setVisible(true);
				panel_DsChamBai.add(imagePanel);
				panel_DsChamBai.repaint();
			}
		});

		JPanel panel_FormAndData = new JPanel();
		tabbedPane.addTab("Form và Data", null, panel_FormAndData, null);
		panel_FormAndData.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 11, 392, 140);
		panel_FormAndData.add(panel_3);
		panel_3.setLayout(null);

		JButton btnNewButton = new JButton("Soạn đáp án");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int soMade, soCauhoi;
				soMade = Integer.parseInt(tfSoMaDe.getText());
				soCauhoi = Integer.parseInt(tfSoCauHoi.getText());
				List<String> columnsName = new ArrayList<String>();
				for(int i = 0; i<soMade;i++) {
					columnsName.add(String.valueOf(i));
				}
				CustomTableModel model = new CustomTableModel(new Object[soMade][soCauhoi],columnsName);
				table_FormAndData.setModel(model);
				}
		});
		btnNewButton.setBounds(48, 30, 133, 23);
		panel_3.add(btnNewButton);

		JButton btnGhipn = new JButton("Ghi đáp án");
		btnGhipn.setBounds(48, 65, 133, 23);
		panel_3.add(btnGhipn);

		JButton btnGhiTnKhc = new JButton("Ghi tên khác");
		btnGhiTnKhc.setBounds(48, 98, 133, 23);
		panel_3.add(btnGhiTnKhc);

		JLabel lblNewLabel_1 = new JLabel("Số mã đề");
		lblNewLabel_1.setBounds(224, 34, 73, 14);
		panel_3.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Số câu hỏi");
		lblNewLabel_1_1.setBounds(224, 68, 73, 14);
		panel_3.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Thang điểm");
		lblNewLabel_1_1_1.setBounds(224, 102, 82, 14);
		panel_3.add(lblNewLabel_1_1_1);

		tfSoMaDe = new JTextField();
		tfSoMaDe.setBounds(315, 30, 65, 20);
		panel_3.add(tfSoMaDe);
		tfSoMaDe.setColumns(10);

		tfSoCauHoi = new JTextField();
		tfSoCauHoi.setColumns(10);
		tfSoCauHoi.setBounds(315, 66, 65, 20);
		panel_3.add(tfSoCauHoi);

		tfThangDiem = new JTextField();
		tfThangDiem.setColumns(10);
		tfThangDiem.setBounds(315, 100, 65, 20);
		panel_3.add(tfThangDiem);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(
				new TitledBorder(null, "Form And Answers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(414, 11, 755, 140);
		panel_FormAndData.add(panel_4);
		panel_4.setLayout(null);

		JButton btnNewButton_1 = new JButton("Nạp mẫu giấy");
		btnNewButton_1.setBounds(12, 23, 161, 44);
		panel_4.add(btnNewButton_1);

		JButton btnNapDapAn = new JButton("Nạp đáp án");

		btnNapDapAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser dialog = new JFileChooser();
				custom.CustomFileFilter filter = new custom.CustomFileFilter(new String[] { "xlsx", "xlsm", "xls", "xltx" },
						"File excel");
				dialog.setFileFilter(filter);
				int result = dialog.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getAbsolutePath();
					tfDapAnPath.setText(filePath);
					try {
						loadDataFromExcelToTable(filePath);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

			private void loadDataFromExcelToTable(String filePath) throws FileNotFoundException, IOException {
				try {
					FileInputStream fis = new FileInputStream(filePath);
					XSSFWorkbook workbook = new XSSFWorkbook(fis);
					Sheet sheet = workbook.getSheetAt(0);
					int rowCount = sheet.getPhysicalNumberOfRows();
					int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
					List<String> columnsName = new ArrayList<String>();
					for(int i  = 0; i< colCount;i++) {
						columnsName.add(String.valueOf(i));
					}
					custom.CustomTableModel cusModel = new custom.CustomTableModel(new Object[rowCount][colCount],
							columnsName);
					for (int i = 0; i < rowCount; i++) {
						for (int j = 0; j < colCount; j++) {
							switch (sheet.getRow(i).getCell(j).getCellType()) {
							case STRING:
								cusModel.setValueAt(sheet.getRow(i).getCell(j).getStringCellValue(), i, j);
								break;
							case NUMERIC:
								cusModel.setValueAt(sheet.getRow(i).getCell(j).getNumericCellValue(), i, j);
								break;
							case BOOLEAN:
								cusModel.setValueAt(sheet.getRow(i).getCell(j).getBooleanCellValue(), i, j);
								break;
							default:
								return;
							}
						}
					}
					table_FormAndData.setModel(cusModel);
					workbook.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		btnNapDapAn.setBounds(12, 79, 161, 44);
		panel_4.add(btnNapDapAn);

		tfMauGiayPath = new JTextField();
		tfMauGiayPath.setBounds(185, 30, 649, 32);
		tfMauGiayPath.setEditable(false);
		panel_4.add(tfMauGiayPath);
		tfMauGiayPath.setColumns(10);

		tfDapAnPath = new JTextField();
		tfDapAnPath.setColumns(10);
		tfDapAnPath.setBounds(185, 85, 649, 32);
		tfDapAnPath.setEditable(false);
		panel_4.add(tfDapAnPath);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 162, 491, 572);
		panel_FormAndData.add(panel_5);
		
		table_FormAndData.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(table_FormAndData);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(513, 163, 656, 571);
		panel_FormAndData.add(scrollPane);

		JPanel panel_DanhSachXong = new JPanel();
		tabbedPane.addTab("Danh sách xong", null, panel_DanhSachXong, null);

		JPanel panel_DanhSachLoi = new JPanel();
		tabbedPane.addTab("Danh sách lỗi", null, panel_DanhSachLoi, null);

		JPanel panel_About = new JPanel();
		tabbedPane.addTab("About", null, panel_About, null);

		frame.pack();
		frame.setLocationRelativeTo(null); // Center the frame on the screen
		frame.setVisible(true);
	}
}
