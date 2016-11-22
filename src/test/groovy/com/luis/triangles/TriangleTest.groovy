package com.luis.triangles;

import spock.lang.*

import com.luis.triangles.domain.Triangle


class TriangleTest extends Specification {

       
    @Unroll
    def "Test triangle possibility check {#side1,#side2,#side3} - #expectedResult"() {
        when:
        Triangle triangle = new Triangle(side1, side2, side3);
        boolean triangleValid = triangle.isAPossibleTriangle()
        
        then:
        triangleValid == expectedResult
        
        where:
        side1 | side2 | side3 | expectedResult
        0     | 0     | 0     | false
        0     | 0     | 1     | false
        0     | 1     | 0     | false
        0     | 1     | 1     | false
        1     | 0     | 0     | false
        1     | 0     | 1     | false
        1     | 1     | 0     | false
        -1    | 1     | 1     | false
        1     | -1    | 1     | false
        1     | 1     | -1    | false
        10    | 1     | 1     | false
        1     | 1     | 1     | true
        1     | 1     | 1.4   | true
        Double.MAX_VALUE | Double.MAX_VALUE | Double.MAX_VALUE | true
        Double.MIN_VALUE | Double.MIN_VALUE | Double.MIN_VALUE | true
    }
    
}