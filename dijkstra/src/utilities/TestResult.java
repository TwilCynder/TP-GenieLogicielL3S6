package utilities;

public class TestResult {
	// Attributes
	private boolean areEquals;
	private String error;

	
	public TestResult() {
		areEquals = true;
	}
	
	public TestResult(String error) {
		areEquals = false;
		this.error = error;
	}

	public boolean areEquals() {
		return areEquals;
	}

	public String getError() {
		return error;
	}
	
	
	public void print(int nTest) {

		if(areEquals) {
			System.out.println("[V] - Test : "+nTest);
		}
		else {
			System.err.println("[X] - Test : "+nTest+"\n"+"|----> "+error);
		}
	}

}
