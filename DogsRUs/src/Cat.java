import java.util.ArrayList;


public class Cat extends Animal{

	
	private boolean canShareRun; 

	/**
	 * Constructor for the cat
	 * @param name The Cat's name
	 * @param owners A list of original owners: a copy is made
	 * @param canShareRun Does the cat share a run with others?
	 * @param food The kind of food it eats
	 * @param mealsPerDay Number of feeds per day 
	 */
	public Cat(String name, ArrayList<Owner> owners, boolean canShareRun, String food,
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
		this.setCanShareRun(canShareRun);
		setFavFood(food); 
		setFoodPerDay(mealsPerDay);
	}

	/**
	 * @return the canShareRun
	 */
	public boolean isCanShareRun() {
		return canShareRun;
	}

	/**
	 * @param canShareRun the canShareRun to set
	 */
	public void setCanShareRun(boolean canShareRun) {
		this.canShareRun = canShareRun;
	}



	

	
}
