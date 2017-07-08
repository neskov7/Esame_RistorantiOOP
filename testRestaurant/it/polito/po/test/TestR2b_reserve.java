package it.polito.po.test;

import junit.framework.TestCase;

import restaurantChain.Chain;
import restaurantChain.InvalidName;
import restaurantChain.Restaurant;

public class TestR2b_reserve extends TestCase{

	Chain ch;
	Restaurant r1;
	public void setUp() throws Exception {
		ch = new Chain();
		ch.addRestaurant("Mac1", 3);
		r1 = ch.getRestaurant("Mac1");
	}

	public void test_invalid() {
		try {			
			r1.reserve("Piero", 2);
			r1.reserve("Piero", 5);
			fail("invalid name");
		} catch (InvalidName e1) {
		}
	}
	public void test_accepted() {
		try {			
			int t = r1.reserve("Piero", 2);
			assertEquals(1,t);
			t = r1.reserve("Chiara", 5);
			assertEquals(2,t);
		} catch (InvalidName e1) {
			fail("valid names");
		}
	}
	public void test_refused() {
		try {
			int t = r1.reserve("Piero", 7);
			assertEquals(2,t);
			t = r1.reserve("Chiara", 6);
			assertEquals(0,t);
		} catch (InvalidName e1) {
			fail("valid names");
		}
	}
	public void test_getRefused() {
		try {
			r1.reserve("Piero", 7);
			r1.reserve("Chiara", 6);
			assertEquals(6,r1.getRefused());
		} catch (InvalidName e1) {
			fail("valid names");
		}
	}
	public void test_getUnusedTables() {
		try {
			r1.reserve("Piero", 7);
			r1.reserve("Chiara", 6);
			assertEquals(1,r1.getUnusedTables());
		} catch (InvalidName e1) {
			fail("valid names");
		}
	}
}
