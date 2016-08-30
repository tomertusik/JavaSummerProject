package test;

import java.util.Scanner;

import algorithms.demo.Demo;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.StrategyDFS;

public class Test {

	private static Scanner in;

	public static void main(String[] args) {
		
		Demo tryDemo = new Demo(); // create a demo
		tryDemo.run(new GrowingTreeGenerator(new StrategyDFS() )); // test both search algorithms
		
		System.out.println("Press any key to continue..."); // pause the program
		in = new Scanner(System.in);
		String s = in.nextLine();
		System.out.println(s);
	}

}
