/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weka3;

import script.ScriptProccesor;

/**
 *
 * @author Skrzypek
 */
public class Weka3 {

    private static String fileName = "183672L2_2.arff";
    private static String destinationName = "183672L3_2.arff";
    private static String directory = "C:\\Users\\Skrzypek\\Desktop\\weka9";

    public static void main(String[] args) {
        ScriptProccesor scriptProccesor = new ScriptProccesor(directory, fileName, destinationName);
        scriptProccesor.procces();
    }

}
