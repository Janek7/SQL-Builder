package de.janek.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDataBaseConnection.class,
        TestSelectBuilder.class,
        TestInsertBuilder.class,
        TestUpdateBuilder.class
})

/**
 * runs all test classes
 * the class is only as a holder for the above annotations
 */
public class TestSuite {

}
