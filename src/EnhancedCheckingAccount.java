public class EnhancedCheckingAccount extends CheckingAccount {

	public EnhancedCheckingAccount(){
		super();
	}
	public EnhancedCheckingAccount(Integer balance){
		super(balance);
	}
	public boolean withdraw(Integer amount)
	{
		if(isActive() == false)
			System.out.println("Amount must be active");
		else { 
		System.out.println("Enhanced Checking, balance not reduced.");
		System.out.println("Current balance: " + getBalance());
		return true;
		}
		return false;
	}
}
