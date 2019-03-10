package main.game.helpers;

import java.util.List;

import main.game.enums.GameResult;
import main.game.models.Card;
import main.game.models.HandResult;

public class GameResolverHelper {

	public static GameResult calculateGameResult(HandResult playerOneResult, HandResult playerTwoResult) {
		GameResult resultType = compareResultTypes(playerOneResult, playerTwoResult);
		if (!resultType.equals(GameResult.DRAW))
			return resultType;
		// Hand Result Type Now Tied - Check Hand Winning Cards For Highest
		GameResult resultWinningHighest = compareHighestWinningCardsRank(playerOneResult, playerTwoResult);
		if (!resultWinningHighest.equals(GameResult.DRAW))
			return resultWinningHighest;
		// Players winning cards are equal - check kickers
		return compareRemainingCardsHighest(playerOneResult, playerTwoResult);
	}

	private static GameResult compareResultTypes(HandResult playerOneResult, HandResult playerTwoResult) {
		int resultTypeComparison = playerOneResult.getType().compareTo(playerTwoResult.getType());
		if (resultTypeComparison > 0)
			return GameResult.PLAYER_ONE_WIN;
		else if (resultTypeComparison < 0)
			return GameResult.PLAYER_TWO_WIN;
		else
			return GameResult.DRAW;
	}

	private static GameResult compareHighestWinningCardsRank(HandResult playerOneResult, HandResult playerTwoResult) {
		List<Card> playerOneCards = playerOneResult.getCards().getHandResultCards(),
				playerTwoCards = playerTwoResult.getCards().getHandResultCards();
		if (playerOneCards.size() != playerTwoCards.size())
			throw new IllegalStateException("Winning hand card sizes don't match, please debug me.");

		for (int i = 0; i < playerOneCards.size(); i++) {
			int resultWinningCardComparison = playerOneCards.get(i).compareTo(playerTwoCards.get(i));
			if (resultWinningCardComparison > 0)
				return GameResult.PLAYER_ONE_WIN;
			else if (resultWinningCardComparison < 0)
				return GameResult.PLAYER_TWO_WIN;
		}
		return GameResult.DRAW;
	}

	private static GameResult compareRemainingCardsHighest(HandResult playerOneResult, HandResult playerTwoResult) {
		List<Card> playerOneCards = playerOneResult.getCards().getRemainingCards(),
				playerTwoCards = playerTwoResult.getCards().getRemainingCards();
		if (playerOneCards.size() != playerTwoCards.size())
			throw new IllegalStateException("Remaining hand card sizes don't match, please debug me.");

		for (int i = 0; i < playerOneCards.size(); i++) {
			int resultRemainingCardComparison = playerOneCards.get(i).compareTo(playerTwoCards.get(i));
			if (resultRemainingCardComparison > 0)
				return GameResult.PLAYER_ONE_WIN;
			else if (resultRemainingCardComparison < 0)
				return GameResult.PLAYER_TWO_WIN;
		}
		throw new IllegalStateException("A game has resulted in a draw, please debug me.");
	}
}
