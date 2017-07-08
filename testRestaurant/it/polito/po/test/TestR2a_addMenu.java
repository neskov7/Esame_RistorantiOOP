package it.polito.po.test;

import junit.framework.TestCase;


import restaurantChain.Chain;
import restaurantChain.InvalidName;
import restaurantChain.Restaurant;

public class TestR2a_addMenu extends TestCase{

	Chain ch;
	Restaurant r1;
	public void setUp() throws Exception {
		ch = new Chain();
		ch.addRestaurant("Mac1", 8);
		r1 = ch.getRestaurant("Mac1");
	}

	public void test_validAdd() {
		try {			
			r1.addMenu("m1", 12.5);
			r1.addMenu("m2", 20.0);
			r1.addMenu("m3", 25.0);
		} catch (InvalidName e1) {
			fail("valid names");
		}
	}
	public void test_invalidAdd() {
		try {
			r1.addMenu("m1", 12.5);
			r1.addMenu("m1", 20.0);
			fail();
		} catch (InvalidName e1) {
		}
	}
}
