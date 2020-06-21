package org.kevin.objects.entity;

import java.io.Serializable;
import java.util.*;

/**
 * 一个类，用于表示化学方程式的等号两边，并在内部给出不同的计算方法
 *
 * @author 18145
 * @version 1.0
 */


public class ChemicalFormula implements Serializable {
    public static final long serialVersionUID = 1;

    /**
     * 化学方程式的等号的左边
     */
    private final TreeMap<Molecule, Integer> moleculesLeft;
    /**
     * 化学方程式的等号右边
     */
    private final TreeMap<Molecule, Integer> moleculesRight;

    /**
     * 一个空的构造器，生产一个空的化学式以便后续往里面添加元素
     */
    public ChemicalFormula() {
        this.moleculesLeft = new TreeMap<>();
        this.moleculesRight = new TreeMap<>();
    }

    /**
     * 从给定的<code>TreeMap</code>生成化学方程式
     *
     * @param moleculesLeft  方程式等号的左边
     * @param moleculesRight 方程式等号的右边
     */


    public ChemicalFormula(TreeMap<Molecule, Integer> moleculesLeft, TreeMap<Molecule, Integer> moleculesRight) {
        this.moleculesLeft = moleculesLeft;
        this.moleculesRight = moleculesRight;
    }

    /**
     * 向化学方程式的右边添加化学式
     *
     * @param molecule 需要添加的化学式
     */
    public void addRight(Molecule molecule, int number) {
        this.moleculesRight.put(molecule, number);
    }

    /**
     * 向化学方程式的左边添加化学式
     *
     * @param molecule 需要添加的化学式
     */
    public void addLeft(Molecule molecule, int number) {
        this.moleculesLeft.put(molecule, number);
    }

    /**
     * 同时向化学方程式的两边添加化学式
     *
     * @param left  需要添加到左边的化学式
     * @param right 需要添加到右边的化学式
     */
    public void add(Molecule left, int leftNumber, Molecule right, int rightNumber) {
        addRight(right, rightNumber);
        addLeft(left, leftNumber);
    }

    public TreeMap<Molecule, Integer> getMoleculesLeft() {
        return moleculesLeft;
    }

    public TreeMap<Molecule, Integer> getMoleculesRight() {
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

}
