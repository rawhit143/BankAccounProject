package control;
import java.util.Date;
public class Customer {
	
	public Customer()
	{}
public Customer(String name, String openingDate, Integer openingAmount, String accountType, String branchName) {
		
		this.name = name;
		this.openingDate = openingDate;
		this.openingAmount = openingAmount;
		this.accountType = accountType;
		this.branchName = branchName;
	}
private int accountNum;
private  String name;
private String openingDate;
private Integer openingAmount;
private String accountType;
private String branchName;
private int password;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getOpeningDate() {
	return openingDate;
}
public void setOpeningDate(String openingDate) {
	this.openingDate = openingDate;
}
public Integer getOpeningAmount() {
	return openingAmount;
}
public void setOpeningAmount(Integer openingAmount) {
	this.openingAmount = openingAmount;
}
public String getAccountType() {
	return accountType;
}
public void setAccountType(String accountType) {
	this.accountType = accountType;
}
public String getBranchName() {
	return branchName;
}
public void setBranchName(String branchName) {
	this.branchName = branchName;
}
public int getAccountNum() {
	return accountNum;
}
@Override
public String toString() {
	return "Customer [accountNum=" + accountNum + ", name=" + name + ", openingDate=" + openingDate + ", openingAmount="
			+ openingAmount + ", accountType=" + accountType + ", branchName=" + branchName + "]";
}
public void setPassword(int password) {
	this.password = password;
}
public int getPassword() {
	return password;
}
}
