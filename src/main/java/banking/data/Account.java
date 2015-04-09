package banking.data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="account")
public class Account implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCOUNT_ID", unique = true, nullable = false)
	private Integer accountId;
	
	@Column(name = "ACCOUNT_BALANCE", unique = true, nullable = false)
	private Double accountBalance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
	private Customer customer;
	
	
	

	public Account() {
	}
	
	public Account(Double accountBalance) {
		super();
		this.accountBalance = accountBalance;
	}

	public Account(Double accountBalance, Customer customer) {
		super();
		this.accountBalance = accountBalance;
		this.customer = customer;
	}

	public Account(Integer accountId, Double accountBalance, Customer customer) {
		super();
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		this.customer = customer;
	}

	
	
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

	
	

	

}
