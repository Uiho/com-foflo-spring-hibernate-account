package banking.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import banking.data.Account;
import banking.data.Customer;

public interface AccountDao {
	Account saveAccount(String name, Double initialBalance);
	Map<Integer,Account>showAllAccounts();
	Account getSpeicificAccount(Integer number);
	double deposit(Integer number, Double deposit);
	double withdraw(Integer number, Double wd);
	boolean deleteAccount(Integer number);
	boolean doesAccountExists(Integer number) ;
	Customer InsertCustomer(String name);
	Account InsertAccount(Double initialBalance, Customer customer);
	double getAccountBalance(Integer number);
	String getCustomerName(Integer CustomerId);

}
