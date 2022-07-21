import java.util.Scanner;

public class InOut {
	public static Scanner scan = new Scanner(System.in);
	public static String read() {
		String input = scan.nextLine();
		if(input != null)
			return input;
		return "";
	}
	public static void closeScan() {
		scan.close();
	}
	public static void help()
	{
		System.out.println("help           -display help menu");
		System.out.println("new user       -add new user");
		System.out.println("new account    -open new account (requires user login)");
		System.out.println("login          -access existing user profile");
		System.out.println("logout         -exit user profile");
		System.out.println("make deposit   -add funds to account");
		System.out.println("withdraw funds -remove funds from account");
		System.out.println("check balance  -display balance of an account");
		System.out.println("delete user    -display balance of an account");
	}
	public static void invalidInput()
	{
		System.out.println("invalid input");
	}
	
}
