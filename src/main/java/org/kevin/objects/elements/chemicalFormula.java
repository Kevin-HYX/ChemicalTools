package org.kevin.objects.elements;

import org.kevin.tools.fraction;

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


public class chemicalFormula implements Serializable {
    public static final long serialVersionUID = 1;

    /**
     * 用于输入化学式的扫描器
     *
     * @see Scanner
     */
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * 化学方程式的等号的左边
     */
    private ArrayList<molecule> moleculesLeft;
    /**
     * 化学方程式的等号右边
     */
    private ArrayList<molecule> moleculesRight;

    /**
     * 一个空的构造器，生产一个空的化学式以便后续往里面添加元素
     */
    public chemicalFormula() {

    }

    /**
     * 从给定的ArrayList生成化学方程式
     *
     * @param moleculesLeft  方程式等号的左边
     * @param moleculesRight 方程式等号的右边
     */


    public chemicalFormula(ArrayList<molecule> moleculesLeft, ArrayList<molecule> moleculesRight) {
        this.moleculesLeft = moleculesLeft;
        this.moleculesRight = moleculesRight;
    }

    /**
     * 从控制台中，使用默认的提示符以交互式读取一个化学方程式
     *
     * @return 一个化学方程式
     * @see chemicalFormula
     */
    public static chemicalFormula getchemicalFormulaFromScannerWithDefaultTip() {
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
        ArrayList<molecule> arrayListLeft = new ArrayList<>(left);
        ArrayList<molecule> arrayListRight = new ArrayList<>(right);
        for (int i = 1; i <= left; i++) {
            arrayListLeft.add(molecule.getAMoleCuteFromScanner("准备上输入化学式左边第" + i + "个化学式，输入完一个化学式以后，请以end结尾"));
        }
        for (int i = 1; i <= right; i++) {
            arrayListRight.add(molecule.getAMoleCuteFromScanner("准备上输入化学式右边第" + i + "个化学式，输入完一个化学式以后，请以end结尾"));
        }
        return new chemicalFormula(arrayListLeft, arrayListRight);
    }

    public void addRight(molecule molecule) {
        this.moleculesRight.add(molecule);
    }

    public void addLeft(molecule molecule) {
        this.moleculesLeft.add(molecule);
    }

    public int getLeftLength() {
        return moleculesLeft.size();
    }

    public int getRightLength() {
        return moleculesRight.size();
    }

    public int getAllLength() {
        return moleculesRight.size() + moleculesLeft.size();
    }

    public double[] fromAnyMoleculeMessGetAllKindsOfMoleculeMess(int location, double mess) {
        return fromAnyMoleculeMessGetAllKindsOfMoleculeMess(location, new fraction(mess));
    }

    public double[] fromAnyMoleculeMessGetAllKindsOfMoleculeMess(int location, fraction mess) {
        ArrayList<molecule> moleculeArrayList = new ArrayList<>();
        moleculeArrayList.addAll(moleculesLeft);
        moleculeArrayList.addAll(moleculesRight);
        fraction l = new fraction(moleculeArrayList.get(location - 1).getMessForStudent());
        double[] result = new double[getAllLength()];
        for (int i = 0; i < moleculeArrayList.size(); i++) {
            if (i == location - 1) {
                result[i] = mess.doubleValue();
                continue;
            }
            fraction f = new fraction(moleculeArrayList.get(i).getMessForStudent());
            f.multiply(mess);
            f.divide(l);
            result[i] = f.doubleValue();
        }
        return result;
    }
}
