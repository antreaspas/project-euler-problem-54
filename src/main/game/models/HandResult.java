package main.game.models;

import main.game.enums.HandResultType;
import main.game.models.data.HandResultCardInfo;

public class HandResult {

	private final HandResultType type;
	private final HandResultCardInfo cards;

	public HandResult(HandResultType type, HandResultCardInfo cards) {
		this.type = type;
		this.cards = cards;
	}

	public HandResultType getType() {
		return type;
	}

	public HandResultCardInfo getCards() {
		return cards;
	}

}
