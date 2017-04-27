package mnpl.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mnpl.logic.Board;
import mnpl.logic.Player;
import mnpl.logic.Property;
import mnpl.logic.Purchasable;
import mnpl.logic.Square;
import mnpl.logic.Utility;

public class Main {
	public static void main(String[] args) {
		Board b = new Board();
		Scanner sc = new Scanner(System.in);
		int i = 0;
		while (true) {
			printBoard(b);
			Player currentPlayer = Board.getPlayer(i);
			char inputChar;
			int inputInt, propertyIt = 1;
			if (!currentPlayer.getAquired().isEmpty() && (Board.getHotels() > 0 || Board.getHouses() > 0)) {
				System.out.println("Do you wish to buy houses or hotels? (y/n)");
				inputChar = sc.next().charAt(0);
				if (inputChar == 'y' || inputChar == 'Y') {
					for (Integer playerProperty : currentPlayer.getAquired()) {
						System.out.println(propertyIt + ": " + Board.getSquare(playerProperty));
						propertyIt++;
					}
					propertyIt--;
					System.out.println("Select a property (1-" + propertyIt + ")");
					do {
						inputInt = sc.nextInt();
					} while (inputInt > propertyIt || inputInt < 1);
					propertyIt = inputInt;
					System.out.println("How many houses? (hotel is 5)");
					inputInt = sc.nextInt();
					if (inputInt == 5 && Board.getHotels() > 0) {

					}
				}
			}

			int turnsRemaining = 1;
			int doublesOld = 0;
			int doublesNew = 0;
			while (turnsRemaining > 0 && doublesNew != -1) {
				System.out.println(currentPlayer.getName());
				turnsRemaining--;
				doublesNew = currentPlayer.play(doublesOld);
				if (doublesNew > doublesOld)
					turnsRemaining++;
				doublesOld = doublesNew;
				System.out.println("Dice roll: " + currentPlayer.getDiceRoll() + " (" + currentPlayer.getDice()[0]
						+ " + " + currentPlayer.getDice()[1] + ")");
				System.out.println("Total double throws: " + doublesNew);
				System.out.println("Balance: " + currentPlayer.getBalance());

				Square currentSquare = Board.getSquare(currentPlayer.getPosition());
				System.out.println("You land on " + currentSquare.getTitle() + '!');
				System.out.println(currentSquare);
				if (currentSquare instanceof Purchasable) {
					if (!((Purchasable) currentSquare).isOwned()) {
						System.out.println(currentSquare.getTitle() + " currently has no owner!");
						System.out.println("Do you buy it? (y/n)");
						inputChar = sc.next().charAt(0);
						if (inputChar == 'y' || inputChar == 'Y') {
							currentPlayer.purchase();
							System.out.println("Congratulations on your purchase!");
							sc.nextLine();
						}

					} else if (currentSquare instanceof Utility) {
						System.out.println(
								"You pay " + ((Purchasable) currentSquare).getRent() * currentPlayer.getDiceRoll());
					} else {
						System.out.println("You pay " + ((Purchasable) currentSquare).getRent());
					}
				}

				sc.nextLine();
			}
			i++;
			i %= 2;
		}
	}

	public static void printBoard(Board b) {
		char[] head;
		List<Square> squares = b.getSquares();
		List<Integer> playersPos = new ArrayList<Integer>();
		for (Player p : b.getPlayers()) {
			playersPos.add(p.getPosition());
		}

		for (int i = 0; i < squares.size(); i++) {
			head = "_ _\t: ".toCharArray();
			if (playersPos.get(0) == i) {
				head[0] = '1';
			}
			if (playersPos.get(0) == i) {
				head[2] = '2';
			}
			System.out.println(new String(head) + i + ", " + Board.getSquare(i));
		}
	}
}
