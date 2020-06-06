package org.kevin.objects.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * @author 18145
 * @version 1.0
 */
public class fraction extends Number {
    private BigInteger mother;
    private BigInteger son;
    public static final fraction MINUS_ONE = new fraction(-1);
    public static final fraction MINUS_TWO = new fraction(-2);
    public static final fraction MINUS_THREE = new fraction(-3);
    public static final fraction MINUS_FIVE = new fraction(-5);
    public static final fraction MINUS_TEN = new fraction(-10);
    public static final fraction ONE = new fraction(1);
    public static final fraction TWO = new fraction(2);
    public static final fraction THREE = new fraction(3);
    public static final fraction FIVE = new fraction(5);
    public static final fraction TEN = new fraction(10);


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

    public fraction(int a, int b) {
        mother = new BigInteger(String.valueOf(a));
        son = new BigInteger(String.valueOf(b));
    }

    /**
     * 构造函数，以一个小数来生成fraction
     *
     * @param V 一个浮点数，用于生成fraction
     */
    public fraction(double V) {
        String[] a = String.valueOf(V).split("\\.");
        stringArrayToFraction(a);
    }

    /**
     * 通过一个String来创建fraction
     *
     * @param V 用于创建fraction的String，必须可以转换为double
     * @see String
     */

    public fraction(String V) {
        String[] a = String.valueOf(Double.parseDouble(V)).split("\\.");
        stringArrayToFraction(a);
    }

    public fraction(String[] V) {
        stringArrayToFraction(V);
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
    public fraction append(fraction fraction) {
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

    public fraction multiply(fraction fraction) {
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

    public fraction divide(fraction fraction) {
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

    public fraction subtract(fraction fraction) {
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
     * 返回一个数组
     *
     * @return 一个BigInteger数组，用于表示
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
