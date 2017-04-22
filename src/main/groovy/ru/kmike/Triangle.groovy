package ru.kmike;
import groovy.transform.CompileStatic

@CompileStatic
public class Triangle {
    private BigDecimal a,b,c // triangle sides. BigDecimal of arbitrary precision is used to simplify comparisons
    
    public Triangle(BigDecimal a, BigDecimal b, BigDecimal c){
        validateSides(a, b, c)            
        this.a=a
        this.b=b
        this.c=c
    }
    private boolean validateSides(BigDecimal a, BigDecimal b, BigDecimal c){
        if( [a,b,c].any{it==null})
            throw new IllegalArgumentException('side cannot be null')
        if( [a,b,c].any{it==0})
            throw new IllegalArgumentException('side cannot be zero')
        if( [a,b,c].any{it<0})
            throw new IllegalArgumentException('side length cannot be negative')            
        if( validateInequalities(a,b,c)==false)
            throw new IllegalArgumentException('triangle inequalities violated')
    }
    
    private boolean validateInequalities(BigDecimal a, BigDecimal b, BigDecimal c){ // https://en.wikipedia.org/wiki/Triangle_inequality
        if( (a+b>c)&&
            (b+c>a)&&
            (c+a)>b ){
            return true            
        }
        return false        
    }
    
    public String determineType(){
        if( [a,b,c].every{it==a})
            return 'equilateral'
        if( [a,b,c].unique().size()==2)
            return 'isosceles'
        return 'scalene'
    }
    
    public String toString(){
        return 'Triangle['+a+','+b+','+c+']'
    }
}
