package de.janek.sql.builder.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * IMPORTANT: maybe some tests are failing because the required data isnt existing in the database
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestDataBaseConnection.class,
        TestSelectBuilder.class,
        TestContainsBuilder.class,
        TestInsertBuilder.class,
        TestUpdateBuilder.class,
        TestDeleteBuilder.class
})

/**
 * runs all test classes
 * the class is only as a holder for the above annotations
 */
public class TestSuite {

}
