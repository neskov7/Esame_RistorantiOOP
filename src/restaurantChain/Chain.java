package restaurantChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Chain {
	
	private Map<String, Restaurant> chainRestaurant = new HashMap<>();

	public void addRestaurant(String name, int tables) throws InvalidName {
		if (chainRestaurant.containsKey(name)) { throw new InvalidName(); }
		chainRestaurant.put(name, new Restaurant(name, tables));
	}

	public Restaurant getRestaurant(String name) throws InvalidName {
		Restaurant restaurant = chainRestaurant.get(name);
		if (restaurant == null) {	throw new InvalidName(); 	}
		return restaurant;
	}

	public List<Restaurant> sortByIncome() {
		return null;
	}

	public List<Restaurant> sortByRefused() {
		return null;
	}

	public List<Restaurant> sortByUnusedTables() {
		return null;
	}
}
