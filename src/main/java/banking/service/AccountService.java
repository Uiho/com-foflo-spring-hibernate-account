package banking.service;

import java.sql.SQLException;
import java.util.Map;

import banking.data.Account;


public interface AccountService {
	Account saveAccount(String customerName, Double initialBalance);
	Map<Integer,Account> showAllAccounts();
	String getSpecificAccount(Integer number);
	double deposit(Integer number, Double deposit) ;
	double withdraw(Integer number, Double wd) ;
	boolean deleteAccount(Integer number) ;
	boolean doesAccountExists(Integer number);
	

}
