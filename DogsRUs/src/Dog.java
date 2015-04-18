import java.util.ArrayList;

/**
 * To support an individual dog
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 */
public class Dog extends Animal {

	
	private boolean likesBones;
	private boolean needsWalked;


	/**
	 * Constructor for the dog
	 * @param name The dog's name
	 * @param owners A list of original owners: a copy is made
	 * @param likeBones Does the dog like bones?
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day 
	 */
	public Dog(String name, ArrayList<Owner> owners, boolean likeBones, boolean needWalked, String food,
			int mealsPerDay) {
		setName(name);
		setOriginalOwners(new ArrayList<Owner>());
		
		// We make a true copy of the owners ArrayList to make sure that we
		// don't break encapsulation: i.e. don't share object references with
		// other code
		for(Owner o: owners){
			Owner copy = new Owner(o.getName(), o.getPhone());
			getOriginalOwners().add(copy);
		}
		this.likesBones = likeBones;
		this.needsWalked = needWalked; 
		setFavFood(food); 
		setFoodPerDay(mealsPerDay);
	}

	

	/**
	 * Does the dog like bones?
	 * @return true if he does
	 */
	public boolean getLikesBones() {
		return likesBones;
	}

	


	/**
	 * A basic implementation to just return all the data in string form
	 * Using a string builder object
	 */
	public String toString() {
		
		StringBuilder results = new StringBuilder();
		results.append("Dog name: " + getName() + "\n");
		results.append("Likes bones?: " + likesBones + "\n");
		results.append("Original Owner(s): " + getOriginalOwners() + "\n");
		results.append("Favourite food: " + getFavFood() + "\n"); 
		results.append("Needs walked?" + isNeedWalked()+ "\n");

		return results.toString();
	}



	public boolean isNeedWalked() {
		return needsWalked;
	}



	public void setNeedsWalked(boolean needsWalked) {
		this.needsWalked = needsWalked;
	}

	
	
}
