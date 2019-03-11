package game.enums;

public enum Suite {
	HEARTS, CLUBS, SPADES, DIAMONDS;

	public static Suite getSuiteFromText(char c) {
		switch (c) {
			case 'H':
				return HEARTS;
			case 'C':
				return CLUBS;
			case 'S':
				return SPADES;
			case 'D':
				return DIAMONDS;
			default:
				throw new IllegalArgumentException();
		}
	}
}
