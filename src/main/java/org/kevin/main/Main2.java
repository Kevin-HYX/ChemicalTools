package org.kevin.main;

import org.kevin.objects.entity.ChemicalFormula;

/**
 * @author 18145
 * @version 1.0
 */
public class Main2 {
    public static void main(String[] args) {
        ChemicalFormula chemicalFormula = ChemicalFormula.getChemicalFormulaFromScannerWithDefaultTip();
        double[] arr = chemicalFormula.fromAnyMoleculeMassGetAllKindsOfMoleculeMass(1, 18);
        for (double v : arr) {
            System.out.println(v);
        }
    }
}
