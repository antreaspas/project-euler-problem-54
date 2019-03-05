package main.game.models;

import java.util.Arrays;

public class Hand {

	private final Card[] cards;

	public Hand() {
		this.cards = new Card[5];
		Arrays.sort(cards);
	}

	public void addCardFromTextAtIndex(String strCard, int index) {
		this.cards[index] = new Card(strCard);
	}

	public Card[] getCards() {
		return this.cards;
	}

}
