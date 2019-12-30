package easy.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import easy.dao.ProductDAO;
import easy.model.Product;

public class ProductSearch extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchField;
	private ProductDAO productdao;
	private JTable table;
	private JScrollPane scrollPane;
	private JMenuBar menuBar;
	private JMenu mnProducts;
	private JMenu mnAccount;
	private JMenuItem mntmAddNewItems;
	private JMenuItem mntmFind;
	private JMenuItem mntmDelete;
	private JMenuItem mntmExit;
	private JMenuItem mntmEditAccount;
	private JPanel reportPanel;
	private JLabel lblTotalItems;
	private JLabel label;
	private JLabel lblAvaibleItems;
	private JLabel availablela;
	private JLabel lblReport;
	private JButton btnSell;
	private JLabel soldNum;
	private JLabel investmentNum;
	private JLabel sellNum;
	private JLabel profitNum;
	private JMenuItem mntmUpdate;
//report data
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					ProductSearch frame = new ProductSearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ProductSearch() throws SQLException {
		
		try {
			productdao = new ProductDAO();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "Error:" + e1, "Error", JOptionPane.ERROR_MESSAGE);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 572);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnProducts = new JMenu("Products");
		menuBar.add(mnProducts);

		mntmAddNewItems = new JMenuItem("Add");
		mntmAddNewItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddNewProductItem dialog = new AddNewProductItem(productdao, ProductSearch.this);
				dialog.setVisible(true);
			}
		});
		mnProducts.add(mntmAddNewItems);

		mntmFind = new JMenuItem("Find");
		mntmFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Use Search Field to Find Item.");
			}
		});
		mnProducts.add(mntmFind);

		mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				get selected row
				int row = table.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(ProductSearch.this, "You must select a Product.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int response = 	JOptionPane.showConfirmDialog(ProductSearch.this,"Delete this products?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				
				if(response != JOptionPane.YES_OPTION){
					return;
				}
				
//				get selected product object
				Product tmpProduct = (Product) table.getValueAt(row, ProductTableModel.OBJECT_COL);

				try {
					productdao.deleteItem(tmpProduct.getId());
					refreshProductView();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		mntmUpdate = new JMenuItem("Update");
		mnProducts.add(mntmUpdate);
		mnProducts.add(mntmDelete);

		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnProducts.add(mntmExit);

		mnAccount = new JMenu("Account");
		menuBar.add(mnAccount);

		mntmEditAccount = new JMenuItem("Edit Account");
		mnAccount.add(mntmEditAccount);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		JLabel productLebel = new JLabel("Type Product Name");
		panel.add(productLebel);

		searchField = new JTextField();
		panel.add(searchField);
		searchField.setColumns(20);

		JButton btnNewButton = new JButton("search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String searchtext = searchField.getText();
				try {
					ArrayList<Product> items = new ArrayList<>();
					if (searchtext != null && searchtext.trim().length() > 0) {
						items = productdao.searchProduct(searchtext);
					} else {
						items = productdao.getAllProduct();
					}
					// Using table Model
					ProductTableModel model = new ProductTableModel(items);
					table.setModel(model);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(btnNewButton);

		scrollPane = new JScrollPane();

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblCopyrightReserved = new JLabel("\u00A9copyright reserved - Achyut Dev, 2015 ");
		lblCopyrightReserved.setFont(new Font("Tahoma", Font.PLAIN, 9));

		JButton btnaddNewProduct = new JButton("Add New");
		btnaddNewProduct.setForeground(new Color(0, 128, 0));
		btnaddNewProduct.setFont(new Font("Segoe Script", Font.BOLD, 13));
		btnaddNewProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddNewProductItem dialog = new AddNewProductItem(productdao, ProductSearch.this);
				dialog.setVisible(true);
			}
		});

		JButton btnUpdateItem = new JButton("Update");
		btnUpdateItem.setForeground(Color.BLUE);
		btnUpdateItem.setFont(new Font("Segoe Script", Font.BOLD, 13));
		btnUpdateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// get selected row
				int row = table.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(ProductSearch.this, "You must select a Product.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Product tmpProduct = (Product) table.getValueAt(row, ProductTableModel.OBJECT_COL);
				// System.out.println(tmpProduct);
				AddNewProductItem dialog = new AddNewProductItem(productdao, ProductSearch.this, tmpProduct, true);

				dialog.setVisible(true);
			}
		});

		JPanel panel_1 = new JPanel();

		reportPanel = new JPanel();

		lblReport = new JLabel("Report !");
		lblReport.setForeground(new Color(160, 82, 45));
		lblReport.setFont(new Font("Segoe Script", Font.BOLD, 20));
		lblReport.setIcon(new ImageIcon("rsz_billingreport.png"));

		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.RED);
		btnDelete.setFont(new Font("Segoe Script", Font.BOLD, 13));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				get selected row
				int row = table.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(ProductSearch.this, "You must select a Product.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int response = 	JOptionPane.showConfirmDialog(ProductSearch.this,"Delete this products?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				
				if(response != JOptionPane.YES_OPTION){
					return;
				}
				
//				get selected product object
				Product tmpProduct = (Product) table.getValueAt(row, ProductTableModel.OBJECT_COL);

				try {
					productdao.deleteItem(tmpProduct.getId());
					refreshProductView();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnSell = new JButton("Sell");
		btnSell.setBackground(SystemColor.inactiveCaptionBorder);
		btnSell.setFont(new Font("Arial Black", Font.BOLD, 16));
		btnSell.setForeground(new Color(34, 139, 34));
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row < 0) {
					JOptionPane.showMessageDialog(ProductSearch.this, "You must select a Product.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				int response = 	JOptionPane.showConfirmDialog(ProductSearch.this,"Sell this products?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				
				if(response != JOptionPane.YES_OPTION){
					return;
				}
				
				Product tmpProduct = (Product) table.getValueAt(row, ProductTableModel.OBJECT_COL);
				
				try {
					productdao.soldItem(tmpProduct.getId());
					refreshProductView();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSell.setIcon(new ImageIcon("rsz_sell.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(78))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(18))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnSell)
									.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
									.addComponent(btnDelete)
									.addGap(18)
									.addComponent(btnUpdateItem)
									.addGap(18)
									.addComponent(btnaddNewProduct))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblReport)
								.addComponent(reportPanel, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE))
							.addGap(30))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCopyrightReserved, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addGap(49))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnaddNewProduct, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnUpdateItem, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDelete)
								.addComponent(btnSell)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblReport)
							.addGap(3)
							.addComponent(reportPanel, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
							.addGap(10)))
					.addGap(8)
					.addComponent(lblCopyrightReserved, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		lblTotalItems = new JLabel("Total Items ");
		lblTotalItems.setForeground(UIManager.getColor("ToggleButton.darkShadow"));
		lblTotalItems.setFont(new Font("Tahoma", Font.BOLD, 14));

		// Total items
		String totalItems = productdao.getTotalItems();
		label = new JLabel(totalItems);
		label.setForeground(new Color(30, 144, 255));
		label.setFont(new Font("Segoe Script", Font.BOLD, 18));

		// Available Items
		String availableItem = productdao.getAvaiItems();
		lblAvaibleItems = new JLabel("Available Items");
		lblAvaibleItems.setForeground(UIManager.getColor("ToggleButton.darkShadow"));
		lblAvaibleItems.setFont(new Font("Tahoma", Font.BOLD, 14));

		availablela = new JLabel(availableItem);
		availablela.setBackground(new Color(255, 240, 245));
		availablela.setForeground(new Color(30, 144, 255));
		availablela.setFont(new Font("Segoe Script", Font.BOLD, 18));

		JLabel lblSoldItems = new JLabel("Sold Items");
		lblSoldItems.setForeground(UIManager.getColor("ToggleButton.darkShadow"));
		lblSoldItems.setFont(new Font("Tahoma", Font.BOLD, 14));

		String soldItemNum = productdao.getSoldItems();
		soldNum = new JLabel(soldItemNum);
		soldNum.setBackground(new Color(255, 240, 245));
		soldNum.setForeground(new Color(30, 144, 255));
		soldNum.setFont(new Font("Segoe Script", Font.BOLD, 18));

		JLabel lblTotalInvestment = new JLabel("Total Investment");
		lblTotalInvestment.setForeground(UIManager.getColor("ToggleButton.darkShadow"));
		lblTotalInvestment.setFont(new Font("Tahoma", Font.BOLD, 14));

		String investNUm = "$" + productdao.getTotalInvest();
		investmentNum = new JLabel(investNUm);
		investmentNum.setBackground(new Color(255, 240, 245));
		investmentNum.setForeground(new Color(30, 144, 255));
		investmentNum.setFont(new Font("Segoe Script", Font.BOLD, 18));

		JLabel lblTotalSales = new JLabel("Total Sales");
		lblTotalSales.setForeground(UIManager.getColor("ToggleButton.darkShadow"));
		lblTotalSales.setFont(new Font("Tahoma", Font.BOLD, 14));

		String sellingNUm = "$" + productdao.getTotalSell();
		sellNum = new JLabel(sellingNUm);
		sellNum.setBackground(new Color(255, 240, 245));
		sellNum.setForeground(new Color(30, 144, 255));
		sellNum.setFont(new Font("Segoe Script", Font.BOLD, 18));

		JLabel lblTotalProfits = new JLabel("Total Profits");
		lblTotalProfits.setForeground(UIManager.getColor("ToggleButton.darkShadow"));
		lblTotalProfits.setFont(new Font("Tahoma", Font.BOLD, 14));

		String totalprofit = "$" + productdao.getProfit();
		profitNum = new JLabel(totalprofit);
		profitNum.setBackground(new Color(255, 240, 245));
		profitNum.setForeground(new Color(30, 144, 255));
		profitNum.setFont(new Font("Segoe Script", Font.BOLD, 18));
		GroupLayout gl_reportPanel = new GroupLayout(reportPanel);
		gl_reportPanel
				.setHorizontalGroup(
						gl_reportPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_reportPanel.createSequentialGroup().addContainerGap()
												.addGroup(gl_reportPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(gl_reportPanel.createSequentialGroup()
																.addGroup(gl_reportPanel
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblTotalItems)
																		.addComponent(lblAvaibleItems)
																		.addComponent(lblSoldItems))
														.addGap(25)
														.addGroup(gl_reportPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(soldNum).addComponent(availablela)
																.addComponent(label)))
						.addGroup(gl_reportPanel.createSequentialGroup()
								.addGroup(gl_reportPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTotalInvestment).addComponent(lblTotalProfits)
										.addComponent(lblTotalSales))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_reportPanel.createParallelGroup(Alignment.LEADING).addComponent(profitNum)
										.addComponent(sellNum).addComponent(investmentNum))))
				.addContainerGap(133, Short.MAX_VALUE)));
		gl_reportPanel.setVerticalGroup(gl_reportPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_reportPanel.createSequentialGroup().addGap(42)
						.addGroup(gl_reportPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblTotalItems)
								.addComponent(label))
				.addGap(18)
				.addGroup(gl_reportPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblAvaibleItems)
						.addComponent(availablela))
				.addGap(18)
				.addGroup(gl_reportPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblSoldItems)
						.addComponent(soldNum))
				.addGap(18)
				.addGroup(gl_reportPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblTotalInvestment)
						.addComponent(investmentNum))
				.addGap(18)
				.addGroup(gl_reportPanel.createParallelGroup(Alignment.BASELINE).addComponent(sellNum)
						.addComponent(lblTotalSales)).addGap(18)
				.addGroup(gl_reportPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblTotalProfits)
						.addComponent(profitNum)).addContainerGap(213, Short.MAX_VALUE)));
		reportPanel.setLayout(gl_reportPanel);

		JLabel lbllogoName = new JLabel(" - Easy Inventory");
		lbllogoName.setIcon(new ImageIcon("resource/rsz_2easyinlogo.png"));
		lbllogoName.setForeground(SystemColor.textHighlight);
		lbllogoName.setFont(new Font("Segoe Script", Font.BOLD, 24));
		panel_1.add(lbllogoName);
		contentPane.setLayout(gl_contentPane);
	}

	public void refreshProductView() {
		try {
			ArrayList<Product> list = productdao.getAllProduct();
			ProductTableModel mode = new ProductTableModel(list);
			table.setModel(mode);
			updateReport();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error:" + e, "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void updateReport() throws SQLException {
		label.setText(productdao.getTotalItems());
		
		availablela.setText(productdao.getAvaiItems());
		soldNum.setText(productdao.getSoldItems());
		investmentNum.setText(productdao.getTotalInvest());
		sellNum.setText(productdao.getTotalSell());
		profitNum.setText(productdao.getProfit());
		
	}
	
	
}
