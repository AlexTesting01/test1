package com.example.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.IClassListener;
import org.testng.ITestClass;

public class TestLogger implements ISuiteListener, IClassListener {

    @Override
    public void onStart(ISuite suite) {
        System.out.println("▶️ Run suite <" + suite.getName() + ">");
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("✅ Finished suite <" + suite.getName() + ">");
    }

    @Override
    public void onBeforeClass(ITestClass testClass) {
        System.out.println("    ▶️ Run test <" + testClass.getName() + ">");
    }

    @Override
    public void onAfterClass(ITestClass testClass) {
        System.out.println("    ✅ Finished test <" + testClass.getName() + ">");
    }
}
