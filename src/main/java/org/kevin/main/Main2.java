package org.kevin.main;

import org.kevin.objects.entity.chemicalFormula;

/**
 * @author 18145
 * @version 1.0
 */
public class Main2 {
    public static void main(String[] args) {
        chemicalFormula chemicalFormula = org.kevin.objects.entity.chemicalFormula.getchemicalFormulaFromScannerWithDefaultTip();
        double[] arr = chemicalFormula.fromAnyMoleculeMassGetAllKindsOfMoleculeMass(1, 18);
        for (double v : arr) {
            System.out.println(v);
        }
    }
}
