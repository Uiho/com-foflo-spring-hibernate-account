package banking.dao;

import javax.annotation.PostConstruct;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AccountHibernateDaoSupport extends HibernateDaoSupport {
	
	@Autowired
	private DataSource dataSource;
	
	protected JdbcTemplate jdbcTemplate;

	
	@PostConstruct  
	public void initialize(){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Inside @PostConstruct after bean initialization");
	}
	
	@Autowired
    public void anyMethodName(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }
}
