package smartfactory.matchers;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class Matcher<T> extends TypeSafeMatcher<T> {

	private List<MatcherExpectation> expectations = new ArrayList<MatcherExpectation>();

	protected abstract class MatcherExpectation {
		public abstract void trigger(T arg) throws MatcherException;

		protected void compare(String name, long expected, long actual) throws MatcherException {
			if (expected != actual) {
				throw new MatcherException(String.format("%s - expected: %d, actual: %d", name, expected, actual));
			}
		}

		protected void compare(String name, String expected, String actual) throws MatcherException {
			if (expected.compareTo(actual) != 0) {
				throw new MatcherException(String.format("%s - expected: %s, actual: %s", name, expected, actual));
			}
		}

		protected void compare(String name, int expected, int actual) throws MatcherException {
			if (expected != actual) {
				throw new MatcherException(String.format("%s - expected: %d, actual: %d", name, expected, actual));
			}
		}

		protected void compareTimestamps(String name, long expected, long actual) throws MatcherException {
			if (Math.abs(expected - actual) > TOLERANCE) {
				throw new MatcherException(String.format("%s - expected: %d, actual: %d, difference grater than %d",
						name, expected, actual, TOLERANCE));
			}
		}

		protected void compare(String name, boolean expected, boolean actual) throws MatcherException {
			if (expected != actual) {
				throw new MatcherException(String.format("%s - expected: %b, actual: %b", name, expected, actual));
			}
		}

		protected void compare(String name, Object expected, Object actual) throws MatcherException {
			if (expected != actual) {
				throw new MatcherException(String.format("%s - expected: %s, actual: %s", name, expected, actual));
			}
		}

		final static private int TOLERANCE = 1000;
	}

	private MatcherException mismatch = null;

	@Override
	public void describeTo(Description description) {
		if (mismatch != null) {
			description.appendText(mismatch.getMessage());
		}
	}

	@Override
	protected boolean matchesSafely(T item) {
		try {
			for (MatcherExpectation expectation : expectations) {
				expectation.trigger(item);
			}
		} catch (MatcherException e) {
			mismatch = e;
			return false;
		}
		return true;
	}

	public void addExpectation(MatcherExpectation expectation) {
		expectations.add(expectation);
	}
}
