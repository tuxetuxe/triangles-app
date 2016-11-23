package com.luis.triangles.services;

import static com.luis.triangles.domain.TriangleType.EQUILATERAL;
import static com.luis.triangles.domain.TriangleType.ISOSCELES;
import static com.luis.triangles.domain.TriangleType.SCALENE;

import java.math.BigDecimal;

import com.luis.triangles.domain.Triangle;
import com.luis.triangles.domain.TriangleType;
import com.luis.triangles.exceptions.TriangleNotValidException;

public class TriangleClassificationService {

    /**
     * Given a triangle, check what type of triangle it is given its side lengths.
     * 
     * @param triangle
     *            The triangle to analyze
     * @return An instance of TriangleType. Never null.
     */
    public TriangleType classifyBySideLength(Triangle triangle) {

        if (!triangle.isAPossibleTriangle()) {
            throw new TriangleNotValidException();
        }

        BigDecimal side1 = triangle.getSide1();
        BigDecimal side2 = triangle.getSide2();
        BigDecimal side3 = triangle.getSide3();

        TriangleType triangleType = SCALENE;
        
        if (side1.equals(side2) && side2.equals(side3)) {
            triangleType = EQUILATERAL;
        }else if (side1.equals(side2) || side2.equals(side3) || side1.equals(side3)) {
            triangleType = ISOSCELES;
        }
        return triangleType;
    }
}
