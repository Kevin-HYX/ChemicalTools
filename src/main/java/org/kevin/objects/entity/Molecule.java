package org.kevin.objects.entity;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiFunction;

/**
 * @author 18145
 * @version 1.0
 */
public class Molecule implements Iterable<Map.Entry<Element, Integer>>, Serializable {
    @Deprecated
    public static Scanner scanner = new Scanner(System.in);
    private final HashMap<Element, Integer> map = new HashMap<>();
    private static final long serialVersionUID = 1;
    private double mass;

    public static long getVersion() {
        return serialVersionUID;
    }

    public MoleculeToString printer = (map) -> {
        StringBuilder stringBuilder = new StringBuilder();
        Set<Map.Entry<Element, Integer>> set = map.entrySet();
        for (Map.Entry<Element, Integer> elementIntegerEntry : set) {
            stringBuilder.append(elementIntegerEntry.getValue().toString()).append(elementIntegerEntry.getKey().getEnglishName()).append(" ");
        }
        if (map.isEmpty()) {
            stringBuilder.append("空化学式 ");
        }
        stringBuilder.append("式量(准确值) = ").append(getMass()).append(" 式量(中学生版) = ").append(getMassForStudent());
        return stringBuilder.toString();
    };

    public Molecule() {

    }

    @Deprecated
    public static Molecule getAMoleCuteFromScanner() {
        return getAMoleCuteFromScanner("依次输入化学式的元素符号以及元素数量，输入完一个化学式以后以END结尾");
    }

    /**
     * 用指定的提示内容，以控制台交互式获取用户输入，以创建一个molecule
     *
     * @param tip 提示的内容，应提示用户end是结束标记
     * @return 一个molecule，根据用户输入的内容创建
     * @see Molecule
     * @see String
     */
    @Deprecated
    public static Molecule getAMoleCuteFromScanner(String tip) {
        System.out.println(tip);
        int i = 1;
        Molecule result = new Molecule();
        while (true) {
            try {
                System.out.print("输入第" + i + "种元素的元素符号 =>");
                String ele = scanner.next();
                if ("end".equals(ele.toLowerCase())) {
                    break;
                }
                final Element element = new Element(ele, Element.ENGLISH_NAME);
                System.out.print("输入第" + i + "种元素的元素数量 =>");
                result.add(element, scanner.nextInt());
                i++;
            } catch (NullPointerException e) {

                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("检查输入");
            }
        }
        //没有任何的输入
        return result;
    }

    @Override
    public Iterator<Map.Entry<Element, Integer>> iterator() {
        return map.entrySet().iterator();
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(mass);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Molecule molecule = (Molecule) o;

        if (Double.compare(molecule.mass, mass) != 0) {
            return false;
        }
        return map != null ? map.equals(molecule.map) : molecule.map == null;
    }

    public void add(Element element, int number) {
        map.merge(element, number, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
    }

    public double getMass() {
        double result = 0;
        for (Map.Entry<Element, Integer> elementIntegerEntry : map.entrySet()) {
            result += (elementIntegerEntry.getKey().getMass()) * elementIntegerEntry.getValue();
        }
        return result;
    }

    public double getMassForStudent() {
        double result = 0;
        for (Map.Entry<Element, Integer> elementIntegerEntry : map.entrySet()) {
            result += (elementIntegerEntry.getKey().getMassForStudent()) * elementIntegerEntry.getValue();
        }
        return result;
    }

    public double getElementQualityScore(Element element) {
        final Integer integer = map.get(element);
        if (integer != null) {
            return integer;
        } else {
            throw new NullPointerException("分子中没有指定的元素:" + element);
        }
    }

    public void changePrinter(MoleculeToString moleculeToString) {
        this.printer = moleculeToString;
    }


    @Override
    public String toString() {
        return printer.toString(map);
    }
}
