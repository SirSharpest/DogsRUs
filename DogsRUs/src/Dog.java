import java.util.ArrayList;

/**
 * To support an individual dog
 * @author Chris Loftus
 * @version 1.0 (16th March 2015)
 */
public class Dog extends Animal {

	private ArrayList<Owner> originalOwners;
	private boolean likesBones;
	private String favFood;
	private int foodPerDay;

	/**
	 * Constructor for the dog
	 * @param name The dog's name
	 * @param owners A list of original owners: a copy is made
	 * @param likeBones Does the dog like bones?
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day 
	 */
	public Dog(String name, ArrayList<Owner> owners, boolean likeBones, String food,
			int mealsPerDay) {
		setName(name);
		originalOwners = new ArrayList<Owner>();
		
		// We make a true copy of the owners ArrayList to make sure that we
		// don't break encapsulation: i.e. don't share object references with
		// other code
		for(Owner o: owners){
			Owner copy = new Owner(o.getName(), o.getPhone());
			originalOwners.add(copy);
		}
		this.likesBones = likeBones;
		this.favFood = food;
		this.foodPerDay = mealsPerDay;
	}

	
	/**
	 * Returns a copy of the original owners
	 * @return A copy of the original owners as an array
	 */
	public Owner[] getOriginalOwners(){
		Owner[] result = new Owner[originalOwners.size()];
		result = originalOwners.toArray(result);
		return result;
	}

	/**
	 * Does the dog like bones?
	 * @return true if he does
	 */
	public boolean getLikesBones() {
		return likesBones;
	}
	/**
	 * How many times a day to feed the dog
	 * @param feeds The number of feeds per day
	 */
	public void setFeedsPerDay(int feeds){
		foodPerDay = feeds;
	}
	
	/**
	 * The number of feeds per day the dog is fed
	 * @return The number of feeds per day
	 */
	public int getFeedsPerDay(){
		return foodPerDay;
	}
	
	/**
	 * What's his favourite food?
	 * @param food The food he likes
	 */
	public void setFavouriteFood(String food){
		favFood = food;
	}
	
	/**
	 * The food the dog likes to eat
	 * @return The food 
	 */
	public String getFavouriteFood(){
		return favFood;
	}

	/**
	 * Note that this only compares equality based on a
	 * dog's name.
	 * @param The other dog to compare against.
	 */
	@Override
	public boolean equals(Object obj) { // Generated by Eclipse to be more robust
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dog other = (Dog) obj;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		return true;
	}

	/**
	 * A basic implementation to just return all the data in string form
	 * Using a string builder object
	 */
	public String toString() {
		
		StringBuilder results = new StringBuilder();
		results.append("Dog name: " + getName() + "\n");
		results.append("Likes bones?: " + likesBones + "\n");
		results.append("Original Owner(s): " + originalOwners + "\n");
		results.append("Favourite food: " + favFood + "\n"); 
		results.append("Feedings per day: " + foodPerDay + "\n");

		return results.toString();
	}

}
