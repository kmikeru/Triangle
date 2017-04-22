import spock.lang.*
import ru.kmike.Triangle

class TriangleSpec extends Specification{
 
    def "triangle with all sides defined could be created"(){
        when:
        def o=new Triangle(1.0,1.0,1.0)
        then:
        o.class==Triangle.class
    }
    
    def "triangle with zero side cannot be created"(){
        when:
        def o=new Triangle(1.0d,1.0d,0.0d)
        then:
        thrown(IllegalArgumentException)        
    }
    def "triangle with negative side length cannot be created"(){
        when:
        def o=new Triangle(1.0d,1.0d,-1.0d)
        then:
        thrown(IllegalArgumentException)
    }

    def "validate triangle inequality - correct triangle sides"(){
        when:
        def obj=new Triangle(x,y,z)
        then:
        obj!=null
        
        where:
        x|y|z
        3|4|5
        
    }
    
    def "validate triangle inequality - incorrect triangle sides"(){
        when:
        def obj=new Triangle(x,y,z)
        then:
        thrown(IllegalArgumentException)
        
        where:
        x|y|z
        3|5|10
        1|2|9
    }
    
    def "determine triangle type"(){
        when:
        def obj=new Triangle(a,b,c)
        then:
        obj.determineType()==type
        
        where:
        a|b|c|type
        3|3|3|'equilateral'
        3.0000000000000000001|3.0000000000000000001|3.0000000000000000001|'equilateral'
        3.0|3.0|3.0000000000000000001|'isosceles'
        3|4|5|'scalene'
    }
    
}