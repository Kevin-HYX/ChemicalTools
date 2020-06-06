package org.kevin.objects.tools;

import org.kevin.objects.entity.Element;

import java.util.Scanner;

/**
 * @author 18145
 * @version 1.0
 */
public class Elements {
    public static Scanner scanner;

    public synchronized static boolean init() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
            return true;
        } else {
            scanner = new Scanner(System.in);
            return false;
        }
    }

    public synchronized static Element getAnElementFromScanner() {
        return getAnElementFromScanner("输入一个元素的原子符号");
    }

    public synchronized static Element getAnElementFromScanner(String tip) {
        System.out.println(tip);
        return new Element(scanner.next(), Element.ENGLISH_NAME);
    }
}
