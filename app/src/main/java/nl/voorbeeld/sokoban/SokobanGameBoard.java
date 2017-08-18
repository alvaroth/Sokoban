package nl.voorbeeld.sokoban;

import nl.saxion.act.playground.model.GameBoard;

/**
 * The game board for Sokoban.
 * 
 * @author Jeffrey van der Klij
 */
public class SokobanGameBoard extends GameBoard {
	private static final int GAMEBOARD_WIDTH = 8;
	private static final int GAMEBOARD_HEIGHT = 9;

	/**
	 * Create a new game board.
	 */
	public SokobanGameBoard() {
		super(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);
	}

	@Override
	public void onEmptyTileClicked(int x, int y) {
		// Nothing to do in this game.
	}
	
	@Override
	public String getBackgroundImg(int mx, int my) {
		return "empty";
	}
	
}
