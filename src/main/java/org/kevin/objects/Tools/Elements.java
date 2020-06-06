package org.kevin.objects.Tools;

import org.kevin.objects.entity.element;

import java.util.Scanner;

/**
 * @author 18145
 * @version 1.0
 */
public class Elements {
    //    private Statement
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

    public synchronized static element getAnElementFromScanner() {
        return getAnElementFromScanner("输入一个元素的原子符号");
    }

    public synchronized static element getAnElementFromScanner(String tip) {
        System.out.println(tip);
        return new element(scanner.next(), element.ENGLISH_NAME);
    }
}
