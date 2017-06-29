package restaurantChain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
		return 
				chainRestaurant.values().stream()
				.sorted(Comparator.comparing(Restaurant::getIncome))
				.collect(Collectors.toList())
				;
	}

	public List<Restaurant> sortByRefused() {
		return 
				chainRestaurant.values().stream()
				.sorted(Comparator.comparing(Restaurant::getRefused))
				.collect(Collectors.toList())
				;
	}

	public List<Restaurant> sortByUnusedTables() {
		return 
				chainRestaurant.values().stream()
				.sorted(Comparator.comparing(Restaurant::getUnusedTables))
				.collect(Collectors.toList())
				;
	}
}
