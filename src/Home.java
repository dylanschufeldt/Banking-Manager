import java.util.*;

public class Home {
	//List of users that can be accessed
	public static List<User> users = new ArrayList<User>();
	public static List<String> usernames = new ArrayList<String>();
	
	//current user
	public static User user = null;
	
	public static void main(String[] args) {
		
		//read input until blank
		System.out.println("Hello, type \"help\" for menu.");
		String input = InOut.read();
		while(!input.equals(""))
		{
			readInput(input.trim());
			input = InOut.read();
		}
		InOut.closeScan();
		System.exit(1);
	}

	//read input and execute if possible
	public static void readInput(String input){
		if(input.equals("help"))
			InOut.help();
		else if(input.equals("new user"))
			newUser();
		else if(input.equals("new account"))
			newAccount();
		else if(input.equals("login"))
			login();
		else if(input.equals("logout"))
			user = null;
		else if(input.equals("make deposit"))
			makeDeposit();
		else if(input.equals("withdraw funds"))
			withdrawFunds();
		else if(input.equals("check balance"))
			checkBalance();
		else if(input.equals("delete user"))
			deleteUser();
		else if(input.equals("accrue interest"))
			accrueInterest();
		else
			InOut.invalidInput();
	}
	//create new user
	public static void newUser(){
		if(user != null)
			System.out.println("Must logout to add new user.");
		System.out.println("standard or admin");
		String type = InOut.read();
		System.out.println("Enter username");
		String username = InOut.read();
		if(usernames.contains(username))
			System.out.println("Username is taken.");
		else if(type.equals("standard")) {
			users.add(new StandardUser(username));
			usernames.add(username);
		}
		else if(type.equals("admin")) {
			users.add(new Admin(username));
			usernames.add(username);
		}
			
	}
	//add account to user
	public static void newAccount(){
		if(user == null)
			System.out.println("Must login to open account");
		else
			user.openAccount();
	}
	//attempt to login
	public static void login() {
		System.out.println("Enter username");
		String input = InOut.read();
		if(input.equals("")) {
			InOut.invalidInput();
			return;
		}
		if(user != null) {
			System.out.println("Must logout before logging in.");
			return;
		}
		for(User userSearch : users)
		{
			if(userSearch.getUsername().equals(input)) {
				user = userSearch;
				break;
			}
		}
		if(user == null) {
			System.out.println("user not found");
			return;
		}
		System.out.println("enter password");
		input = InOut.read();
		if(!user.checkPassword(input)) {
			System.out.println("invalid password");
			user = null;
			return;
		}
		System.out.println("successful login: " + user.getName());
	}
	//add funds to selected account
	public static void makeDeposit() {
		if(user == null || !(user instanceof StandardUser)) {
			System.out.println("must login to standard user");
			return;
		}
		int index = 0;
		int amount = 0;
		System.out.println("select account");
		user.displayAccounts();
		index = Integer.parseInt(InOut.read());
		if(!user.isValidAccount(index)) {
			System.out.println("invalid account selected");
		}
		else {
			System.out.println("select amount");
			amount = Integer.parseInt(InOut.read());
			if(amount <= 0)
				System.out.println("invalid amount");
			else {
				user.deposit(index,amount);
				System.out.println("deposit successful");
			}
		}
	}
	//withdraw funds from selected account
	public static void withdrawFunds() {
		if(user == null || !(user instanceof StandardUser)) {
			System.out.println("must login to standard user");
			return;
		}
		int index = 0;
		int amount = 0;
		System.out.println("enter account");
		user.displayAccounts();
		index = Integer.parseInt(InOut.read());
		if(!user.isValidAccount(index)) {
			System.out.println("invalid account selected");
		}
		else {
			System.out.println("enter amount");
			amount = Integer.parseInt(InOut.read());
			if(amount <= 0)
				System.out.println("invalid amount");
			else {
				user.withdraw(index,amount);
				System.out.println("balance: " + user.getBalance(index));
			}
		}
	}
	//display account balance
	//display balance of selected account
	public static void checkBalance() {
		if(user == null || !(user instanceof StandardUser)) {
			System.out.println("must login to standard user");
			return;
		}
		int index = 0;
		System.out.println("select account");
		user.displayAccounts();
		index = Integer.parseInt(InOut.read());
		if(!user.isValidAccount(index)) {
			System.out.println("invalid account selection");
		}
		else {
			System.out.println("balance: " + user.getBalance(index));
		}
	}
	//delete user (must be admin)
	public static void deleteUser() {
		if(user == null || !(user instanceof Admin)) {
			System.out.println("Must be logged into admin account.");
			return;
		}
		System.out.println("Select user index.");
		displayUsers();
		int index = Integer.parseInt(InOut.read());
		if(index == users.indexOf(user))
			System.out.println("Can't delete account if it is logged in");
		else {
			try {
				usernames.remove(index);
				users.remove(index);
				System.out.println("user deleted, new user list");
				displayUsers();
			}
			catch ( IndexOutOfBoundsException e ){
				System.out.println("Invalid index selection.");
			}
		}
	}
	//print all users
	public static void displayUsers() {
		users.forEach( i -> System.out.println(users.indexOf(i) + " " + i) );
	}
	//calculate new balance after interest accrues on account
	public static void accrueInterest() {
		if(user == null || !(user instanceof StandardUser)){
			System.out.println("Must log into standard user.");
			return;
		}
		//entry for account selection
		System.out.println("Select from the following accounts.");
		user.displayAccounts();
		int index = Integer.parseInt(InOut.read());
		int balance = user.getBalance(index);
		if(balance == 0) {
			System.out.println("Balance of 0 cannot accrue interest.");
			return;
		}
		//entry for compounding period
		System.out.println("Select from the following compounding periods:\n"
				+ "Monthly      - 1 \n"
				+ "Bi-monthly   - 2 \n"
				+ "Semi-anually - 6 \n"
				+ "Anually      - 12");
		int compoundPeriod = Integer.parseInt(InOut.read());
		if(compoundPeriod != 1 && compoundPeriod != 2 && compoundPeriod != 6 && compoundPeriod != 12) {
			System.out.println("Invalid selection.");
			return;
		}
		//entry for nominal interest rate
		System.out.println("Enter a nominal interest rate greater than 0% and less than 20%.");
		double rate = Double.parseDouble(InOut.read());
		if(rate < 0 || rate > 20) {
			System.out.println("Invalid entry");
			return;
		}
		//entry for years compounded
		System.out.println("Enter number of years to compound. (between 1 and 100)");
		int years = Integer.parseInt(InOut.read());
		if(years< 1 || years > 100) {
			System.out.println("Invalid entry");
			return;
		}
		
		int interest = calculateInterest(balance, compoundPeriod, rate, years);
		
		System.out.println(interest + " interested accumulated, depositing into account");
		user.deposit(index, interest);
		System.out.println("new balance: " + user.getBalance(index));
	}
	
	public static int calculateInterest(int balance, int compoundPeriod, double rate, int years) {
		double effectiveRate = Math.pow(1 + ((rate / 100) /  (double) compoundPeriod),(double)compoundPeriod) - 1;
		return (int) (balance * effectiveRate * (double) years);
	}

}
