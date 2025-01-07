package com_SCM_utilities_listeners;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryHelper implements IRetryAnalyzer{

	
	    
	    // Retry count limit can be declared as final, as it's a constant
	    private static final int MAX_RETRY_LIMIT = 5;

	    // Mutable count should be private to limit its visibility
	    private int currentRetryCount = 0;

	    @Override
	    public boolean retry(ITestResult result) {
	        if (currentRetryCount < MAX_RETRY_LIMIT) {
	            currentRetryCount++;
	            return true;
	        }
	        return false;
	    }
	}


