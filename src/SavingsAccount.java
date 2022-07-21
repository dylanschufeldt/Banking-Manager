
public class SavingsAccount extends Account {
	public SavingsAccount()
	{
		super("Savings");
	}
	public SavingsAccount(Integer balance)
	{
		super(balance,"Savings");
	}
	public boolean withdraw(Integer amount)
	{
		if(isActive() == false)
			System.out.println("Account must be active.");
		else 
			System.out.println("You should save your money.");
		return false;
	}
}
