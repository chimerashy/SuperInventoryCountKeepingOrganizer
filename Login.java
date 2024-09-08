package main;

import java.util.Scanner;

public class Login {

	private static Login login;
	private String user;
	private String password;
	
	private Login() { }
	//request login information   
	public void GetLoginfo(){
		System.out.println("Enter your user name: ");
		Scanner input = new Scanner(System.in);
		user = input.nextLine();
		System.out.println("Enter your password: ");
		password = input.nextLine();
		setLogin(user, password);
	}
	
	public void setLogin(String user, String password){
		this.user = user;
		this.password = password;
	}
	
	//methods to get login info outside of Login method
	public String getUser(){
		return user;
	}
	public String getPassword(){
		return password;
	}
	
	//singleton instantiator
	public static Login getInstance() {
		if(login == null) {
			synchronized(Login.class) {
				if(login == null) {
					login = new Login();
				}
			}
		}
		return login;
	}

}

