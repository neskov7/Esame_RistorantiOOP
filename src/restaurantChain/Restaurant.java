package restaurantChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Restaurant {

	private String name;
	private int tables, busyTables = 0, refused = 0;
	private Map<String, Menu> listing = new HashMap<>();
	private Map<String, Integer> prenotations = new HashMap<String, Integer>();

	public Restaurant(String name, int tables) {
		this.name = name;
		this.tables = tables;
	}

	public String getName() {
		return name;
	}

	public void addMenu(String name, double price) throws InvalidName {
		if (listing.containsKey(name)) {
			throw new InvalidName();
		}
		listing.put(name, new Menu(name, price));
	}

	public int reserve(String name, int persons) throws InvalidName {
		if (prenotations.containsKey(name))
			throw new InvalidName();
		int requiredTables = (int) Math.ceil(persons / 4);
		if (tables - busyTables - requiredTables < 0) {
			refused ++;
			return 0;
		}
		busyTables += requiredTables;
		prenotations.put(name, requiredTables);
		return requiredTables;
	}

	public int getRefused() {
		return refused;
	}

	public int getUnusedTables() {
		return tables - busyTables;
	}

	public boolean order(String name, String... menu) throws InvalidName {
		return false;
	}

	public List<String> getUnordered() {
		return null;
	}

	public double pay(String name) throws InvalidName {
		return -1.0;
	}

	public List<String> getUnpaid() {
		return null;
	}

	public double getIncome() {
		return -1.0;
	}

}
