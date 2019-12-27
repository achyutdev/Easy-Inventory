package easy.model;


public class Product {
	
	private int id;
	private String tag;
	private String name;
	private String company;
	private double cp;
	private double sp;
	private double profit;
	private boolean status;
	
	
	public Product(int id,String tag, String name, String company, double cp, double sp, boolean status) {
		super();
		this.id = id;
		this.tag = tag;
		this.name = name;
		this.company = company;
		this.status = status;
		this.cp = cp;
		this.sp = sp;
		this.profit = sp-cp;
		
	}
	public Product(String tag, String name, String company, double cp, double sp, boolean status) {
		this(0,tag,name,company,cp,sp,status);		
	}
	
	
	
	public double getCp() {
		return cp;
	}



	public void setCp(double cp) {
		this.cp = cp;
	}



	public double getSp() {
		return sp;
	}



	public void setSp(double sp) {
		this.sp = sp;
	}



	public double getProfit() {
		return profit;
	}



	public void setProfit(double profit) {
		this.profit = profit;
	}



	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus(){
		return status;
	}
	
	@Override
	public String toString() {
		return String
				.format("Product [id=%s, Name=%s, Company=%s, Cost Price=%.2f, Selling Price=%.2f Status=%B]",
						id, name, company, cp,sp , status);
	}
	
}
