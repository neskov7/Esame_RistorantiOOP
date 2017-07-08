package it.polito.po.test;


import java.util.List;

import junit.framework.TestCase;

import restaurantChain.Chain;
import restaurantChain.InvalidName;
import restaurantChain.Restaurant;

public class TestR4_Reports extends TestCase{

	Chain ch;
	Restaurant r1,r2,r3;
	public void setUp() throws Exception {
		ch = new Chain();
		ch.addRestaurant("R3", 3);
		ch.addRestaurant("R2", 4);
		ch.addRestaurant("R1", 5);
		r1 = ch.getRestaurant("R1");
		r2 = ch.getRestaurant("R2");
		r3 = ch.getRestaurant("R3");
		r1.addMenu("m1", 12.5);
		r2.addMenu("m2", 20.0);
		r3.addMenu("m3", 25.0);
		r1.reserve("A", 11);	
		r1.reserve("B", 9);
		r2.reserve("C", 5);
		r2.reserve("D", 7);
		r3.reserve("E", 5);
		r3.reserve("F", 11);
	}

	public void testR3_sortByRefused() {
		assertEquals(9, r1.getRefused());
		assertEquals(0, r2.getRefused());
		assertEquals(11, r3.getRefused());
		List<Restaurant> ref = ch.sortByRefused();
		assertEquals(3, ref.size());
		assertEquals(r2, ref.get(0));
		assertEquals(r1, ref.get(1));
		assertEquals(r3, ref.get(2));
	}
	public void testR3_sortByUnusedTables() {
		assertEquals(2, r1.getUnusedTables());
		assertEquals(0, r2.getUnusedTables());
		assertEquals(1, r3.getUnusedTables());
		List<Restaurant> ref = ch.sortByUnusedTables();
		assertEquals(3, ref.size());
		assertEquals(r2, ref.get(0));
		assertEquals(r3, ref.get(1));
		assertEquals(r1, ref.get(2));
	}
	public void testR3_sortByIncome() {
		try {
			r1.order("A", "m1","m1","m1","m1","m1","m1","m1","m1","m1","m1","m1","m1");
			r2.order("C", "m2","m2","m2","m2","m2");
			r2.order("D", "m2","m2","m2","m2","m2","m2","m2");
			r3.order("E", "m3","m3","m3","m3","m3");
			r1.pay("A");
			r2.pay("C");
			r2.pay("D");
			r3.pay("E");
		} catch (InvalidName e) {
			fail("valid order");
		}
		assertEquals(150.0, r1.getIncome());
		assertEquals(240.0, r2.getIncome());
		assertEquals(125.0, r3.getIncome());
		List<Restaurant> ref = ch.sortByIncome();
		assertEquals(3, ref.size());
		assertEquals(r2, ref.get(0));
		assertEquals(r1, ref.get(1));
		assertEquals(r3, ref.get(2));
	}
}
