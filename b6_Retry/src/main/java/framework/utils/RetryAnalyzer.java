package framework.utils;

import framework.config.ConfigReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        int max = ConfigReader.getInstance().getRetryCount();

        if (count < max) {
            count++;
            System.out.println("[Retry] Lan " + count);
            return true;
        }
        return false;
    }
}