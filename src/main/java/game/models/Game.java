package game.models;

import java.util.Arrays;

import game.enums.GameResult;
import game.helpers.GameResolverHelper;

public class Game {

	private final Hand playerOneHand;
	private final Hand playerTwoHand;

	public Game(String[] strCards) {
		this.playerOneHand = new Hand(Arrays.copyOfRange(strCards, 0, 5));
		this.playerTwoHand = new Hand(Arrays.copyOfRange(strCards, 5, 10));
	}

	public boolean isPlayerOneWinner() {
		return GameResolverHelper.calculateGameResult(playerOneHand.getHandResult(), playerTwoHand.getHandResult())
				.equals(GameResult.PLAYER_ONE_WIN);
	}

}
