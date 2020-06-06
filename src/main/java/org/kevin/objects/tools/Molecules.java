package org.kevin.objects.tools;

import org.kevin.objects.entity.Element;
import org.kevin.objects.entity.Molecule;

import java.util.Scanner;

/**
 * @author 18145
 * @version 1.0
 */
public class Molecules {
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

    public synchronized static Molecule getAMoleCuteFromScanner() {
        return getAMoleCuteFromScanner("依次输入化学式的元素符号以及元素数量，输入完一个化学式以后以END结尾");
    }

    public synchronized static Molecule getAMoleCuteFromScanner(String tip) {
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

}
