package com.luis.triangles;

import static com.luis.triangles.domain.TriangleType.*;

import spock.lang.*

import com.luis.triangles.services.TriangleService
import com.luis.triangles.domain.Triangle
import com.luis.triangles.domain.TriangleType
import com.luis.triangles.exceptions.TriangleNotValidException

class TriangleServiceTest extends Specification {

    TriangleService triangleService;

    def setup() {
        triangleService = new TriangleService()
    }
      
    @Unroll
    def "Test triangle classification {#side1,#side2,#side3} - #expectedResult"() {
        when:
        Triangle triangle = new Triangle(side1, side2, side3);
        TriangleType triangleType = triangleService.classify(triangle)
        
        then:
        triangleType == expectedResult
        
        where:
        side1 | side2 | side3 | expectedResult
        1     | 2     | 3     | SCALENE
        1.1   | 2     | 3     | SCALENE

        1     | 1     | 2     | ISOSCELES
        1     | 2     | 1     | ISOSCELES
        2     | 1     | 1     | ISOSCELES

        1.001 | 1.001 | 2     | ISOSCELES
        1.001 | 2     | 1.001 | ISOSCELES
        2     | 1.001 | 1.001 | ISOSCELES

        
        1     | 1     | 1     | EQUILATERAL
        1.001 | 1.001 | 1.001 | EQUILATERAL
        BigDecimal.valueOf(Double.MAX_VALUE) | BigDecimal.valueOf(Double.MAX_VALUE) | BigDecimal.valueOf(Double.MAX_VALUE) | EQUILATERAL
        BigDecimal.valueOf(Double.MIN_VALUE) | BigDecimal.valueOf(Double.MIN_VALUE) | BigDecimal.valueOf(Double.MIN_VALUE) | EQUILATERAL
    }
    
    @Unroll
    def "Test triangle classification throws exception on invalid input {#side1,#side2,#side3}"() {
        when:
        Triangle triangle = new Triangle(side1, side2, side3);
        TriangleType triangleType = triangleService.classify(triangle)
        
        then:
        thrown(TriangleNotValidException)
        
        where:
        side1 | side2 | side3 
        0     | 0     | 0    
        0     | 0     | 1    
        0     | 1     | 0    
        0     | 1     | 1    
        1     | 0     | 0    
        1     | 0     | 1    
        1     | 1     | 0    
        -1    | 1     | 1    
        1     | -1    | 1    
        1     | 1     | -1
        
        null  | null  | null 
        

    }
}