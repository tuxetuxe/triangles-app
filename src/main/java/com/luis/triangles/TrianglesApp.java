package com.luis.triangles;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.luis.triangles.domain.Triangle;
import com.luis.triangles.domain.TriangleType;
import com.luis.triangles.services.TriangleService;

/**
 * Main command line application entry point.
 */
public class TrianglesApp {

	private TriangleService triangleService = new TriangleService();

	public static void main(String[] args) throws Exception {
		TrianglesApp trianglesApp = new TrianglesApp();
		trianglesApp.execute(args);
	}
	
	private void execute(String[] args) {
		Options options = buildCommandLineOptions();

		List<BigDecimal> sideLengths = parseCommandLineOptions(options, args);

		executeCommand(sideLengths);
	}

	/**
	 * Uses apache commons cli to build the command line options definition
	 * 
	 * @return A Options object populated with the available cli parameters
	 */
	private Options buildCommandLineOptions() {
		Option sideOption = Option.builder("s")
								  .longOpt("sides")
								  .argName("side length")
								  .numberOfArgs(3)
								  .desc("three triangle side lengths")
								  .required()
								  .build();

		Options options = new Options();
		options.addOption(sideOption);
		return options;
	}

	/**
	 * Parses a list of arguments (typically sent from the main method) into a
	 * list of lengths in the triangle sides. If the arguments are invalid, a
	 * help message will be printed in the stdout.
	 */
	private List<BigDecimal> parseCommandLineOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, args);
			
			String[] sidesStringValues = cmd.getOptionValues("sides");
			
			return Arrays.stream(sidesStringValues)
		                 .map(BigDecimal::new)
		                 .collect(Collectors.toList());
		} catch (ParseException e) {
			// Ups! Something went wrong, lets print the help.
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("triangles-app", options);
			System.exit(1);
		}
		// not really needed because the System.exit call above, but the compiler needs a return statement here
		return null;
	}

	/**
	 * Given a list of values checks if it is a valid triangle and it is, prints
	 * the triangle type.
	 * 
	 * @param sides
	 *            List of values that represent the triangle sides
	 */
	private void executeCommand(List<BigDecimal> sides) {
		if (sides == null || sides.size() < 3) {
			System.out.println("Invalid number of side length. Need to be exaclty 3");
		}

		Triangle triangle = new Triangle(sides.get(0), sides.get(1), sides.get(2));

		if (triangle.isAPossibleTriangle()) {
			TriangleType type = triangleService.classify(triangle);
			System.out.println("Triangle type: " + type.getDisplayValue());
		} else {
			System.out.println("The triangle can not exist");
		}
	}
}