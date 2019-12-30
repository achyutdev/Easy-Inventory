package easy.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import easy.dao.ProductDAO;
import easy.model.Product;
import easy.util.SecureRandomTagGen;

public class AddNewProductItem extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField productNameTextField;
	private JTextField brandTextField;
	private JTextField tagNumtextField;
	private JTextField cpTextField;
	private JSlider profitMargin;
	private JPanel addTitleField;

	private String tagNum;

	private ProductDAO productdao;
	private ProductSearch productsearch;

	private Product previousProduct = null;
	private boolean updateMode = false;

	private JTextField sptextField;

	public AddNewProductItem(ProductDAO productdao, ProductSearch productsearch, Product previousProduct,
			boolean updateMode) {
		this();
		this.productdao = productdao;
		this.productsearch = productsearch;
		this.previousProduct = previousProduct;
		this.updateMode = updateMode;

		if (updateMode) {
			setTitle("Update Item");
			populateGui(previousProduct);

			//
			JLabel lblAddNewItem = new JLabel("   Update Product Details");
			lblAddNewItem.setFont(new Font("Segoe Script", Font.BOLD, 18));
			lblAddNewItem
					.setIcon(new ImageIcon("rsz_update-icon.png"));
			addTitleField.add(lblAddNewItem);
		} else {
			JLabel lblAddNewItem = new JLabel("   Add Product Details");
			lblAddNewItem.setFont(new Font("Segoe Script", Font.BOLD, 18));
			lblAddNewItem
					.setIcon(new ImageIcon("rsz_1add-item-icon.png"));
			addTitleField.add(lblAddNewItem);
		}
	}

	public AddNewProductItem(ProductDAO productdao, ProductSearch productsearch) {
		this(productdao, productsearch, null, false);
	}

	private void populateGui(Product previousProduct) {
		productNameTextField.setText(previousProduct.getName());
		brandTextField.setText(previousProduct.getCompany());
		cpTextField.setText(previousProduct.getCp() + "");
		sptextField.setText(previousProduct.getSp() + "");
		tagNumtextField.setText(previousProduct.getTag());
		profitMargin.setValue(getProfitPercentage(previousProduct.getSp(), previousProduct.getCp()));
	}

	private int getProfitPercentage(double d, double e) {

		return (int) ((d - e) * 100 / e);
	}

	public AddNewProductItem() {
		setTitle("Add New Item");
		setBounds(100, 100, 450, 448);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		addTitleField = new JPanel();

		JPanel inputDetailsPanel = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(47)
						.addComponent(addTitleField, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE).addGap(58))
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(23)
						.addComponent(inputDetailsPanel, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(31, Short.MAX_VALUE)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addComponent(addTitleField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(inputDetailsPanel, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE).addGap(7)));

		JLabel lblproductName = new JLabel("Product Name");

		productNameTextField = new JTextField();
		productNameTextField.setColumns(10);

		JLabel lblBrandName = new JLabel("Brand Name");

		brandTextField = new JTextField();
		brandTextField.setColumns(10);

		JLabel lblTagNumber = new JLabel("Tag Number");

		tagNumtextField = new JTextField();
		tagNumtextField.setColumns(10);

		JButton btnGenerate = new JButton("Generate");

		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecureRandomTagGen test = new SecureRandomTagGen();
				tagNum = test.getTagNumber().toUpperCase();
				tagNumtextField.setText(tagNum);
			}

		});

		JLabel lblPrice = new JLabel("Cost Price");

		cpTextField = new JTextField();
		cpTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpTextField.setBorder(null);
			}
		});
		cpTextField.setColumns(10);

		JLabel labelPersentage = new JLabel("0%");

		profitMargin = new JSlider();
		profitMargin.setValue(0);
		profitMargin.setMinimum(-100);
		profitMargin.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					int percentage = profitMargin.getValue();
					double costp = Double.parseDouble(cpTextField.getText());
					labelPersentage.setText(String.valueOf(profitMargin.getValue()) + "%");
					sptextField.setText(String.valueOf((double) (costp + costp * percentage * 0.01)));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(productsearch, "Invalid Cost Price.", "Error",
							JOptionPane.ERROR_MESSAGE);
					cpTextField.setBorder(BorderFactory.createLineBorder(Color.red));
				}

			}
		});

		JLabel lblProfitMargin = new JLabel("Profit Margin");

		JLabel lblSellingPrice = new JLabel("Selling Price");

		sptextField = new JTextField();
		sptextField.setColumns(10);

		GroupLayout gl_inputDetailsPanel = new GroupLayout(inputDetailsPanel);
		gl_inputDetailsPanel.setHorizontalGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputDetailsPanel.createSequentialGroup().addContainerGap().addGroup(gl_inputDetailsPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inputDetailsPanel
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_inputDetailsPanel.createSequentialGroup()
												.addGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblproductName).addComponent(lblBrandName)
														.addComponent(lblTagNumber))
												.addGap(18))
										.addGroup(
												gl_inputDetailsPanel.createSequentialGroup()
														.addComponent(lblPrice, GroupLayout.DEFAULT_SIZE, 71,
																Short.MAX_VALUE)
														.addGap(27)))
								.addGroup(gl_inputDetailsPanel.createSequentialGroup().addComponent(lblProfitMargin)
										.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(
								gl_inputDetailsPanel.createSequentialGroup().addComponent(lblSellingPrice).addGap(28)))
						.addGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(sptextField, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
								.addComponent(brandTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 246,
										Short.MAX_VALUE)
								.addComponent(productNameTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 246,
										Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_inputDetailsPanel.createSequentialGroup()
										.addComponent(tagNumtextField, GroupLayout.PREFERRED_SIZE, 149,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnGenerate))
								.addComponent(cpTextField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 246,
										Short.MAX_VALUE)
								.addGroup(gl_inputDetailsPanel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(profitMargin, GroupLayout.PREFERRED_SIZE, 180,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(labelPersentage,
												GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
										.addGap(8)))
						.addGap(12)));
		gl_inputDetailsPanel.setVerticalGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputDetailsPanel.createSequentialGroup().addGap(33)
						.addGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblproductName).addComponent(productNameTextField,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.LEADING).addComponent(lblBrandName)
								.addComponent(brandTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTagNumber)
								.addComponent(tagNumtextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGenerate))
						.addGap(18)
						.addGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.LEADING).addComponent(lblPrice)
								.addComponent(cpTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(labelPersentage, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblProfitMargin).addComponent(profitMargin, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(18)
						.addGroup(gl_inputDetailsPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSellingPrice).addComponent(sptextField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(21, Short.MAX_VALUE)));
		inputDetailsPanel.setLayout(gl_inputDetailsPanel);
		{
			// JLabel lblAddNewItem = new JLabel(" Add Product Details");
			// lblAddNewItem.setFont(new Font("Segoe Script", Font.BOLD, 18));
			// lblAddNewItem
			// .setIcon(new
			// ImageIcon("C:\\Users\\Dev\\workspace\\InventoryMgtSys\\lib\\rsz_1add-item-icon.png"));
			// addTitleField.add(lblAddNewItem);
		}
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton addButton = new JButton("Add");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						addProductDetailInDB();

					}

					// public void errorHandler(JTextField field, String str){
					// JOptionPane.showMessageDialog(null, "Invalid Input
					// "+str+".");
					// cpTextField.setBorder(BorderFactory.createLineBorder(Color.red));
					// }
					public void addProductDetailInDB() {
						String productName = productNameTextField.getText();
						String productTagNum = tagNum;
						String productBrand = brandTextField.getText();
						double cprice = getPriceDoubleValue(cpTextField);
						double sprice = getPriceDoubleValue(sptextField);
						boolean status = true;
						// int id =

						Product tmpProduct = null;
						if (updateMode) {
							tmpProduct = previousProduct;
							tmpProduct.setName(productName);
							tmpProduct.setCompany(productBrand);
							tmpProduct.setTag(productTagNum);
							tmpProduct.setCp(cprice);
							tmpProduct.setSp(sprice);

						} else {
							tmpProduct = new Product(productTagNum, productName, productBrand, cprice, sprice, status);
						}
						try {
							// save to db
							if (updateMode) {
								productdao.UpdateProduct(tmpProduct);
							} else {
								productdao.addProduct(tmpProduct);
							}
							// closing dialog box
							setVisible(false);
							dispose();

							// refresh panel
							productsearch.refreshProductView();

							// show saved message
							JOptionPane.showMessageDialog(productsearch, "Product added Successfully.",
									"New Product Added", JOptionPane.INFORMATION_MESSAGE);

						} catch (Exception e) {
							JOptionPane.showMessageDialog(productsearch, "Error Saving Product Details.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					}

					protected double getPriceDoubleValue(JTextField field) {
						try {
							return Double.parseDouble(field.getText());
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Invalid Input price.");
							field.setBorder(BorderFactory.createLineBorder(Color.red));
						}
						return 0;
					}

				});
				addButton.setActionCommand("OK");
				buttonPane.add(addButton);
				getRootPane().setDefaultButton(addButton);

			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

		}

	}

}
