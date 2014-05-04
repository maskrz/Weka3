/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package script;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author Skrzypek
 */
public class FileOpener {

    public Instances getInstanceFromFile(String directory, String fileName) {
        DataSource source = openSource(directory, fileName);
        try {
            return source.getDataSet();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private DataSource openSource(String directory, String fileName) {
        try {
            return new DataSource(directory + "\\" + fileName);
        } catch (Exception ex) {
            System.out.println("Unable to open file");
        }
        return null;
    }


}
