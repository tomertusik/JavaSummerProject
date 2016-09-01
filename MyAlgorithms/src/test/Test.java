package test;

import java.util.Scanner;

import algorithms.demo.Demo;
import algorithms.mazeGenerators.SimpleMaze3DGenerator;

public class Test {

	private static Scanner in;

	public static void main(String[] args) {
		
		Demo tryDemo = new Demo(); // create a demo
		tryDemo.run(new SimpleMaze3DGenerator()); // test both search algorithms
		
		System.out.println("Press any key to continue..."); // pause the program
		in = new Scanner(System.in);
		String s = in.nextLine();
	}

}
