package easy.gui;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import easy.model.Product;

public class ProductTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;
	private static final int TAG_COL = 0;
	private static final int NAME_COL = 1;
	private static final int COMPANY_COL = 2;
	private static final int CPRICE_COL = 3;
	private static final int SPRICE_COL = 4;
	private static final int PROFIT_COL = 5;
	private static final int STATUS_COL = 6;
	DecimalFormat df = new DecimalFormat("#.00");

	private String[] columnName = { "Tag Number", "Item Name", "Item Brand", "Cost Price", "Selling Price", "Profit",
			"ST" };
	private ArrayList<Product> products;

	public ProductTableModel(ArrayList<Product> products) {
		this.products = products;
	}

	@Override
	public int getColumnCount() {
		return columnName.length;
	}

	@Override
	public int getRowCount() {
		return products.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnName[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Product tmpProduct = products.get(row);
		switch (col) {
		case TAG_COL:
			return tmpProduct.getTag();
		case NAME_COL:
			return tmpProduct.getName();
		case COMPANY_COL:
			return tmpProduct.getCompany();
		case CPRICE_COL:
			return df.format(tmpProduct.getCp());
		case SPRICE_COL:
			return df.format(tmpProduct.getSp());
		case PROFIT_COL:
			return df.format(tmpProduct.getProfit());
		case STATUS_COL:
			if (tmpProduct.getStatus()) {
				return "avaible";
			}

			else {
				return "Sold";
			}
		case OBJECT_COL:
			return tmpProduct;

		default:
			break;
		}
		return null;
	}

}
