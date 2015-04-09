package banking.service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.TransactionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import banking.dao.AccountDao;
import banking.data.Account;
import banking.view.AccountView;

@Component
public class AccountServiceImpl implements AccountService{
	
	private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	
	@Autowired
	private AccountDao accountDao;


	public AccountServiceImpl() {
		logger.info("Inside AccountServiceImpl class Constructor");
	}
	

	
	public Account saveAccount(final String customerName, final Double initialBalance) {
		accountDao.saveAccount(customerName, initialBalance);
		return null;
	}
	
	
	public Map<Integer, Account> showAllAccounts() {
		Map<Integer,Account> accountMap = accountDao.showAllAccounts();
		Iterator it= accountMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			Account account= (Account) pair.getValue();
			showAccountDetails(account);
			it.remove();
		}
 		return null;
	}
	
	
	public String getSpecificAccount(Integer number){
		Account account = accountDao.getSpeicificAccount(number);
		showAccountDetails(account);
		return null;
	}
	

	public double deposit(Integer number, Double deposit)  {
		accountDao.deposit(number, deposit);
		return 0;
	}

	public double withdraw(Integer number, Double wd)  {
		accountDao.withdraw(number, wd);
		return 0;
	}

	public boolean deleteAccount(Integer number) {
		accountDao.deleteAccount(number);
		return false;
	}

	public boolean doesAccountExists(Integer number)  {
		accountDao.doesAccountExists(number);
		return false;
	}
	
	public void showAccountDetails(Account account){
		System.out.print(account.getAccountId()+"  ");
		System.out.print(account.getCustomer().getCustomerId()+"  ");
		System.out.print(account.getCustomer().getCustomerName()+"  ");
		System.out.println(account.getAccountBalance());
	}
	
	
}
