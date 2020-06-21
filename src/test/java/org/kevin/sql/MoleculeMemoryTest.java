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
        Thread thread = new Thread(() -> {
            try {
                System.out.println("开始运行");
                MoleculeMemory moleculeMemory = new MoleculeMemory("root", "Kevin87461353");
                for (int i = 90000; i <= 100000; i++) {
                    Molecule molecule = new Molecule();
                    molecule.add(new Element(12), 2);
                    boolean b = moleculeMemory.batchWriteMolecule(molecule, String.valueOf(i), String.valueOf(i), "哈哈");
                }
                moleculeMemory.executeBatch();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        });
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("开始运行");
                MoleculeMemory moleculeMemory = new MoleculeMemory(ConnectionManager.getNewConnection("root", "Kevin87461353"));
                for (int i = 6000; i <= 70000; i++) {
                    Molecule molecule = new Molecule();
                    molecule.add(new Element(12), 2);
                    boolean b = moleculeMemory.batchWriteMolecule(molecule, String.valueOf(i), String.valueOf(i), "哈哈");
                }
                moleculeMemory.executeBatch();
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        });
        thread1.start();
        thread.start();

    }

    @Test
    void updateMolecule() {
    }

}