package com.remousses.adventofcode.y2022;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.remousses.adventofcode.AbstractDay;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Day2Part2 extends AbstractDay {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day2Part2().readCode("/2022/day2.txt");
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

            ShapePart2 player1 = OpponnentShapePart2.valueOf(split[0]).getShape();
            MyShapePart2 playerShape2 = MyShapePart2.valueOf(split[1]);
            
            if (MyShapePart2.Y.equals(playerShape2)) {
            	myPoint += player1.getPoint() + GamePointPart2.EQUAL.getPoint();
            } else if (ShapePart2.PAPER.equals(player1)) {
            	myPoint += extracted(playerShape2, ShapePart2.ROCK, ShapePart2.SCISSORS);
            } else if (ShapePart2.ROCK.equals(player1)) {
            	myPoint += extracted(playerShape2, ShapePart2.SCISSORS, ShapePart2.PAPER);
            } else if (ShapePart2.SCISSORS.equals(player1)) {
            	myPoint += extracted(playerShape2, ShapePart2.PAPER, ShapePart2.ROCK);
            }
        }

        System.out.println(myPoint);
	}

	private int extracted(MyShapePart2 playerShape2, ShapePart2 shapeLoss, ShapePart2 shapeWin) {
		return MyShapePart2.X.equals(playerShape2) ?
				shapeLoss.getPoint() + GamePointPart2.LOSS.getPoint() :
				shapeWin.getPoint() + GamePointPart2.WIN.getPoint();
	}
}

@Getter
@AllArgsConstructor
enum OpponnentShapePart2 {
	A(ShapePart2.ROCK),
	B(ShapePart2.PAPER),
	C(ShapePart2.SCISSORS);
	
	private ShapePart2 shape;
}

@Getter
@AllArgsConstructor
enum MyShapePart2 {
	X(GamePointPart2.LOSS),
	Y(GamePointPart2.EQUAL),
	Z(GamePointPart2.WIN);
	
	private GamePointPart2 gamePoint;
}

@AllArgsConstructor
@Getter
enum ShapePart2 {
	ROCK(1),
	PAPER(2),
	SCISSORS(3);

	private int point;
	
}

@AllArgsConstructor
@Getter
enum GamePointPart2 {
	LOSS(0),
	EQUAL(3),
	WIN(6);
	
	private int point;
}
