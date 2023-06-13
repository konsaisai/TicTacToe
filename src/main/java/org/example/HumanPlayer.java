package org.example;

import java.util.Optional;
import java.util.Scanner;
import static org.example.Main.*;

public class HumanPlayer implements Player {
    @Override
    public Optional<Position> getPosition(Board board, Cell cell) {
        String red    = "\u001b[00;31m";
        Position position;
        //入力チェック
        do {
            System.out.println(cell + ":Input number (Row Col)");
            System.out.println(" Ex. 1 1");
            Scanner in = new Scanner(System.in);
            String num = in.nextLine();
            try {
                position = parsePosition(num, board.getRowLength());
                if (!board.positionCheck(position)) {
                    position = null;
                    System.out.println("This cell is already selected!!" + red);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Input Error!!!" + red);
                System.out.println(e.getMessage());
                position = null;
            }
        }while (position == null);

        //反映
        return Optional.ofNullable(position);
    }
}
