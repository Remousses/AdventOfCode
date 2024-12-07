package com.remousses.adventofcode.y2024;

import com.remousses.adventofcode.AbstractDay;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part2 extends AbstractDay {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Day3Part2().readCode("/2024/day3.txt");
	}
	
	@Override
	protected void writeCode(final BufferedReader br) throws NumberFormatException, IOException {
		String line;
		Integer total = 0;
		Pattern pattern = Pattern.compile("(?:do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\))");
		boolean mulEnabled = true;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) {
            	break;
            }
			Matcher matcher = pattern.matcher(line);
			// Track the state of whether mul is enabled (true) or disabled (false)

			while (matcher.find()) {
				String val = matcher.group();
				if ("don't()".equals(val)) {
					mulEnabled = false;
					continue;
				} else if ("do()".equals(val)) {
					mulEnabled = true;
					continue;
				}
				if (mulEnabled) {
					// Extraire les nombres de mul(X,Y)
					int firstNum = Integer.parseInt(matcher.group(1));
					int secondNum = Integer.parseInt(matcher.group(2));
					total += firstNum * secondNum; // Ajouter le produit à la somme
				}
			}
		}


		System.out.println("total : " + total);
	}

	private static void test() {
		String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";

		// Définir les expressions régulières pour mul(), do() et don't()
		String regex = "(?:do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\))";

		// Créer un Matcher pour les instructions mul()
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		// Initialiser mulEnabled à true, car les mul() sont activés au départ
		boolean mulEnabled = true;

		// Variable pour stocker la somme des produits
		long sum = 0;

		// Traiter d'abord les instructions do() et don't() pour ajuster l'état de mulEnabled



		// Ensuite, traiter les mul(X, Y) en fonction de l'état actuel de mulEnabled


		// Afficher le résultat final
		System.out.println("La somme des multiplications valides est : " + sum);
	}
}
