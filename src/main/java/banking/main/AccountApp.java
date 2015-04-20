package banking.main;

import java.sql.SQLException;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import banking.data.Account;
import banking.data.Customer;
import banking.view.AccountView;

public class AccountApp {
	
	public static Logger logger = LoggerFactory.getLogger(AccountApp.class);
	
	public static void main(String[] args) throws InterruptedException, SQLException {
		
		logger.info("Start MainApp class");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("config/BeanLocations.xml");
		
		AccountView accountView = (AccountView) context.getBean("accountView");
		
		accountView.showMainMenu();		
	}
}
