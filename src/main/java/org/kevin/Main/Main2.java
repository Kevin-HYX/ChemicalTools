package org.kevin.Main;

import org.kevin.objects.elements.chemicalFormula;

/**
 * @author 18145
 * @version 1.0
 */
public class Main2 {
    public static void main(String[] args) {
        chemicalFormula chemicalFormula = org.kevin.objects.elements.chemicalFormula.getchemicalFormulaFromScannerWithDefaultTip();
        double[] arr = chemicalFormula.fromAnyMoleculeMessGetAllKindsOfMoleculeMess(1, 18);
        for (double v : arr) {
            System.out.println(v);
        }
    }
}
