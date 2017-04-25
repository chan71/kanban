package org.ck.sample;

import org.ck.sample.model.IterationTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AppTest {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(IterationTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
