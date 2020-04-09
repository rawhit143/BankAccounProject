package downLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import control.Customer;

public class CustomerDbInteraction {
	
public static Connection establishCon() throws Exception
{
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","dhana");
		if(con.isClosed())
		return null;
	else 
		return con;
}
//String name, Date openingDate, Integer openingAmount, String accountType, String branchName
public static int newCust(Customer c) throws Exception
{
	if(CustomerDbInteraction.establishCon()==null)
	{
		
		System.out.println("Connection Failed");
		return 0;
	}
	else
	{
		System.out.println("Connection established successfully");
		Statement st=CustomerDbInteraction.establishCon().createStatement();
		ResultSet rs=st.executeQuery("select customer_account_num.nextval from dual");
		int id=0;
		while(rs.next())
		{
			id=rs.getInt(1);
		}
		String query="insert into CUSTOMER VALUES(?,?,?,?,?,?,?)";
PreparedStatement ps=CustomerDbInteraction.establishCon().prepareStatement(query);
ps.setInt(1,id);
ps.setString(2,c.getName());
ps.setString(3,c.getOpeningDate().toString());
ps.setInt(4,c.getOpeningAmount());
ps.setString(5,c.getAccountType());
ps.setString(6,c.getBranchName());
ps.setInt(7,c.getPassword());
ps.executeUpdate();

return c.getPassword();
	}
	
}
public static boolean depositMoney(int accountNum,int amount) throws Exception
{
	if(CustomerDbInteraction.establishCon()==null)
	{
		
		System.out.println("Connection Failed");
		return false;
	}
	else
	{   
		System.out.println("Connection established successfully");
		Statement st=CustomerDbInteraction.establishCon().createStatement();
		int updateCount=0;
		updateCount=st.executeUpdate("UPDATE CUSTOMER SET OPENING_AMOUNT=(OPENING_AMOUNT +"+amount+") where account_num="+accountNum);
		if(updateCount!=0)
		return true;
		else 
			return false;
	}
}



public static boolean withdrawMoney(int accountNum,int amount) throws Exception
{
	if(CustomerDbInteraction.establishCon()==null)
	{
		
		System.out.println("Connection Failed");
		return false;
	}
	else
	{   
		System.out.println("Connection established successfully");
		Statement st=CustomerDbInteraction.establishCon().createStatement();
		int updateCount=0;
		updateCount=st.executeUpdate("UPDATE CUSTOMER SET OPENING_AMOUNT=(OPENING_AMOUNT -"+amount+") where account_num="+accountNum);
		if(updateCount!=0)
		return true;
		else 
			return false;
	}
}

public static Customer getAccountDetails(int accountNum,int password) throws Exception
{
	if(CustomerDbInteraction.establishCon()==null)
	{
		
		System.out.println("Connection Failed");
		return null;
	}
	else 
	{   System.out.println("Connection established successfully");
		Statement st=CustomerDbInteraction.establishCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=st.executeQuery("Select * from customer where account_num="+accountNum+"AND password="+password );
		if(rs.next())
		{rs.beforeFirst();
			
			Customer c =new Customer();
		while(rs.next())
		{
			c.setName(rs.getString("Name"));
			c.setOpeningDate(rs.getString("date_of_opening"));
			c.setAccountType(rs.getString("ACCOUNT_TYPE"));
			c.setOpeningAmount(rs.getInt("OPENING_AMOUNT"));
			c.setBranchName(rs.getString("BRANCH_NAME"));
		}
		return c;
		}
		else
			return null;}
}
	
	public static String getAdminDetails(int adminId,int password) throws  Exception
	{
		if(CustomerDbInteraction.establishCon()==null)
		{
			
			System.out.println("Connection Failed");
			return null;
		}
		else 
		{   System.out.println("Connection established successfully");
			Statement st=CustomerDbInteraction.establishCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs=st.executeQuery("Select * from admin_details where admin_id="+adminId+"AND password="+password );
			if(rs.next())
			{rs.beforeFirst();
				
			String name=null;
			while(rs.next())
			{
				name=rs.getString("Name");
				
			}
			return name;
			}
			else
				return null;
	
		
	}
}}
