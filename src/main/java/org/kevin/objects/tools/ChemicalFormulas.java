package org.kevin.objects.tools;

import org.kevin.objects.entity.ChemicalFormula;
import org.kevin.objects.entity.Fraction;
import org.kevin.objects.entity.Molecule;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author 18145
 * @version 1.0
 */
public class ChemicalFormulas {
    public static Scanner scanner = new Scanner(System.in);

    public synchronized static boolean init() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
            return true;
        } else {
            scanner = new Scanner(System.in);
            return false;
        }
    }

    /**
     * 从控制台中，使用默认的提示符以交互式读取一个化学方程式
     *
     * @return 一个化学方程式
     * @see ChemicalFormula
     */
    public synchronized static ChemicalFormula getChemicalFormulaFromScannerWithDefaultTip() {
        int left;
        int right;
        while (true) {
            try {
                System.out.println("该化学方程式中，左右两边各有几个化学式？");
                System.out.print("左边 =>");
                left = scanner.nextInt();
                System.out.print("右边 =>");
                right = scanner.nextInt();
                if (left <= 0 || right <= 0) {
                    System.out.println("检查你的输入");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("检查你的输入");
                /*
                Scanner出错的时候，会导致错误的消息不被捕获。仍遗留在缓冲区中，这样就会无限的报错
                加入一个nextLine()以后，就会将错误消息抵消，从而提高程序稳定
                 */
                scanner.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<Molecule> arrayListLeft = new ArrayList<>(left);
        ArrayList<Molecule> arrayListRight = new ArrayList<>(right);
        for (int i = 1; i <= left; i++) {
            arrayListLeft.add(Molecules.getAMoleCuteFromScanner("准备上输入化学式左边第" + i + "个化学式，输入完一个化学式以后，请以end结尾"));
        }
        for (int i = 1; i <= right; i++) {
            arrayListRight.add(Molecules.getAMoleCuteFromScanner("准备上输入化学式右边第" + i + "个化学式，输入完一个化学式以后，请以end结尾"));
        }
        return new ChemicalFormula(arrayListLeft, arrayListRight);
    }

    public double[] fromAnyMoleculeMassGetAllKindsOfMoleculeMass(ChemicalFormula chemicalFormula, int location, double mass) {
        return fromAnyMoleculeMassGetAllKindsOfMoleculeMass(chemicalFormula, location, new Fraction(mass));
    }

    public double[] fromAnyMoleculeMassGetAllKindsOfMoleculeMass(ChemicalFormula chemicalFormula, int location, Fraction mass) {
        ArrayList<Molecule> moleculeArrayList = new ArrayList<>();
        moleculeArrayList.addAll(chemicalFormula.getMoleculesLeft());
        moleculeArrayList.addAll(chemicalFormula.getMoleculesRight());
        Fraction l = new Fraction(moleculeArrayList.get(location - 1).getMassForStudent());
        double[] result = new double[chemicalFormula.getAllLength()];
        for (int i = 0; i < moleculeArrayList.size(); i++) {
            if (i == location - 1) {
                result[i] = mass.doubleValue();
                continue;
            }
            Fraction f = new Fraction(moleculeArrayList.get(i).getMassForStudent());
            f.multiply(mass);
            f.divide(l);
            result[i] = f.doubleValue();
        }
        return result;
    }

}
