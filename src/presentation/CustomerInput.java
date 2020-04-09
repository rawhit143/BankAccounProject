package presentation;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import control.Customer;
import downLayer.CustomerDbInteraction;
public class CustomerInput {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int password=0;
		Scanner kb=new Scanner(System.in);
		System.out.println("                  Welcome to RawDhana Bank \n\n");
		System.out.println("1, Admin Login                     2, Customer Login");
		System.out.println("\nEnter Your Choice = ");
		int ch=kb.nextInt();
		
		switch(ch)
		{
		case 1:
			System.out.println("Enter Admin ID : ");
			int adminId=kb.nextInt();
			System.out.println("Enter Password : ");
			password=kb.nextInt();
			String name=CustomerDbInteraction.getAdminDetails(adminId,password);
			if(name==null)
			{
				
				System.out.println("Invalid Credentials");
			}
			else
			{
				System.out.println("Hello "+name);
				
			}
			break;
		
		
		case 2:
		
		
		int accountNum=0;
		int choice=0;
		int cont=0;
		
		
		
		do {
		System.out.println("    1, Open New Account                          2, Deposit");
		System.out.println("\n    3, Get Account Details  4, Withdraw                     6, Exit");
		System.out.println("\nEnter your Choice = ");
		choice=kb.nextInt();
		if(choice==1)
		{
			accountNum=0;
		}
		switch(choice)
		{
		
		case 1:
			Customer c=new Customer();
            Random rand=new Random();		
			System.out.print("Enter your Name = ");
			c.setName(kb.next());
			c.setOpeningDate(new Date().toString().substring(0,20));
			System.out.print("Enter Opening Amount = ");
			c.setOpeningAmount(kb.nextInt());
			System.out.print("Enter Account Type = ");
			c.setAccountType(kb.next());
			System.out.print("Enter Near By Branch = ");
			c.setBranchName(kb.next());
			c.setPassword(rand.nextInt(1000000000));
			int pass=CustomerDbInteraction.newCust(c);
			if(password!=0)
			{
				System.out.println("Congo! Account Opened SuccessFully");
				System.out.println("Your temporary password : "+password);
			}
				else
				System.out.println("Account Cannot be opened please try after some time");
			break;
			
		case 2:
			if(accountNum==0)
			{
				System.out.println("\nEnter your account number= ");
				accountNum=kb.nextInt();
			}
			System.out.println("Enter password : ");
			password=kb.nextInt();
			Customer c2=CustomerDbInteraction.getAccountDetails(accountNum,password);
			
			if(c2==null)
			{
				System.out.println("Invalid Credentials");
				accountNum=0;
			}
			else
			{
			System.out.println("Hello "+c2.getName());
			System.out.println("\nEnter the amount to be deposited = ");
			int amount=kb.nextInt();
			
			boolean status1=CustomerDbInteraction.depositMoney(accountNum,amount);
			if(status1==true)
				System.out.println("Successfully Deposited!");
			else
				System.out.println("Failed!");
			}
			break;
			
			
			
			
			
		case 3:
			if(accountNum==0)
			{
				System.out.println("\nEnter your account number= ");
				accountNum=kb.nextInt();
			}
			System.out.println("Enter password : ");
			password=kb.nextInt();
			
			Customer cust=CustomerDbInteraction.getAccountDetails(accountNum,password);
			if(cust==null)
			{
				System.out.println("Invalid Credentials");
				accountNum=0;
			}
			else
			{
			System.out.println("Name : "+cust.getName());
			System.out.println("Branch Name : "+cust.getBranchName());
			System.out.println("Date of Opening : "+cust.getOpeningDate());
			System.out.println("Account Balance : "+cust.getOpeningAmount()+"/-");
			}
			break;
			
			
		case 4:
			if(accountNum==0)
			{
				System.out.println("\nEnter your account number= ");
				accountNum=kb.nextInt();
			}
			System.out.println("Enter password : ");
			 password=kb.nextInt();
			Customer c3=CustomerDbInteraction.getAccountDetails(accountNum,password);
			
			if(c3==null)
			{
				System.out.println("Invalid Credentials");
				accountNum=0;
			}
			else
			{
			System.out.println("Hello "+c3.getName());
			System.out.println("\nEnter the amount to be Withdrawn = ");
			int amount=kb.nextInt();
			if(amount>c3.getOpeningAmount())
			{
				System.out.println("Low Balance..Cannot be withdrawn");
				break;
			}
			boolean status1=CustomerDbInteraction.withdrawMoney(accountNum,amount);
			if(status1==true)
				System.out.println("Successfully Deposited!");
			else
				System.out.println("Failed!");
			}
			break;
		
		
			
		case 6:
			System.out.println("Thankyou for using RawDhana\n\nBye Bye");
			System.exit(0);
		default:
			System.out.println("Invalid Choice");
		}
		System.out.println("Do you Wanna Continue?\n\nPress\n1,Yes\n0,NO");
		
		cont=kb.nextInt();
		}while(cont!=0);
	break;
		}
	kb.close();
	}
}
