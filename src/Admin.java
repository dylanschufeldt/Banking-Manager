
//instances of admin users can delete other users
public class Admin extends User {

	public Admin(String username) {
		super(username);
	}

	public boolean isValidAccount(int index) {
		System.out.println("Administrators don't have bank accounts.");
		return false;
	}

	

	public void deposit(int index, int amount) {
		System.out.println("Administrators don't have bank accounts.");
		return;

	}


	public boolean withdraw(int index, int amount) {
		System.out.println("Administrators don't have bank accounts.");
		return false;

	}


	public void openAccount() {
		System.out.println("Administrators don't have bank accounts.");
		return;

	}


	public void displayAccounts() {
		System.out.println("Administrators don't have bank accounts.");
		return;

	}


	public int getBalance(int index) {
		System.out.println("Administrators don't have bank accounts.");
		return 0;
	}

}
