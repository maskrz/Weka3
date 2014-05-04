/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import java.io.File;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Test;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author Skrzypek
 */
public class ScriptProccesorIT {

    private static final String fileName = "test183672L2_2.arff";
    private static final String destinationName = "test183672L3_2.arff";
    private static final String directory = "C:\\Users\\Skrzypek\\Desktop\\weka9";
    ScriptProccesor scriptProcessor;

    @AfterClass
    public static void tearDownClass() {
        try {
            File file = new File(directory + "\\" + destinationName);

            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    /**
     * Test of procces method, of class ScriptProccesor.
     */
    @Test
    public void testProcces() {
        scriptProcessor = new ScriptProccesor(directory, fileName, destinationName);
        System.out.println("procces");
        scriptProcessor.procces();
        try {
            File createdFile = new File(directory + "\\" + destinationName);
            if (!createdFile.exists()) {
                fail("File has not been created!");
            }

            DataSource source = new DataSource(directory + "\\" + destinationName);
            Instances data = source.getDataSet();
            int instancesAmount = data.numInstances();
            Assert.assertEquals(3, instancesAmount);
            Instance record = data.firstInstance();
            Assert.assertEquals(9, record.numAttributes());
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

}
