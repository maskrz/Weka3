/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package script;

import weka.core.Instances;

/**
 *
 * @author Skrzypek
 */
public class ScriptProccesor {

    private final String directory;
    private final String fileName;
    private final String destinationName;
    private FileOpener fileOpener;
    private OperationsHandler operationsHandler;
    private FileSaver fileSaver;
    private Instances data;


    public ScriptProccesor(String directory, String fileName, String destinationName) {
        this.directory = directory;
        this.fileName = fileName;
        this.destinationName = destinationName;
        createServices();
    }

    public void procces() {
        openFile();
        handleOperations();
        saveFile();
    }

    private void openFile() {
        data = fileOpener.getInstanceFromFile(directory, fileName);
    }

    private void handleOperations() {
        data = operationsHandler.handleOperations(data);
    }

    private void saveFile() {
        fileSaver.saveFile(data, directory, destinationName);
    }

    private void createServices() {
        fileOpener = new FileOpener();
        operationsHandler = new OperationsHandler();
        fileSaver = new FileSaver();
    }
}
