import java.util.ArrayList;

/**
 * 
 * To model a Kennel - a collection of dogs
 * 
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 *
 */
public class Kennel {
	private String name;
	private ArrayList<Animal> animals;
	private int nextFreeLocation;
	private int capacity;

	/**
	 * Creates a kennel with a default size 20
	 * 
	 * @param maxNoAnimals
	 *            The capacity of the kennel
	 */
	public Kennel(){
		this(20);
		
	}
	
	/**
	 * Create a kennel
	 * 
	 * @param maxNoAnimals
	 *            The capacity of the kennel
	 */
	public Kennel(int maxNoAnimals) {
		nextFreeLocation = 0; // no Dogs in collection at start
		capacity = maxNoAnimals;
		animals = new ArrayList<Animal>(capacity); // set up default. This can
												// actually be exceeded
												// when using ArrayList but we
												// won't allow that
												// to happen.
	}

	/**
	 * This method sets the value for the name attribute. The purpose of the
	 * attribute is: The name of the kennel e.g. "DogsRUs"
	 * 
	 * @param theName
	 */
	public void setName(String theName) {
		name = theName;
	}
	
	/**
	 * Set the size of the kennel
	 * @param capacity The max animals we can house
	 * this function will not check that the new size is reasonable 
	 * and will not be less than current number of animals
	 */
	public void setCapacity(int capacity){
		
		if(this.capacity < capacity){
			System.out.println("Sorry but that number is less than what we already contain");
			System.out.println("No change has been made!");
		}
		else{
			System.out.println("Capacity has been changed");
			this.capacity = capacity;
		}

	}
	
	/**
	 * Maximum capacity of the kennels
	 * @return The max size of the kennel
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * This method gets the value for the name attribute. The purpose of the
	 * attribute is: The name of the Kennel e.g. "DogsRUs"
	 * 
	 * @return String The name of the kennel
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method returns the number of dogs in a kennel
	 * 
	 * @return int Current number of dogs in the kennel
	 * 
	 */
	public int getNumOfDogs() {	
		int numDogs = 0; 	
		for (int i = 0; i < animals.size(); i++) {	
			if(isDog(animals.get(i))){
				numDogs++; 
			}	
		}
		return numDogs;
	}
	
	/**
	 * This method returns the number of cats in a kennel
	 * 
	 * @return int Current number of cats in the kennel
	 * 
	 * 
	 */
	public int getNumOfCats() {	
		int numCats = 0; 	
		for (int i = 0; i < animals.size(); i++) {	
			if(!isDog(animals.get(i))){
				numCats++; 
			}	
		}
		return numCats;
	}
	
	/**
	 * This method returns the total number of animals in the kennel
	 */
	public int getNumOfAnimals(){
		
		return getNumOfDogs() + getNumOfCats(); 
		
	}

	/**
	 * Enables a user to add a Dog to the Kennel
	 * 
	 * @param theDog
	 *            A new dog to home
	 *            
	 */
	public void addDog(Dog theDog) {
		if (nextFreeLocation >= capacity) {
			System.out.println("Sorry kennel is full - cannot add");
			return;
		}
		// we add in the position indexed by nextFreeLocation
		// This starts at zero
		animals.add(theDog);

		// now increment index ready for next one
		nextFreeLocation = nextFreeLocation + 1;
	}
	
	/**
	 * Enables a user to add a Cat to the Kennel
	 * 
	 * @param theCat
	 *            A new cat to home
	 *            
	 *  
	 */
	public void addCat(Cat theCat) {
		if (nextFreeLocation >= capacity) {
			System.out.println("Sorry kennel is full - cannot add");
			return;
		}
		// we add in the position indexed by nextFreeLocation
		// This starts at zero
		animals.add(theCat);

		// now increment index ready for next one
		nextFreeLocation = nextFreeLocation + 1;
	}
	
	
	

	/**
	 * Enables a user to delete a Dog from the Kennel.
	 * 
	 * @param theAnimal
	 *            The dog to remove
	 */
	public void removeDog(String who) {
		Dog which = null;
		// Search for the animal by name
		for (Animal a : animals) {
			if (who.equals(a.getName()) && isDog(a)) {
				which = (Dog) a;
			}
		}
		if (which != null) {
			animals.remove(which); // Requires that Animal has an equals method
			System.out.println("removed " + who);
			nextFreeLocation = nextFreeLocation - 1;
		} else
			System.err.println("cannot remove - not in kennel");
	}
	
	/**
	 * Enables a user to delete a Cat from the Kennel.
	 * 
	 * @param theAnimal
	 *            The cat to remove
	 */
	public void removeCat(String who) {
		Cat which = null;
		// Search for the animal by name
		for (Animal a : animals) {
			if (who.equals(a.getName()) && !isDog(a)) {
				which = (Cat) a;
			}
		}
		if (which != null) {
			animals.remove(which); // Requires that Animal has an equals method
			System.out.println("removed " + who);
			nextFreeLocation = nextFreeLocation - 1;
		} else
			System.err.println("cannot remove - not in kennel");
	}
	
	
	

