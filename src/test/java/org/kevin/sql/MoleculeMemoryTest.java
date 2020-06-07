package org.kevin.sql;

import org.junit.jupiter.api.Test;
import org.kevin.objects.entity.Element;
import org.kevin.objects.entity.Molecule;

import java.io.IOException;
import java.sql.SQLException;

class MoleculeMemoryTest {

    @Test
    void checkAvailable() {
    }

    @Test
    void writeMolecule() {
        try {
            System.out.println("开始运行");
            for (int i = 50000; i <= 100000; i++) {
                MoleculeMemory moleculeMemory = new MoleculeMemory("root", "Kevin87461353");
                Molecule molecule = new Molecule();
                molecule.add(new Element(12), 2);
                boolean b = moleculeMemory.writeMolecule(molecule, String.valueOf(i), String.valueOf(i), "哈哈");
                System.out.println(i + ":" + b);
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

    }

    @Test
    void updateMolecule() {
    }

    @Test
    void readMoleculeByName() throws SQLException {
        MoleculeMemory moleculeMemory = new MoleculeMemory("root", "Kevin87461353");
        try {
            System.out.println(moleculeMemory.readMoleculeObjectByName("1", MoleculeMemory.ENGLISH_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}