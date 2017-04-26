package ru.kmike;
import groovy.transform.CompileStatic

@CompileStatic
public class Triangle {
    private BigDecimal a,b,c // Triangle sides. BigDecimal of arbitrary precision is used to simplify comparisons
    
    public Triangle(BigDecimal a, BigDecimal b, BigDecimal c){
        /** Check if the triangle with specified sides could exist */
        if( [a,b,c].any{it==null})
            throw new IllegalArgumentException('side cannot be null')
        else if( [a,b,c].any{it==0})
            throw new IllegalArgumentException('side cannot be zero')
        else if( [a,b,c].any{it<0})
            throw new IllegalArgumentException('side length cannot be negative')            
        else if( inequalitiesValidated(a,b,c)==false)
            throw new IllegalArgumentException('triangle inequalities violated')
        else{
            this.a=a
            this.b=b
            this.c=c
        }
    }
    
    /** Validate if triangle sides to triangle inequality rule 
     * @see https://en.wikipedia.org/wiki/Triangle_inequality */
    private boolean inequalitiesValidated(BigDecimal a, BigDecimal b, BigDecimal c){ 
        if( (a+b>c)&&
            (b+c>a)&&
            (c+a)>b ){
            return true            
        }
        return false        
    }
    
    public String determineType(){
        if( [a,b,c].every{it==a}) // all sides are equal
            return 'equilateral'
        if( [a,b,c].unique().size()==2) // only 2 different side lengths exist, so two sides are equal
            return 'isosceles'
        return 'scalene'
    }
    
    public String toString(){
        return 'Triangle['+a+','+b+','+c+']'
    }
}
