package banking.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;





import banking.data.Account;
import banking.data.Customer;
import banking.mapper.AccountMapper;


@Repository
public class AccountDaoImpl  extends AccountHibernateDaoSupport implements AccountDao {
	
	private Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);
	
	public AccountDaoImpl() {
		logger.info("Inside AccountDaoImpl class Constructor");
	}
	
	public Account saveAccount(String name, Double initialBalance) {
		Account account =InsertAccount(initialBalance,InsertCustomer(name));
		logger.info("Finished creating a bank account for " + name);
		return account;
	}

	/**
	 * ShowAllAccounts()
	 */
	public Map<Integer, Account> showAllAccounts(){
		Map<Integer,Account> accountMap= new HashMap<Integer, Account>();
		List<Integer> list = getHibernateTemplate().find("select accountId from Account");
		for(int i=0;i<list.size(); i++){
			accountMap.put(list.get(i), getSpeicificAccount(list.get(i)));
		}
		return accountMap;
	}
	
	
	/**
	 * getSpeicficAccount is in order to get a specific account by account_id inserted in by User.
	 * Return value is a Account class Object.
	 */
	public Account getSpeicificAccount(Integer number) {
		
		List<Customer> clist = getHibernateTemplate().find("select customer from Account where account_id =?",number);
		Customer customer =clist.get(0);
		
		List<Account> alist = getHibernateTemplate().find("from Account where account_id=?", number);
		Account account =alist.get(0);
		account.setCustomer(customer);
		
		return account;
	}
	

	public double deposit(Integer number, Double deposit) {
		List<Account> alist =getHibernateTemplate().find("from Account where account_id=?",number);
		Account account = alist.get(0);
		Double newBalance = account.getAccountBalance() + deposit;
		account.setAccountBalance(newBalance);
		getHibernateTemplate().update(account);
		
		return 0;
	}

	public double withdraw(Integer number, Double wd) {
		List<Account> alist =getHibernateTemplate().find("from Account where account_id=?",number);
		Account account = alist.get(0);
		Double newBalance = account.getAccountBalance() - wd;
		if(newBalance <0) return -1;
		account.setAccountBalance(newBalance);
		getHibernateTemplate().update(account);
		return 0;
	}

	public boolean deleteAccount(Integer number) {
		List<Account> alist =getHibernateTemplate().find("from Account where account_id=?",number);
		Account account = alist.get(0);
		getHibernateTemplate().delete(account);
		return false;
	}

	public boolean doesAccountExists(Integer number) {
		List<Account> alist =getHibernateTemplate().find("from Account where account_id=?",number);
		if(alist.isEmpty() == false){
			return true;
		}
		return false;
	}
	
	

	public Customer InsertCustomer(String name) {
		Customer customer = new Customer(name);
		getHibernateTemplate().save(customer);
		return customer;
	}

	public Account InsertAccount(Double initialBalance, Customer customer) {
		Account account = new Account(initialBalance,customer);
		customer.getAccounts().add(account);
		getHibernateTemplate().save(account);
		return account;
	}
	
	
	

	public double getAccountBalance(Integer number) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getCustomerName(Integer CustomerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void showAccountDetails(Account account){
		System.out.print(account.getAccountId()+"  ");
		System.out.print(account.getCustomer().getCustomerId()+"  ");
		System.out.print(account.getCustomer().getCustomerName()+"  ");
		System.out.println(account.getAccountBalance());
	}
	
	public void ShowAllAccountsByJdbc(){
		String SQL  = "SELECT account_id, Account.customer_id, customer_name, account_balance "
				+ " FROM Account "
				+ " JOIN Customer "
				+ " ON Account.customer_id = Customer.customer_id "
				+ " ORDER BY Account_id ASC"; //DESC, ASC
		List<Account> accounts = jdbcTemplate.query(SQL, new AccountMapper());
		for (Account account : accounts) {
			showAccountDetails(account);
		}
	}

	
}
