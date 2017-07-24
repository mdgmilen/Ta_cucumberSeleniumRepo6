package org.mileng.ta_sample1.framework;

import java.util.function.BooleanSupplier;

public class SyncEx {

	private static final int DEFAULT_WAIT_TIMEOUT = 20; // One minute

	/**
	 * Waits for arbitrary condition
	 *
	 * @param condition
	 *            supplied as a BooleanSupplier lambda (() ->
	 *            boolean_expression)
	 * @param seconds
	 * @return true if the condition became true during the seconds timeout,
	 *         false otherwise
	 */
	public static boolean wait(BooleanSupplier condition) {
		long endTime = getCurrentTime() + DEFAULT_WAIT_TIMEOUT;
		do {
			if (condition.getAsBoolean()) {
				return true;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				return false;
			}
		} while (getCurrentTime() < endTime);
		return false;
	}
	
	private static long getCurrentTime() {
		return System.currentTimeMillis() / 1000l;
	}
}
