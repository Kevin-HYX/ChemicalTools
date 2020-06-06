package org.kevin.objects.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 一个类，用于表示化学方程式的等号两边，并在内部给出不同的计算方法
 *
 * @author 18145
 * @version 1.0
 */


public class ChemicalFormula implements Serializable {
    public static final long serialVersionUID = 1;
    /**
     * 用于输入化学式的扫描器
     *
     * @see Scanner
     */
    @Deprecated
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * 化学方程式的等号的左边
     */
    private final ArrayList<Molecule> moleculesLeft;
    /**
     * 化学方程式的等号右边
     */
    private final ArrayList<Molecule> moleculesRight;

    /**
     * 一个空的构造器，生产一个空的化学式以便后续往里面添加元素
     */
    public ChemicalFormula() {
        this.moleculesLeft = new ArrayList<>();
        this.moleculesRight = new ArrayList<>();
    }

    /**
     * 从给定的ArrayList生成化学方程式
     *
     * @param moleculesLeft  方程式等号的左边
     * @param moleculesRight 方程式等号的右边
     */


    public ChemicalFormula(ArrayList<Molecule> moleculesLeft, ArrayList<Molecule> moleculesRight) {
        this.moleculesLeft = moleculesLeft;
        this.moleculesRight = moleculesRight;
    }

    /**
     * 从控制台中，使用默认的提示符以交互式读取一个化学方程式
     *
     * @return 一个化学方程式
     * @see ChemicalFormula
     */
    @Deprecated
    public static ChemicalFormula getChemicalFormulaFromScannerWithDefaultTip() {
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
            arrayListLeft.add(Molecule.getAMoleCuteFromScanner("准备上输入化学式左边第" + i + "个化学式，输入完一个化学式以后，请以end结尾"));
        }
        for (int i = 1; i <= right; i++) {
            arrayListRight.add(Molecule.getAMoleCuteFromScanner("准备上输入化学式右边第" + i + "个化学式，输入完一个化学式以后，请以end结尾"));
        }
        return new ChemicalFormula(arrayListLeft, arrayListRight);
    }

    /**
     * 向化学方程式的右边添加化学式
     *
     * @param molecule 需要添加的化学式
     */
    public void addRight(Molecule molecule) {
        this.moleculesRight.add(molecule);
    }

    /**
     * 向化学方程式的左边添加化学式
     *
     * @param molecule 需要添加的化学式
     */
    public void addLeft(Molecule molecule) {
        this.moleculesLeft.add(molecule);
    }

    /**
     * 同时向化学方程式的两边添加化学式
     *
     * @param left  需要添加到左边的化学式
     * @param right 需要添加到右边的化学式
     */
    public void add(Molecule left, Molecule right) {
        addRight(right);
        addLeft(left);
    }

    public ArrayList<Molecule> getMoleculesLeft() {
        return moleculesLeft;
    }

    public ArrayList<Molecule> getMoleculesRight() {
        return moleculesRight;
    }

    /**
     * 得到化学方程式左边有几个化学式
     *
     * @return 左边的计数值
     */

    public int getLeftLength() {
        return moleculesLeft.size();
    }

    /**
     * 得到化学方程式右边有几个化学式
     *
     * @return 右边的计数值
     */
    public int getRightLength() {
        return moleculesRight.size();
    }

    /**
     * 得到化学方程式总共
     *
     * @return 总共的计数值
     */
    public int getAllLength() {
        return moleculesRight.size() + moleculesLeft.size();
    }

    /**
     * 此方法已经弃用,新的方法位于ChemicalFormula中
     */
    @Deprecated
    public double[] fromAnyMoleculeMassGetAllKindsOfMoleculeMass(int location, double mass) {
        return fromAnyMoleculeMassGetAllKindsOfMoleculeMass(location, new Fraction(mass));
    }

    /**
     * 此方法已经弃用,新的方法位于ChemicalFormula中
     */
    @Deprecated
    public double[] fromAnyMoleculeMassGetAllKindsOfMoleculeMass(int location, Fraction mass) {
        ArrayList<Molecule> moleculeArrayList = new ArrayList<>();
        moleculeArrayList.addAll(moleculesLeft);
        moleculeArrayList.addAll(moleculesRight);
        Fraction l = new Fraction(moleculeArrayList.get(location - 1).getMassForStudent());
        double[] result = new double[getAllLength()];
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
