package it.polito.po.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestR1_RestaurantChain.class);
        suite.addTestSuite(TestR2a_addMenu.class);
        suite.addTestSuite(TestR2b_reserve.class);
        suite.addTestSuite(TestR3a_order.class);
        suite.addTestSuite(TestR3b_pay.class);
        suite.addTestSuite(TestR4_Reports.class);

        //$JUnit-END$
        return suite;
    }

}
