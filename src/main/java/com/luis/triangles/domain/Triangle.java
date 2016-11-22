package com.luis.triangles.domain;

import java.math.BigDecimal;

public class Triangle {

	BigDecimal side1;
	BigDecimal side2;
	BigDecimal side3;

	public Triangle(BigDecimal side1, BigDecimal side2, BigDecimal side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	/**
	 * Checks if this triangle can actually exist using:
	 *  * All sides must exist and have non negative and non zero lengths
	 *  * The triangle inequality principle must hold - principle (https://en.wikipedia.org/wiki/Triangle_inequality
	 * 
	 * @return false if this triangle can not exist
	 */
	public boolean isAPossibleTriangle() {

		// All sides nees to be defined
		if (side1 == null || side2 == null || side3 == null) {
			return false;
		}

		//All sides can't be negative or zero
		if (side1.compareTo(BigDecimal.ZERO) <= 0 || 
			side2.compareTo(BigDecimal.ZERO) <= 0 || 
			side3.compareTo(BigDecimal.ZERO) <= 0 ) {
			return false;
		}
		
		return side1.add(side2).compareTo(side3) >= 0 &&
	           side1.add(side3).compareTo(side2) >= 0 &&
		       side2.add(side3).compareTo(side1) >= 0;
	}

	public BigDecimal getSide1() {
		return side1;
	}

	public void setSide1(BigDecimal side1) {
		this.side1 = side1;
	}

	public BigDecimal getSide2() {
		return side2;
	}

	public void setSide2(BigDecimal side2) {
		this.side2 = side2;
	}

	public BigDecimal getSide3() {
		return side3;
	}

	public void setSide3(BigDecimal side3) {
		this.side3 = side3;
	}

	@Override
	public String toString() {
		return String.format("triangle: %d, %d, %d", side1, side2, side3);
	}


}
