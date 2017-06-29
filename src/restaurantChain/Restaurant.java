package restaurantChain;

import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Restaurant {

	private String name;
	private int tables, busyTables = 0, refused = 0;
	private Map<String, Menu> listing = new HashMap<>();
	private Map<String, Integer> prenotations = new HashMap<String, Integer>();
	private Map<String, String[]> orders = new HashMap<>(); 
	private Map<String, Double> paid = new HashMap<String, Double>();

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
		prenotations.put(name, persons);
		return requiredTables;
	}

	public int getRefused() {
		return refused;
	}

	public int getUnusedTables() {
		return tables - busyTables;
	}

	public boolean order(String name, String... menu) throws InvalidName {
		if (!prenotations.containsKey(name)) {	throw new InvalidName();	}
		if (menu.length< prenotations.get(name)) return false;
		for (String m : menu) {
			if(!listing.containsKey(m)) throw new InvalidName();
		}
		orders.put(name, menu);
		return true;
	}

	public List<String> getUnordered() {
		
		List<String> unordered = new ArrayList<String>();
		prenotations.keySet().forEach(p -> {
			if (!orders.containsKey(p)) {
				unordered.add(p);
			}
		});
		return unordered;
	}

	public double pay(String name) throws InvalidName {
		if (!prenotations.containsKey(name)) throw new InvalidName();
		if (!orders.containsKey(name)) throw new InvalidName();
		double toPay = 0;
		for ( String m : orders.get(name)) {
			toPay += listing.get(m).getPrice();
		}
		paid.put(name, toPay);
		return toPay;
	}

	public List<String> getUnpaid() {
		List<String> unpaid = new  ArrayList<String>();
		orders.keySet().forEach( o -> {
			if(!paid.containsKey(o)){
				unpaid.add(o);
			}
		} );
		return unpaid;
	}

	public double getIncome() {
		return paid.values().stream().reduce(0.0, (x,y) -> x+y );
	}

}
