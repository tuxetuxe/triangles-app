package com.luis.triangles.domain;

public enum TriangleType {

	EQUILATERAL("Equilateral"), 
	ISOSCELES("Isosceles"), 
	SCALENE("Scalene");

	private String displayValue;

	private TriangleType(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

}
