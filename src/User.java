public abstract class User {
	private String name = "";
	private String email = "";
	private String password = "";
	private String username = "";
	private boolean valid = false;

	public abstract boolean isValidAccount(int index);
	public abstract boolean withdraw(int index, int amount);
	public abstract void deposit(int index, int amount);
	public abstract void openAccount();
	public abstract void displayAccounts();
	public abstract int getBalance(int index);
	
	//constructors
	public User(String username) {
		super();
		boolean valid = setFields(username);
		this.valid = valid;
	}
	
	public User(String name, String email, String password, String username){
		if(name == null || email == null || password == null || username == null)
		{
			valid = false;
			return;
		}
		this.name = name;
		this.email = email;
		this.password = password;
		this.username = username;
	}
	
	//setFields only called in User
	private boolean setFields(String username) {
		//set username
		String input = "";
		if(username.equals(""))
		{
			System.out.println("Username cannot be blank.");
			return false;
		}
		this.username = username;
		
		//set email
		System.out.println("What is your email?");
		email = InOut.read();
		if(email.equals(""))
		{
			System.out.println("Email cannot be blank.");
			return false;
		}
		System.out.println("Email confirmation sent.");
		System.out.println("Did you receive confirmation message? \"yes\" or \"no\"");
		input = InOut.read();
		if(input.equals("yes"))
			System.out.println("email confirmed");
		else if(input.equals("no"))
		{
			System.out.println("email not confirmed");
			return false;
		}
		else
		{
			InOut.invalidInput();
			return false;
		}
		
		//set name
		System.out.println("Enter name."
				+ "");
		name = InOut.read();
		if(name.equals(""))
		{
			System.out.println("Name cannot be blank.");
			return false;
		}
		
		//set password
		System.out.println("Enter password.");
		password = InOut.read();
		if(password.equals(""))
		{
			System.out.println("Password cannot be blank.");
			return false;
		}
		System.out.println("Enter your password again.");
		input = InOut.read();
		if(!password.equals(input))
		{
			System.out.println("Password cannot be blank.");
			return false;
		}
		System.out.println("User created.");
		return true;
	}
	
	public boolean checkPassword(String input) {
		if(input.equals(password))
			return true;
		return false;
	}
	//verifies if user is valid
	public boolean isValid(){
		return this.valid;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUsername() {
		return username;
	}

	public String toString() {
		return "User [" + (username != null ? "username=" + username + ", " : "") + "valid=" + valid + "]";
	}
	
}
