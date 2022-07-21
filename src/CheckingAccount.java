
public class CheckingAccount extends Account {

	public CheckingAccount()
	{
		super("Checking");
		System.out.println("checking account created");
	}
	public CheckingAccount(Integer balance)
	{
		super(balance,"Checking");
		System.out.println("checking account created");
	}
	
	public boolean withdraw(Integer amount)
	{
		if(isActive() == false)
			System.out.println("Amount must be active");
		else {
			boolean withdrawn = super.withdraw(amount);
			System.out.println("current balance: " + getBalance());
			return withdrawn;
		}
		return false;
	}
}
