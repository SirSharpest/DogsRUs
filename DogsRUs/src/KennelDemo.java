import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class runs a Kennel
 * 
 * @author Lynda Thomas and Chris Loftus
 * @version 1.1 (16th March 2015)
 * 
 * TODO:: Error checking on everything!!
 * 
 */
public class KennelDemo {
	private String filename; // holds the name of the file
	private Kennel kennel; // holds the kennel
	private Scanner scan; // so we can read from keyboard
	
	
	/*
	 * Notice how we can make this private, since we only call from main which
	 * is in this class. We don't want this class to be used by any other class.
	 */
	private KennelDemo() {
		scan = new Scanner(System.in);
		System.out.print("Please enter the filename of kennel information: ");
		filename = scan.next();
		kennel = new Kennel();
	}

	/*
	 * initialise() method runs from the main and reads from a file
	 */
	private void initialise() {
		System.out.println("Using file " + filename);
		
		// Using try-with-resource (see my slides from session 15)
		try(FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			Scanner infile = new Scanner(br)){
			
			String kennelName = infile.nextLine();
			int kennelSize = infile.nextInt();
			infile.nextLine();
			kennel.setCapacity(kennelSize);
			
			//Quite a large change here from the 
			//base operation. 
			//Going to read in the number as num of animals
			//then if the next line is "dog" handle as normal
			//else then we handle for a cat instead (just a few line differences) 
			
			
			int animals = infile.nextInt();
			infile.nextLine();
			kennel.setName(kennelName);
			for(int i=0; i < animals; i++){
				
				
				 if(infile.nextLine().equalsIgnoreCase("dog")){
					String dogName = infile.nextLine();
					int numOwners = infile.nextInt();
					infile.nextLine();
					ArrayList<Owner> owners = new ArrayList<>();
					for(int oCount=0; oCount < numOwners; oCount++){
						String name = infile.nextLine();
						String phone = infile.nextLine();
						Owner owner = new Owner(name, phone);
						owners.add(owner);
					}
					boolean likesBones = infile.nextBoolean();
					infile.nextLine();
					boolean needsWalked = infile.nextBoolean();
					int feedsPerDay = infile.nextInt();
					infile.nextLine(); 
					String favFood = infile.nextLine();
					
					Dog dog = new Dog(dogName, owners, likesBones, needsWalked, favFood, feedsPerDay);
					kennel.addDog(dog);
				}
				
				 else{
					
					String catName = infile.nextLine();
					int numOwners = infile.nextInt();
					infile.nextLine();
					ArrayList<Owner> owners = new ArrayList<>();
					for(int oCount=0; oCount < numOwners; oCount++){
						String name = infile.nextLine();
						String phone = infile.nextLine();
						Owner owner = new Owner(name, phone);
						owners.add(owner);
					}
					boolean canShareRun = infile.nextBoolean();
					infile.nextLine();
					int feedsPerDay = infile.nextInt();
					infile.nextLine(); 
					String favFood = infile.nextLine();
					
					Cat cat = new Cat(catName, owners, canShareRun, favFood, feedsPerDay);
					kennel.addCat(cat);
					
					
					
				}

				

				
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("The file: " + " does not exist. Assuming first use and an empty file." +
		                       " If this is not the first use then have you accidentally deleted the file?");
		} catch (IOException e) {
			System.err.println("An unexpected error occurred when trying to open the file " + filename);
			System.err.println(e.getMessage());
		}
	}

	/*
	 * runMenu() method runs from the main and allows entry of data etc
	 */
	private void runMenu() {
		String response;
		
		
		do {
			printMenu();
			System.out.println("What would you like to do:");
			scan = new Scanner(System.in);
			response = scan.nextLine().toUpperCase();
			switch (response) {
			case "1":
				admitDog();
				break;
			case "2":
				admitCat();
				break;
			case "3":			
				changeKennelName();
				break;
			case "4":
				printDogsWithBones();
				break;
			case "5":
				printCatsThatShareRun();
				break;
			case "6":
				searchForDog();
			    break;
			case "7":
				searchForCat();
				break;
			case "8":
				removeDog();
				break;
			case "9":
				removeCat();
				break;
			case "10":
				setKennelCapacity();
				break;
			case "11":
				printAll();
			case "Q":
				break;
			default:
				System.out.println("Try again");
			}
		} while (!(response.equals("Q")));
	}

	/**
	 * edited to use animals rather than dogs just. 
	 */
	private void setKennelCapacity() {
		System.out.print("Enter max number of animals: ");
		int max = scan.nextInt();
		scan.nextLine();
		kennel.setCapacity(max);
	}


	
	/**
	 * prints all dogs which like bones 
	 */
	private void printDogsWithBones() {
		Dog[] dogsWithBones = kennel.obtainDogsWhoLikeBones();
		System.out.println("Dogs with bones: ");
		for (Dog d: dogsWithBones){
			System.out.println(d.toString());
		}	
	}
	
	/**
	 * 
	 */
	private void printCatsThatShareRun() {
		Cat[] cats = kennel.obtainCatsWhoCanShareRun();
		System.out.println("Cats that can share runs: ");
		for (Cat c: cats){
			System.out.println(c.toString());
		}	
	}

	/**
	 * Sorts the animals then prints 
	 * printAll() method runs from the main and prints status
	 */
	private void printAll() {
		
		kennel.sortKennel();
		System.out.println(kennel);
	}

	/*
	 * save() method runs from the main and writes back to file
	 */
	private void save() {
		try(FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter outfile = new PrintWriter(bw);){
			
			outfile.println(kennel.getName());
			outfile.println(kennel.getCapacity());
			outfile.println(kennel.getNumOfAnimals());
			Dog[] dogs = kennel.obtainAllDogs();
			Cat[] cats = kennel.obtainAllCats();
			for (Dog d: dogs){
				outfile.println("dog");
				outfile.println(d.getName());
				ArrayList<Owner> owners = d.getOriginalOwners();
				outfile.println(owners.size());
				for(Owner o: owners){
					outfile.println(o.getName());
					outfile.println(o.getPhone());
				}
				outfile.println(d.getLikesBones());
				outfile.println(d.isNeedWalked());
				outfile.println(d.getFoodPerDay());
				outfile.println(d.getFavFood());
			}
			
			for (Cat c: cats){
				outfile.println("cat");
				outfile.println(c.getName());
				ArrayList<Owner> owners = c.getOriginalOwners();
				outfile.println(owners.size());
				for(Owner o: owners){
					outfile.println(o.getName());
					outfile.println(o.getPhone());
				}
				outfile.println(c.isCanShareRun());
				outfile.println(c.getFoodPerDay());
				outfile.println(c.getFavFood());
			}
		} catch (IOException e) {
			System.err.println("Problem when trying to write to file: " + filename);
		}

	}

	private void removeDog() {
		System.out.println("which dog do you want to remove");
		String dogtoberemoved;
		dogtoberemoved = scan.nextLine();
		kennel.removeDog(dogtoberemoved);
	}
	
	private void removeCat(){
		System.out.println("which cat do you want to remove");
		String cattoberemoved;
		cattoberemoved = scan.nextLine();
		kennel.removeCat(cattoberemoved);
	}

	private void searchForDog() {
		System.out.println("which dog do you want to search for");
		String name = scan.nextLine();
		System.out.println(kennel.searchDogs(name));
	}
	
	private void searchForCat() {
		System.out.println("which cat do you want to search for");
		String name = scan.nextLine();
		System.out.println(kennel.searchCats(name));
	}

	private void changeKennelName() {
		String name = scan.nextLine();
		kennel.setName(name);
	}

	private void admitDog() {
		boolean lb = false;
		boolean nw = false;
		System.out
				.println("enter on separate lines: name, owner-name, owner-phone, likeBones?, needsWalked?, favourite food, number of times fed");
		String name = scan.nextLine();
		
		//error checking that name contains atleast one letter
		while(name.matches("[0-9]+")){
			System.out.println("Sorry please use letters only in the name");
			System.out.println("Try again");
			name = scan.nextLine();
		}
		
		ArrayList<Owner> owners = getOwners();
		System.out.println("Does he like bones? (Y/N)");
		String likeBones;
		likeBones = scan.nextLine().toUpperCase();
		if (likeBones.equals("Y")) {
			lb = true;
		}
		System.out.println("Do they needs walks? (Y/N)");
		String needsWalked;
		needsWalked = scan.nextLine().toUpperCase();
		if(needsWalked.equals("Y")) {
			nw = true; 
		}
		System.out.println("What is his/her favourite food?");
		String fav;
		fav = scan.nextLine();
		System.out.println("How many times is he/she fed a day? (as a number)");
		int numTimes;
		numTimes = scan.nextInt(); // This can be improved (InputMismatchException?)
		scan.nextLine();
		Dog newDog = new Dog(name, owners, lb, nw, fav, numTimes);
		kennel.addDog(newDog);
	}
	
	private void admitCat() {
		boolean sr = false;
		System.out
				.println("enter on separate lines: name, owner-name, owner-phone, can share a run?, favourite food, number of times fed");
		String name = scan.nextLine();
		
		
		//error checking that there is atleast one letter in the name 
		while(name.matches("[0-9]+")){
			System.out.println("Sorry please use letters only in the name");
			System.out.println("Try again");
			name = scan.nextLine();
		}
		
		ArrayList<Owner> owners = getOwners();
		System.out.println("Can they share a run? (Y/N)");
		String shareRun;
		shareRun = scan.nextLine().toUpperCase();
		if (shareRun.equals("Y")) {
			sr = true;
		}
		System.out.println("What is his/her favourite food?");
		String fav;
		fav = scan.nextLine();
		System.out.println("How many times is he/she fed a day? (as a number)");
		int numTimes;
		numTimes = scan.nextInt(); // This can be improved (InputMismatchException?)
		scan.nextLine();
		Cat newCat = new Cat(name, owners, sr, fav, numTimes);
		kennel.addCat(newCat);;
	}

	private ArrayList<Owner> getOwners() {
		ArrayList<Owner> owners = new ArrayList<Owner>();
		String answer;
		do {
			System.out
					.println("Enter on separate lines: owner-name owner-phone");
			String ownName = scan.nextLine();
		
			while(ownName.matches("[0-9]+")){
				System.out.println("Sorry please use letters only in the name");
				System.out.println("Try again");
				ownName = scan.nextLine();
			}
			
			String ownPhone = scan.nextLine();
			
			//ensuring that only numbers are entered for the users phone number 
			while(!ownPhone.matches("[0-9]+")){
				System.out.println("Sorry please use numbers only in the phone number");
				System.out.println("Try again");
				ownPhone = scan.nextLine();
			}
			
			Owner own = new Owner(ownName, ownPhone);
			owners.add(own);
			System.out.println("Another owner (Y/N)?");
			answer = scan.nextLine().toUpperCase();
		} while (!answer.equals("N"));
		return owners;
	}

	private void printMenu() {
		System.out.println("1 -  add a new Dog ");
		System.out.println("2 -  add a new Cat");
		System.out.println("3 -  set up Kennel name");
		System.out.println("4 -  print all dogs who like bones");
		System.out.println("5 -  print all cats that can share a run");
		System.out.println("6 -  search for a dog");
		System.out.println("7 -  search for a cat");
		System.out.println("8 -  remove a dog");
		System.out.println("9 -  remove a cat" );
		System.out.println("10 -  set kennel capacity");
		System.out.println("11 - print all animals alphabetically");
		System.out.println("q - Quit");
	}

	// /////////////////////////////////////////////////
	public static void main(String args[]) {
		System.out.println("**********HELLO***********");
		KennelDemo demo = new KennelDemo();
		demo.initialise();
		demo.runMenu();
		demo.printAll();
		demo.save();
		System.out.println("***********GOODBYE**********");
	}
}
