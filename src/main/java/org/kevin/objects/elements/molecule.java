package org.kevin.objects.elements;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 18145
 * @version 1.0
 */
public class molecule {
    private final HashMap<element, Integer> map = new HashMap<>();
    private double mess;
    public molecuteToString printer = (map) -> {
        StringBuilder stringBuilder = new StringBuilder();
        Set<Map.Entry<element, Integer>> set = map.entrySet();
        for (Map.Entry<element, Integer> elementIntegerEntry : set) {
            stringBuilder.append(elementIntegerEntry.getValue().toString()).append(elementIntegerEntry.getKey().getEnglishName()).append(" ");
        }
        if (map.isEmpty()) {
            stringBuilder.append("空化学式 ");
        }
        stringBuilder.append("式量(准确值) = ").append(getMess()).append(" 式量(中学生版) = ").append(getMessForStudent());
        return stringBuilder.toString();
    };
    public static Scanner scanner = new Scanner(System.in);

    public static molecule getAMoleCuteFromScanner() {
        return getAMoleCuteFromScanner("依次输入化学式的元素符号以及元素数量，输入完一个化学式以后以END结尾");
    }

    /**
     * 用指定的提示内容，以控制台交互式获取用户输入，以创建一个molecule
     *
     * @param tip 提示的内容，应提示用户end是结束标记
     * @return 一个molecule，根据用户输入的内容创建
     * @see molecule
     * @see String
     */
    public static molecule getAMoleCuteFromScanner(String tip) {
        System.out.println(tip);
        int i = 1;
        molecule result = new molecule();
        while (true) {
            try {
                System.out.print("输入第" + i + "种元素的元素符号 =>");
                String ele = scanner.next();
                if (ele.toLowerCase().equals("end")) {
                    break;
                }
                final element element = new element(ele, org.kevin.objects.elements.element.ENGLISH_NAME);
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


    public molecule() {

    }

    public void add(element element, int number) {
        map.merge(element, number, Integer::sum);
    }

    public double getMess() {
        double result = 0;
        for (Map.Entry<element, Integer> elementIntegerEntry : map.entrySet()) {
            result += (elementIntegerEntry.getKey().getMess()) * elementIntegerEntry.getValue();
        }
        return result;
    }

    public double getMessForStudent() {
        double result = 0;
        for (Map.Entry<element, Integer> elementIntegerEntry : map.entrySet()) {
            result += (elementIntegerEntry.getKey().getMessForStudent()) * elementIntegerEntry.getValue();
        }
        return result;
    }

    public double getElementQualityScore(element element) {
        final Integer integer = map.get(element);
        if (integer != null) {
            return integer;
        } else {
            throw new NullPointerException("分子中没有指定的元素:" + element);
        }
    }

    public void changePrinter(molecuteToString molecuteToString) {
        this.printer = molecuteToString;
    }

    @Override
    public String toString() {
        return printer.toString(map);
    }
}
