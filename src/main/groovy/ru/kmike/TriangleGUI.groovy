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
        model.sideA='5'
        Closure updateAction = { println 'button clicked!'
            try{                
                Triangle t=new Triangle(model.lengthA(),model.lengthB(),model.lengthC())
                model.message=t.determineType()                
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
    
    public BigDecimal lengthA(){
        return new BigDecimal(this.sideA)
    }
    public BigDecimal lengthB(){
        return new BigDecimal(this.sideB)
    }
    public BigDecimal lengthC(){
        return new BigDecimal(this.sideC)
    }
}