	/**
	 * @return String showing all the information in the kennel
	 * Now makes use of the string builder object 
	 */
	public String toString() {
		
		StringBuilder results = new StringBuilder();
		results.append("Data in Kennel " + name + " is:").append('\n');
		for (Animal d : animals) {
			results.append(d).append('\n');
		}
		return results.toString();
	}

	/**
	 * Returns an array of the dogs in the kennels
	 * @return An array of the correct size
	 */
	public Dog[] obtainAllDogs() {
		Dog[] result = new Dog[getNumOfDogs()];

		int pos = 0; 
		
		for (int i = 0; i < animals.size(); i++) {
			if(isDog(animals.get(i))){
				result[pos] = (Dog) animals.get(i);
				pos++; 
			}
		}
		
		return result;
	
	}
	
	/**
	 * Returns an array of the dogs in the kennels
	 * @return An array of the correct size
	 */
	public Cat[] obtainAllCats() {
		Cat[] result = new Cat[getNumOfCats()];

		int pos = 0; 
		
		for (int i = 0; i < animals.size(); i++) {
			if(!isDog(animals.get(i))){
				result[pos] = (Cat) animals.get(i);
				pos++; 
			}
		}
		
		return result;
	
	}

	/**
	 * Only returns those dogs who like bones
	 * @return An array of dogs of the correct size. If no dogs like bones then returns an empty array (size 0)
	 */
	public Dog[] obtainDogsWhoLikeBones() {
		
		/*
		 * slight problem that has occurred to me, 
		 * what if there is no dogs who like bones 
		 * 
		 * Also the memory waste here of creating too large an array
		 */
		
		int numDogsWhoLikeBones = 0; 
		for (int i = 0; i < animals.size(); i++) {
			
			if(isDog(animals.get(i))){
				if(((Dog)animals.get(i)).getLikesBones() == true){
					numDogsWhoLikeBones++; 
				}
			}

		}	
		Dog[] result = new Dog[numDogsWhoLikeBones];
		
		//signifies the current space in the array to write to
		int pos = 0; 
		
		for (int i = 0; i < animals.size(); i++) {
			
			if(isDog(animals.get(i))){
				if(((Dog)animals.get(i)).getLikesBones() == true){
					result[pos] = (Dog) animals.get(i); 
					pos++; 
				}
			}

		}
		
		return result;
	}
	
	/**
	 * Only returns those dogs who like bones
	 * @return An array of dogs of the correct size. If no dogs like bones then returns an empty array (size 0)
	 */
	public Cat[] obtainCatsWhoCanShareRun() {
		
		
		int numCatsWhoCanShareRun = 0; 
		for (int i = 0; i < animals.size(); i++) {
			
			if(!isDog(animals.get(i))){
				if(((Cat)animals.get(i)).isCanShareRun() == true){
					numCatsWhoCanShareRun++; 
				}
			}

		}	
		Cat[] result = new Cat[numCatsWhoCanShareRun];
		
		//signifies the current space in the array to write to
		int pos = 0; 
		
		for (int i = 0; i < animals.size(); i++) {
			if(((Cat)animals.get(i)).isCanShareRun() == true){
				result[pos] = (Cat) animals.get(i); 
				pos++; 
			}
		}
		
		return result;
	}
	
	
	
	public boolean isDog(Animal animal){
		
		if(animal instanceof Dog){
			return true; 
		}
		return false;
		
	}

	/**
	 * 
	 * @param name the name to find 
	 * @return results from the search
	 * this will find any and all matches to the characters entered 
	 * extra bit of flair here as it can return multiple matches 
	 */
	public ArrayList<Dog> searchDogs(String name) {
		
		ArrayList<Dog> result = new ArrayList<Dog>(0); 
		
		//search for dogs with names matching the char sequence
		for (int i = 0; i < animals.size(); i++) {
			
			if(isDog(animals.get(i))){
				if(animals.get(i).getName().toLowerCase().contains(name.toLowerCase())){
					result.add((Dog) animals.get(i));
				}	
			}

		}	
		return result;
	}
	
	/**
	 * 
	 * @param name the name to find 
	 * @return results from the search
	 * this will find any and all matches to the characters entered 
	 * extra bit of flair here as it can return multiple matches 
	 */
	public ArrayList<Cat> searchCats(String name) {
		
		ArrayList<Cat> result = new ArrayList<Cat>(0); 
		
		//search for dogs with names matching the char sequence
		for (int i = 0; i < animals.size(); i++) {
			
			if(!isDog(animals.get(i))){
				if(animals.get(i).getName().toLowerCase().contains(name.toLowerCase())){
					result.add((Cat) animals.get(i));
				}	
			}

		}	
		return result;
	}

	/**
	 * 
	 * @param name the name to find 
	 * @return results from the search
	 * this will find any and all matches to the characters entered 
	 * extra bit of flair here as it can return multiple matches 
	 */
	public ArrayList<Animal> searchAll(String name) {
		
		ArrayList<Animal> result = new ArrayList<Animal>(0); 
		
		//search for dogs with names matching the char sequence
		for (int i = 0; i < animals.size(); i++) {
			
			if(isDog(animals.get(i))){
				if(animals.get(i).getName().toLowerCase().contains(name.toLowerCase())){
					result.add(animals.get(i));
				}	
			}
		}	
		return result;
	}
	
}
