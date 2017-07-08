package it.polito.po.test;

import junit.framework.TestCase;

import restaurantChain.Chain;
import restaurantChain.InvalidName;
import restaurantChain.Restaurant;

public class TestR1_RestaurantChain extends TestCase {

	Chain ch;
	public void setUp() throws Exception {
		ch = new Chain();
	}

	public void test_validAdd() {
		try {
			ch.addRestaurant("Mac1", 8);
			ch.addRestaurant("Mac2", 9);
			ch.addRestaurant("Mac3", 5);
		} catch (InvalidName e1) {
			fail("valid names");
		}
	}
	public void test_invalidAdd() {
		try {
			ch.addRestaurant("Mac1", 8);
			ch.addRestaurant("Mac1", 9);
			fail("invalid name");
		} catch (InvalidName e1) {
		}
	}
	public void test_validGet() {
		try {
			ch.addRestaurant("Mac1", 8);
			Restaurant r1 = ch.getRestaurant("Mac1");
			assertNotNull(r1);
		} catch (InvalidName e1) {
			fail("valid name");
		}
	}
	public void test_invalidGet() {
		try {
			ch.addRestaurant("Mac1", 8);
			ch.getRestaurant("Mac2");
			fail("invalid name");
		} catch (InvalidName e1) {
		}
	}
}
