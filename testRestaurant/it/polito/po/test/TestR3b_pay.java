package it.polito.po.test;

import java.util.List;

import junit.framework.TestCase;

import restaurantChain.Chain;
import restaurantChain.InvalidName;
import restaurantChain.Restaurant;

public class TestR3b_pay extends TestCase {

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
			r1.pay("Anna");
			fail("invalid name");
		} catch (InvalidName e) {
		}
	}
	public void test_accepted() {
		try {
			r1.order("Piero", "m1","m2","m3","m1","m3");
			double paid = r1.pay("Piero");
			assertEquals(95.0,paid);
		} catch (InvalidName e) {
			fail("valid name");
		}
	}
	public void test_unordered() {
		try {
			double paid = r1.pay("Piero");
			assertEquals(0.0,paid);
		} catch (InvalidName e) {
			fail("valid name");
		}
	}
	public void test_getUnpaid() {
		try {
			r1.reserve("Susanna", 2);
			r1.reserve("Giorgio", 1);
			r1.order("Giorgio", "m2");
			r1.order("Susanna", "m3", "m2");
			r1.order("Piero", "m1","m2","m3","m1","m3");
			r1.pay("Piero");
			List<String> unpaid = r1.getUnpaid();
			assertEquals(2,unpaid.size());
			assertEquals("Giorgio",unpaid.get(0));
			assertEquals("Susanna",unpaid.get(1));
			r1.pay("Susanna");
			unpaid = r1.getUnpaid();
			assertEquals(1,unpaid.size());
			assertEquals("Giorgio",unpaid.get(0));
			r1.pay("Giorgio");
			unpaid = r1.getUnpaid();
			assertEquals(0,unpaid.size());
		} catch (InvalidName e) {
			fail("valid name");
		}
	}
	public void test_getIncome() {
		try {
			r1.reserve("Susanna", 2);
			r1.reserve("Giorgio", 1);
			r1.order("Giorgio", "m2");
			r1.order("Susanna", "m3", "m2");
			r1.order("Piero", "m1","m2","m3","m1","m3");
			double piero = r1.pay("Piero");
			assertEquals(95.0,piero);
			double susanna = r1.pay("Susanna");
			assertEquals(45.0,susanna);
			double giorgio = r1.pay("Giorgio");
			assertEquals(20.0,giorgio);
			double income = r1.getIncome();
			assertEquals(160.0,income);
		} catch (InvalidName e) {
			fail("valid name");
		}
	}
}
