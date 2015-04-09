package banking.view;



import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import banking.service.AccountService;

@Component
public class AccountView {
	private Logger logger = LoggerFactory.getLogger(AccountView.class);
	
	@Autowired
	private AccountService accountService;

	private Scanner in= new Scanner(System.in);
	
	private boolean keepbanking;
	
	public AccountView() {
		logger.info("Inside AccountView class Constructor");
		keepbanking = true;
	}
	
	
	public void showMainMenu() throws InterruptedException, SQLException{
		try{
			while(keepbanking){
				
				showMenu();
				
				while (!in.hasNext("[0-9]+")) {
					System.out.println();
					System.out.println("Only numbers are accepted. Please try again.");
					System.out.println();
					showMenu();
					in.next(); 
				}
		
				int option = in.nextInt();
					
				switch(option){
				case 1: createAccount();
					break;
				case 2: showAllAccounts();
					break;
				case 3: specificAccount();
					break;
				case 4: deposit();
					break;
				case 5: withdraw();
					break;
				case 6: deleteAccount();
					break;
				case 9: exit();
					break;
				default:
					System.out.println();
					System.out.println();
				}
			}
		}
		finally{
			in.close();
		}
	}
	
	public void createAccount(){
		System.out.println("What is your name?");
		String name = in.next();
	
		System.out.println("How much money do you want to deposit?");
				while (!in.hasNextDouble()) {
					System.out.println();
					System.out.println("Invalid input. Please check and try again.");
					System.out.println();
					in.next();
				}
		double deposit = in.nextDouble();
		
		accountService.saveAccount(name, deposit);
	}
	
	public void showAllAccounts() throws SQLException{
		accountService.showAllAccounts();
	}
	
	public void specificAccount() throws SQLException{
		System.out.println("Enter account number");
		int accountNum = in.nextInt();
		accountService.getSpecificAccount(accountNum);
	}
	
	private void deposit() throws SQLException{
		System.out.println("What is account number?");
		int accountNum = in.nextInt();
	
		System.out.println("How much money do you want to add?");
		
				while (!in.hasNextDouble()) {
					System.out.println();
					System.out.println("Invalid input. Please check and try again.");
					System.out.println();
					in.next();
				}
				
		double deposit = in.nextDouble();
		
		accountService.deposit(accountNum, deposit);
	}
	
	private void withdraw() throws SQLException{
		
		System.out.println("What is account number?");
		int accountNum = in.nextInt();
	
		System.out.println("How much money do you want to withdraw?");
				while (!in.hasNextDouble()) {
					System.out.println();
					System.out.println("Invalid input. Please check and try again.");
					System.out.println();
					in.next();
				}
		double wd = in.nextDouble();
		double totalBalance=accountService.withdraw(accountNum, wd);
		if(totalBalance<0){
			System.out.println("We can't withdraw you money. Check your total balance, and try again");
		}
		
	}
	
	private void deleteAccount() throws SQLException{
		
		System.out.println("What is account number?");
		int accountNum = in.nextInt();
	
		accountService.deleteAccount(accountNum);
	}
	
	private void exit(){
		
		keepbanking =false;
		System.out.println("Good Bye!");
	}
	
	private void showMenu(){
		
		System.out.println();
		System.out.println("  Please press the number corresponding to the option you desire:");
		System.out.println("	1 - Create a new account");
		System.out.println("	2 - List all accounts");
		System.out.println("	3 - List a specific account");
		System.out.println("	4 - Deposit");
		System.out.println("	5 - Withdraw");
		System.out.println("	6 - Delete account");
		System.out.println("	9 - Exit");
		System.out.print(">>");
	}
	
	
	
}
