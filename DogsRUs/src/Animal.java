import java.util.ArrayList;


public abstract class Animal {

	private String petName;
	private ArrayList<Owner> originalOwners;
	private String favFood;
	private int foodPerDay;

	public String getName() {
		return petName;
	}

	public void setName(String newName) {
		petName = newName;
	}

	/**
	 * @return the originalOwners
	 */
	public ArrayList<Owner> getOriginalOwners() {
		return originalOwners;
	}

	/**
	 * @param originalOwners the originalOwners to set
	 */
	public void setOriginalOwners(ArrayList<Owner> originalOwners) {
		this.originalOwners = originalOwners;
	}

	/**
	 * @return the favFood
	 */
	public String getFavFood() {
		return favFood;
	}

	/**
	 * @param favFood the favFood to set
	 */
	public void setFavFood(String favFood) {
		this.favFood = favFood;
	}

	/**
	 * @return the foodPerDay
	 */
	public int getFoodPerDay() {
		return foodPerDay;
	}

	/**
	 * @param foodPerDay the foodPerDay to set
	 */
	public void setFoodPerDay(int foodPerDay) {
		this.foodPerDay = foodPerDay;
	}




	/**
	 * Note that this only compares equality based on a
	 * animal's name.
	 * @param The other dog to compare against.
	 */
	@Override
	public boolean equals(Object obj) { 
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		return true;
	}




}
