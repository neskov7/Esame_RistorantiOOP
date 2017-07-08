package it.polito.po.test;

import java.util.List;

import restaurantChain.Chain;
import restaurantChain.InvalidName;
import restaurantChain.Restaurant;
import junit.framework.TestCase;

public class TestR3a_order extends TestCase {

	Chain ch;
	Restaurant r1;
	public void setUp() throws Exception {
		ch = new Chain();
		ch.addRestaurant("Mac1", 3);
		r1 = ch.getRestaurant("Mac1");
		r1.addMenu("m1", 12.5);
		r1.addMenu("m2", 20.0);
		r1.addMenu("m3", 25.0);
		r1.reserve("Piero", 3);
	}
	
	public void test_invalidName() {
		try {
			r1.order("Anna", "m1","m2","m3");
			fail("invalid name");
		} catch (InvalidName e) {
		}
	}
	public void test_accepted() {
		try {
			boolean o = r1.order("Piero", "m1","m2","m3","m2");
			assertEquals(true, o);
		} catch (InvalidName e) {
			fail("valid name");
		}
	}
	public void test_unaccepted() {
		try {
			boolean o = r1.order("Piero", "m1","m2");
			assertEquals(false, o);
		} catch (InvalidName e) {
			fail("valid name");
		}
	}
	public void test_invalidMenuName() {
		try {
			r1.order("Piero", "m1","m4","m3");
			fail("invalid name");
		} catch (InvalidName e) {
		}
	}
	public void test_getUnordered() {
		try {
			r1.reserve("Susanna", 3);
			r1.reserve("Maria", 3);
			r1.order("Piero", "m1","m2","m3","m2");
			List<String> unord = r1.getUnordered();
			assertEquals(2,unord.size());
			assertEquals("Maria",unord.get(0));
			assertEquals("Susanna",unord.get(1));
			r1.order("Maria", "m1","m2","m3");
			unord = r1.getUnordered();
			assertEquals(1,unord.size());
			assertEquals("Susanna",unord.get(0));
			r1.order("Susanna", "m1","m2","m3");
			unord = r1.getUnordered();
			assertEquals(0,unord.size());
		} catch (InvalidName e) {
			fail("valid name");
		}
	}
}
