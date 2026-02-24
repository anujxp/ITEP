package com.example.snakeladder;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeAndLadderGame {
	private final Board board;
	private final IDice dice;
	private final Queue<Player> players;
	private Player winner;

	public SnakeAndLadderGame(Board board, IDice dice, List<Player> playerList) {
		this.board = board;
		this.dice = dice;
		this.players = new LinkedList<>(playerList);
	}

	public void start() {
		System.out.println("Game Started!");
		while (winner == null) {System.out.println();
			Player currentPlayer = players.poll();
			int roll = dice.roll();
			movePlayer(currentPlayer, roll);

			if (currentPlayer.getCurrentPosition() == board.getSize()) {
				winner = currentPlayer;
				System.out.println(winner.getName() + " wins the game!");
			} else {
				players.add(currentPlayer);
			}
		}
	}

	private void movePlayer(Player player, int roll) {
		int oldPos = player.getCurrentPosition();
		int newPos = oldPos + roll;

		// Rule: Must land exactly on the final square
		if (newPos > board.getSize()) {
			System.out.println(player.getName() + " rolled a " + roll + " but needs exactly "
					+ (board.getSize() - oldPos) + " to win. Staying at " + oldPos);
			return;
		}

		// Handle Environmental Hazards
		newPos = resolveJumps(newPos);

		player.setCurrentPosition(newPos);
		System.out.println(player.getName() + " rolled a " + roll + " and moved from " + oldPos + " to " + newPos);
	}

	private int resolveJumps(int position) {
		Cell currentCell = board.getCell(position);
		if (currentCell.getJump() != null) {
			Jump jump = currentCell.getJump();
			System.out.println("  Encounters " + jump.getHazardType() + " at " + jump.getStart() + "! Jumping to "
					+ jump.getEnd());
			return resolveJumps(jump.getEnd()); // Recursive in case hazards chain
		}
		return position;
	}
}