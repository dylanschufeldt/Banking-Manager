public abstract class Account {
	//encapsulation and javaBeans
	private String accountType;
	private Integer balance = 0;
	private boolean active = true;
	
	public Account(String accountType)
	{
		super();
		setAccountType(accountType);
	}
	
	public Account(Integer Balance, String accountType)
	{
		this(accountType);
		this.balance = Balance;
	}
	
	public Integer getBalance(){
		return balance;
	}
	
	public String display() {
		return accountType + " " + balance + " active: " + active;
	}
	
	public String getAccountType(){
		return accountType;
	}
	
	public boolean isActive(){
		return this.active;
	}
	
	public void deposit(Integer amount){
		if(active == false)
			System.out.println("Account must be active");
		else if(amount <= 0)
			System.out.println("Amount must be greater than 0");
		else
			this.balance += amount;
	}
	
	public boolean withdraw(Integer amount){
		if(active == false)
			System.out.println("Amount must be active");
		else if(amount <= 0)
			System.out.println("Amount must be greater than 0");
		else if(balance < amount){
			System.out.println("Amount must be less than balance (" + this.balance+ ")");
		}
		else {
			this.balance -= amount;
			return true;
		}
		return false;
	}
	//only used in constructor
	private void setAccountType(String accountType){
		if(accountType.equals(""))
			active = false;
		this.accountType = accountType;
	}
}
