package org.kevin.SQL;

import org.junit.jupiter.api.Test;
import org.kevin.objects.entity.chemicalFormula;
import org.kevin.objects.entity.element;
import org.kevin.objects.entity.molecule;

class chemicalFormulaMemoryTest {

    @Test
    void check() {
    }

    @Test
    void writeChemicalFormula() {
        chemicalFormulaMemory chemicalFormulaMemory = new chemicalFormulaMemory("root", "Kevin87461353");
        chemicalFormula chemicalFormula = new chemicalFormula();
        molecule moleculeL = new molecule();
        moleculeL.add(new element(12), 2);
        molecule moleculeR = new molecule();
        moleculeR.add(new element(78), 3);
        chemicalFormula.addLeft(moleculeL);
        chemicalFormula.addRight(moleculeR);
        chemicalFormulaMemory.writeChemicalFormula(chemicalFormula, "ABC", "EDV = s", "haha", "ee", org.kevin.SQL.chemicalFormulaMemory.DO_REWRITE);
    }
}