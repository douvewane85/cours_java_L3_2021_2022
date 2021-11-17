/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.utils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author USER
 */
public class Validator {
     public static boolean validatorField(TextField field,Label error,String fieldName){
        boolean ok=true;
          if(field.getText().trim().isEmpty()){
             error.setText(fieldName+" est Obligatoire");
             error.setVisible(true);
             ok=false;
           }
        return ok;
    }
    
}
