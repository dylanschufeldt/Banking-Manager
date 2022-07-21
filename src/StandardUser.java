import java.util.*;

//Inherits an abstract class and its methods
public class StandardUser extends User {
	private List<Account> accounts = new ArrayList<Account>();
	
	public StandardUser(String username){
		super(username);
	}
	public StandardUser(String name, String email, String password, String username){
		super(name, email, password, username);
	}
	//user is invalid if fields aren't filled out correctly
	public boolean isValidAccount(int index) {
		if(index < 0 || index >= accounts.size() || accounts.get(index).isActive() == false)
			return false;
		else
			return true;
	}
	//prints accounts associated with user
	public void displayAccounts() {
		int i = 0;
		for(Account allAccounts : accounts) {
			System.out.println(i++ + " " + allAccounts.display());
		}
	}
	//add funds to specified account
	public void deposit(int index, int amount) {
		accounts.get(index).deposit(amount);
	}
	//create account associated with this user
	public void openAccount(){
			String accountType = "";
			System.out.println("Enter account type. (checking, savings, enhanced checking");
			String input = InOut.read();
			if(input.equals("") || 
			 (!input.equals("checking") && 
		      !input.equals("savings")  && 
		      !input.equals("enhanced checking"))){
				InOut.invalidInput();
				return;
			}
			accountType = input;
			System.out.println("Enter starting balance");
			input = InOut.read();
			if(input.equals("")){
				InOut.invalidInput();
				return;
			}
			if(accountType.equals("checking")) {
				accounts.add(new CheckingAccount(Integer.valueOf(input)));
			}
			else if(accountType.equals("savings")) {
				accounts.add(new SavingsAccount(Integer.valueOf(input)));
			}
			else{
				accounts.add(new EnhancedCheckingAccount(Integer.valueOf(input)));
			}
			return;
		}
	//remove funds from specified account
	public boolean withdraw(int index, int amount) {
		return accounts.get(index).withdraw(amount);
	}
	//retrieve balance at specified account
	public int getBalance(int index) {
		return accounts.get(index).getBalance();
	}
	
	
}
