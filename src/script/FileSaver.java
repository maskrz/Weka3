/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instances;

/**
 *
 * @author Skrzypek
 */
class FileSaver {
    String newLine = System.getProperty("line.separator");
    StringBuilder sb;

    public void saveFile(Instances data, String directory, String destinationName) {
        try {
            PrintWriter printWriter = new PrintWriter(directory + "//" + destinationName);
            printWriter.print(buildFile(data));
            printWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(FileSaver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String buildFile(Instances data) {
        String result = "@relation " + data.relationName() + newLine + newLine;
        sb = new StringBuilder(result);
        appendAttributes(data);
        appendRecords(data);
        return sb.toString();
    }

    private void appendAttributes(Instances data) {
        int attributesAmount = data.numAttributes();
        for(int i = 0; i < attributesAmount; i++) {
            sb.append(data.attribute(i) + newLine);
        }
    }

    private void appendRecords(Instances data) {
        sb.append(newLine + "@Data" + newLine);
        int recordsAmount = data.numInstances();
        for(int i = 0; i < recordsAmount; i++) {
            sb.append(data.instance(i) + newLine);
        }
    }
}
