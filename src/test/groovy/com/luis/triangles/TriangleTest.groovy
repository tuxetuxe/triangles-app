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
    
    @Unroll
    def "Test triangle equality - {#triangleASide1,#triangleASide2,#triangleASide3} == {#triangleBSide1,#triangleBSide2,#triangleBSide3}  => #expectedResult"() {
        when:
        Triangle triangleA = new Triangle(triangleASide1, triangleASide2, triangleASide3);

        and:
        Triangle triangleB = new Triangle(triangleBSide1, triangleBSide2, triangleBSide3);
        
        then:
        triangleA.equals(triangleB) == expectedResult

        where:
        triangleASide1 | triangleASide2 | triangleASide3 | triangleBSide1 | triangleBSide2 | triangleBSide3 | expectedResult
        100            | 100            | 100           | 100            | 100            | 100           | true
        200            | 100            | 100           | 100            | 100            | 100           | false
        100            | 100            | 100           | 200            | 100            | 100           | false
        100            | 200            | 100           | 100            | 100            | 100           | false
        100            | 100            | 100           | 100            | 200            | 100           | false
        100            | 100            | 200           | 100            | 100            | 100           | false
        100            | 100            | 100           | 100            | 100            | 200           | false
        null           | 100            | 100           | null           | 100            | 100           | true
        null           | 100            | 100           | 100            | 100            | 100           | false
        100            | null           | 100           | 100            | null           | 100           | true
        100            | null           | 100           | 100            | 100            | 100           | false
        100            | 100            | null          | 100            | 100            | null          | true
        100            | 100            | null          | 100            | 100            | 100           | false
    }

    @Unroll
    def "Test triangle equality - with itself"() {
        when:
        Triangle triangleA = new Triangle(123, 123, 123);

        then:
        triangleA.equals(triangleA) == true
    }

    @Unroll
    def "Test triangle equality - other object type"() {
        when:
        Triangle triangleA = new Triangle(123, 123, 123);

        then:
        triangleA.equals("another object type") == false
    }

    @Unroll
    def "Test triangle equality - with null"() {
        when:
        Triangle triangleA = new Triangle(123, 123, 123);

        then:
        triangleA.equals(null) == false
    }

}