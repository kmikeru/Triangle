package ru.kmike;

import groovy.transform.CompileStatic
import ru.kmike.Triangle

@CompileStatic
public class TriangleCalculator {
    public static void main(String[] args) {
        BigDecimal[] sides=parseArgs(args)
        if(sides==null){
            println 'Exactly 3 numeric arguments must be given'
        }else{
            try{
                Triangle t=new Triangle(sides[0],sides[1],sides[2])
                println t.determineType()
            }catch(Exception e){
                println 'error:'+e.message
            }
        }
    }
    
    private static BigDecimal[] parseArgs(String[] args){
        if(args.size()!=3)
            return null
        try{
            BigDecimal a=args[0] as BigDecimal
            BigDecimal b=args[1] as BigDecimal
            BigDecimal c=args[2] as BigDecimal
            return [a,b,c] as BigDecimal[]
        }catch(Exception e){
            return null
        }
    }
}
