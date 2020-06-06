package org.kevin.objects.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * @author 18145
 * @version 1.0
 */
public class Fraction extends Number {
    private BigInteger mother;
    private BigInteger son;
    public static final Fraction MINUS_ONE = new Fraction(-1);
    public static final Fraction MINUS_TWO = new Fraction(-2);
    public static final Fraction MINUS_THREE = new Fraction(-3);
    public static final Fraction MINUS_FIVE = new Fraction(-5);
    public static final Fraction MINUS_TEN = new Fraction(-10);
    public static final Fraction ONE = new Fraction(1);
    public static final Fraction TWO = new Fraction(2);
    public static final Fraction THREE = new Fraction(3);
    public static final Fraction FIVE = new Fraction(5);
    public static final Fraction TEN = new Fraction(10);


    public BigInteger getMother() {
        return mother;
    }

    public void setMother(BigInteger mother) {
        this.mother = mother;
    }


    public BigInteger getSon() {
        return son;
    }

    public void setSon(BigInteger son) {
        this.son = son;
    }

    /**
     * 构造函数，以分子分母的形式生成fraction
     *
     * @param a 一个数，用于分母
     * @param b 一个数，用于分子
     */

    public Fraction(int a, int b) {
        mother = new BigInteger(String.valueOf(a));
        son = new BigInteger(String.valueOf(b));
    }

    /**
     * 构造函数，以一个小数来生成fraction
     *
     * @param v 一个浮点数，用于生成fraction
     */
    public Fraction(double v) {
        String[] a = String.valueOf(v).split("\\.");
        stringArrayToFraction(a);
    }

    /**
     * 通过一个String来创建fraction
     *
     * @param v 用于创建fraction的String，必须可以转换为double
     * @see String
     */

    public Fraction(String v) {
        String[] a = String.valueOf(Double.parseDouble(v)).split("\\.");
        stringArrayToFraction(a);
    }

    public Fraction(String[] v) {
        stringArrayToFraction(v);
    }

    /**
     * 此方法用于改善代码复用
     *
     * @param a 一个String[] 包含分子和分母，要求可以转化为int
     * @see String
     */
    private void stringArrayToFraction(String[] a) {
        son = new BigInteger(a[0] + a[1]);

        mother = BigInteger.TEN.pow(a[1].length());
    }


    /**
     * 用作加法运算
     *
     * @param fraction 要加的数
     * @return 运算结果
     */
    public Fraction append(Fraction fraction) {
        BigInteger bigInteger = new BigInteger(mother.toString());
        mother = mother.multiply(fraction.getMother());
        son = (son.multiply(fraction.getMother())).add(fraction.getSon().multiply(bigInteger));
        return this;
    }

    /**
     * 用作乘法运算
     *
     * @param fraction 要乘的数
     * @return 运算结果
     */

    public Fraction multiply(Fraction fraction) {
        mother = mother.multiply(fraction.getMother());
        son = son.multiply(fraction.getSon());
        return this;
    }

    /**
     * 用作除法运算
     *
     * @param fraction 要除的数
     * @return 运算结果
     */

    public Fraction divide(Fraction fraction) {
        BigInteger bigInteger = new BigInteger(mother.toString());
        son = son.multiply(fraction.getMother());
        mother = bigInteger.multiply(fraction.getSon());
        return this;

    }

    /**
     * 用作减法运算
     *
     * @param fraction 要减的数
     * @return 运算结果
     */

    public Fraction subtract(Fraction fraction) {
        BigInteger bigInteger = new BigInteger(mother.toString());

        mother = mother.multiply(fraction.getMother());
        son = (son.multiply(fraction.getMother())).subtract(bigInteger.multiply(fraction.getSon()));
        return this;

    }

    /**
     * 返回一个int 数
     *
     * @return 一个int整数，表示该fraction的整数值
     */
    @Override
    public int intValue() {
        BigDecimal bigDecimal = new BigDecimal(mother);
        BigDecimal bigDecimal1 = new BigDecimal(son);
        return bigDecimal1.divide(bigDecimal, MathContext.DECIMAL128).intValue();
    }

    /**
     * 返回一个double数
     *
     * @return 一个double浮点数，表示该fraction的浮点数值
     */
    @Override
    public double doubleValue() {
        BigDecimal bigDecimal = new BigDecimal(mother);
        BigDecimal bigDecimal1 = new BigDecimal(son);
        return bigDecimal1.divide(bigDecimal, MathContext.DECIMAL128).doubleValue();
    }

    /**
     * 返回一个BigDecimal数
     *
     * @return 一个BigDecimal，表示该fraction的浮点数值
     * @see BigDecimal
     */

    public BigDecimal bigDecimalValue() {
        BigDecimal bigDecimal = new BigDecimal(mother);
        BigDecimal bigDecimal1 = new BigDecimal(son);
        return bigDecimal1.divide(bigDecimal, MathContext.DECIMAL128);
    }

    @Override
    public long longValue() {
        BigDecimal bigDecimal = new BigDecimal(mother);
        BigDecimal bigDecimal1 = new BigDecimal(son);
        return bigDecimal1.divide(bigDecimal, MathContext.DECIMAL128).longValue();
    }

    @Override
    public float floatValue() {
        BigDecimal bigDecimal = new BigDecimal(mother);
        BigDecimal bigDecimal1 = new BigDecimal(son);
        return bigDecimal1.divide(bigDecimal, MathContext.DECIMAL128).floatValue();
    }

    /**
     * 返回一个数组,第一个元素为分子,第二个元素为分母
     *
     * @return 一个BigInteger数组，用于表示分子分母
     */

    public BigInteger[] motherAndSon() {
        return new BigInteger[]{mother, son};
    }


    /**
     * 用于转换为String以便输出
     *
     * @return 转换后的String，格式为”分子/分母“
     */
    @Override
    public String toString() {
        return son + "/" + mother;
    }


}
