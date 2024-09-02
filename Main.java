package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/* Commented out to make testing easier 
		
		//enforce loggin in
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Please log in to continue :)");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		//call login function
		Login login = Login.getInstance();
		//request login information
		login.GetLoginfo();
		
		//connect to db with provided info
		DB db = DB.getConnector(login.getUser(), login.getPassword());	

		*/
		
		//txt menu
		char choice = 'q';
		char dewit;
		
		do {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Welcome to SICKO! Ready to do some CRUD?");
			System.out.println("Please type a letter to make a selection: ");
			System.out.println("T: Test something O_O\nS: Search\nU: Update a product Qty, Name, or Description\nL: Check low stock/see shopping list\nC: Create a new product\nV: View all products\nD: Delete a product\nQ: Quit");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			LowStockAlert lsa = new LowStockAlert();
			lsa.LSA();
			
			
			Scanner input = new Scanner(System.in);
			choice = input.next().charAt(0);
			
			if(choice == 't' || choice == 'T') {
				System.out.println("You got it, boss!\nTest commencing!\n...");
				Test test = new Test();
				test.TestWrite();
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n...");
				System.out.println("Test completed! Did we win??");
				}
			else if(choice == 's' || choice == 'S') {
				System.out.println("Okie dokie!");
				Search search = new Search();
				search.FindProduct();
			}
			else if(choice == 'u' || choice == 'U') {
				System.out.println("Alrighty, lets update that product!");
				Update update = new Update();
				update.UpdateProduct();
			}
			else if(choice == 'l' || choice == 'L') {
				System.out.println("WHEEEEEEEEEEEEEEEEEEEEEE");
				LowStockList lowstock = new LowStockList();
				lowstock.LowStock();
			}
			else if(choice == 'c' || choice == 'C') {
				System.out.println("Time to create a new product entry!");
				Create create = new Create();
				create.CreateNewProduct();
			}
			else if(choice == 'v' || choice == 'V') {
				System.out.println("Lemme get all that for ya...");
				Products p = new Products();
				p.GetAllProds();
				System.out.println("...\nWhew! Looks like that's everything.");
			}
			else if(choice == 'd' || choice == 'D') {
				System.out.println("🎵 Let's delete the things we don't need 🎵");
				Delete delete = new Delete();
				delete.DeleteProduct();
			}else {
				if(choice == 'q') {
					System.out.println("See ya later!");
				}else {
					System.out.println("Sorry, but you've gotta choose one of the options. :(\n...\nLet's try that again!");
				}
			}
			
		}while(choice != 'q' && choice != 'Q');
		
	}

}
