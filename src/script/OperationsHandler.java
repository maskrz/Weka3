/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 *
 * @author Skrzypek
 */
public class OperationsHandler {

    private static final double CRITICAL_CREDIT_AMOUNT = 900;
    private static final String REMOVE_STATUS_NAME = "odmowa";
    private static final int STATUS_ATTRIBUTE_NUMBER = 0;
    private static final int CREDIT_AMOUNT_ATTRIBUTE_NUMBER = 1;
    private static final String TO_REMOVE_ATTRIBUTE_NUMBER = "1";
    private static final String REMOVE_SYMBOL = "-R";

    private Instances data;
    private int current;
    Remove remove;

    public Instances handleOperations(Instances data) {
        this.data = data;
        removeRecords();
        return removeAttribute();
    }

    private void removeRecords() {
        removeDeniedStatusAndLargeCredit();
    }

    private void removeDeniedStatusAndLargeCredit() {
        for (current = 0; current < data.numInstances(); current++) {
            getRecordAndRemoveIfNecessary();
        }
    }

    private void getRecordAndRemoveIfNecessary() {
        Instance record = getCurrentRecord();
        String creditStatus = getCreditStatus(record);
        double creditAmount = getCreditAmount(record);
        removeIfNecessary(creditStatus, creditAmount);
    }

    private Instance getCurrentRecord() {
        return data.instance(current);
    }

    private String getCreditStatus(Instance record) {
        return record.attribute(STATUS_ATTRIBUTE_NUMBER).value(
                (int) record.value(STATUS_ATTRIBUTE_NUMBER));
    }

    private double getCreditAmount(Instance record) {
        return record.value(CREDIT_AMOUNT_ATTRIBUTE_NUMBER);
    }

    private void removeIfNecessary(String creditStatus, double creditAmount) {
        if (REMOVE_STATUS_NAME.equals(creditStatus)
                || creditAmount > CRITICAL_CREDIT_AMOUNT) {
            data.delete(current);
            current--;
        }
    }

    private Instances removeAttribute() {
        createInstanceAndSetOptions();
        Instances newData = doRemove(remove);
        return newData;
    }

    private void createInstanceAndSetOptions() {
        remove = createRemoveInstance();
        String[] options = createOptions();
        setOptionsForRemove(remove, options);
    }

    private Remove createRemoveInstance() {
        return new Remove();
    }

    private String[] createOptions() {
        return new String[]{REMOVE_SYMBOL, TO_REMOVE_ATTRIBUTE_NUMBER};
    }

    private void setOptionsForRemove(Remove remove, String[] options) {
        try {
            remove.setOptions(options);
            remove.setInputFormat(data);
        } catch (Exception ex) {
            Logger.getLogger(OperationsHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Instances doRemove(Remove remove) {
        try {
            return Filter.useFilter(data, remove);
        } catch (Exception ex) {
            Logger.getLogger(OperationsHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
