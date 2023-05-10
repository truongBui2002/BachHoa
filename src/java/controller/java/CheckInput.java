/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.java;

/**
 *
 * @author LENOVO
 */
public class CheckInput {
    public boolean isPosInt(String str){
        try {
            int number = Integer.parseInt(str);
            return number>0;
        } catch (NumberFormatException e) {
            return false;
        } 
    }
    public boolean isPosDouble(String str){
        try {
            double number = Double.parseDouble(str);
            return number>0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
