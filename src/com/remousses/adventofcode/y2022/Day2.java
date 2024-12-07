package com.remousses.adventofcode.y2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.remousses.adventofcode.AbstractDay;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Day2 extends AbstractDay {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day2().readCode("/2022/day2.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
        int myPoint = 0;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	continue;
            }
            
            String[] split = line.split(" ");

            Shape player1 = PlayerShape.valueOf(split[0]).getShape();
            Shape player2 = PlayerShape.valueOf(split[1]).getShape();
            
            myPoint += player2.getPoint();
            
            if (player1.equals(player2)) {
            	myPoint += GamePoint.EQUALITY.getPoint();
            } else if (Shape.PAPER.equals(player1)) {
            	myPoint += extracted(player2, Shape.SCISSORS);
            } else if (Shape.ROCK.equals(player1)) {
            	myPoint += extracted(player2, Shape.PAPER);
            } else if (Shape.SCISSORS.equals(player1)) {
            	myPoint += extracted(player2, Shape.ROCK);
            }
        }

        System.out.println(myPoint);
	}

	private int extracted(Shape player2, Shape shapeWin) {
		return shapeWin.equals(player2) ? GamePoint.WIN.getPoint() : GamePoint.LOSS.getPoint();
	}
}

@AllArgsConstructor
@Getter
enum PlayerShape {
	A(Shape.ROCK),
	B(Shape.PAPER),
	C(Shape.SCISSORS),
	X(Shape.ROCK),
	Y(Shape.PAPER),
	Z(Shape.SCISSORS);

	private Shape shape;
	
}

@AllArgsConstructor
@Getter
enum Shape {
	ROCK(1),
	PAPER(2),
	SCISSORS(3);

	private int point;
	
}

@AllArgsConstructor
@Getter
enum GamePoint {
	LOSS(0),
	EQUALITY(3),
	WIN(6);
	
	private int point;
}
