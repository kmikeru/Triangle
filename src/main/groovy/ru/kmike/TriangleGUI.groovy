package ru.kmike;

import groovy.swing.SwingBuilder 
import groovy.beans.Bindable 
import javax.swing.*
import java.awt.*
import groovy.transform.CompileStatic
import ru.kmike.Triangle

import java.beans.PropertyChangeListener  

@CompileStatic
public class TriangleGUI {
    
    public static void main(String[] args){
        TriangleGUIModel model=new TriangleGUIModel()
        
        Closure updateAction = {
            try{
                BigDecimal a=model.getLengthA()
                BigDecimal b=model.getLengthB()
                BigDecimal c=model.getLengthC()
                
                if([a,b,c].every{it!=null}){
                    Triangle t=new Triangle(a,b,c)
                    model.message=t?.determineType()
                }else{
                    model.message='Triangle with specified sides cannot be created'
                }
            }catch(Exception e){
                model.message='error:'+e.message
            }
        }        
        TriangleView view=new TriangleView(model,updateAction)        
    }
            
}

class TriangleView{
    public SwingBuilder swingBuilder
    public TriangleView(TriangleGUIModel model, Closure updateAction){
        this.swingBuilder = new SwingBuilder()
        this.swingBuilder.edt{
            frame(title:"Hello Groovy Swing", 
                   defaultCloseOperation: JFrame.EXIT_ON_CLOSE,
                   size:[400,400],
                   show:true) {
                    panel(){                        
                        label("Enter triangle sides")
                        sideA = textField(columns:6, text: bind (target: model, targetProperty:"sideA",mutual: true))
                        sideB = textField(columns:6, text: bind (target: model, targetProperty:"sideB",mutual: true))
                        sideC = textField(columns:6, text: bind (target: model, targetProperty:"sideC",mutual: true))
                        button(text:"Update", actionPerformed: updateAction )
                        label(id: 'message', text: bind {model.message} )
                    }
        }
        }
    }       
}
class TriangleGUIModel{    
    @Bindable String sideA
    @Bindable String sideB
    @Bindable String sideC
    @Bindable String message='Enter triangle sides\' lengths and click the button'
    
    private BigDecimal getValidatedLength(String s){
        try{
            return new BigDecimal(s)
        }catch(Exception e){
            return null
        }
    }
    
    public BigDecimal getLengthA(){
        return getValidatedLength(this.sideA)
    }
    public BigDecimal getLengthB(){
        return getValidatedLength(this.sideB)
    }
    public BigDecimal getLengthC(){
        return getValidatedLength(this.sideC)
    }
    
}