package org.kevin.objects.elements;

import org.kevin.objects.tableOfTheElements;

import java.io.Serializable;
import java.util.Scanner;

/**
 * @author 18145
 * @version 1.0
 */
public class element implements Serializable {
    private int ID;
    public static final long serialVersionUID = 1;
    public static final int CHINESE_NAME = 0;
    public static final int ENGLISH_NAME = 1;
    public static Scanner scanner = new Scanner(System.in);

    public static element getAnElementFromScanner() {
        return getAnElementFromScanner("输入一个元素的原子符号");
    }

    public static element getAnElementFromScanner(String tip) {
        System.out.println(tip);
        return new element(scanner.next(), element.ENGLISH_NAME);
    }

    public element(int ID) {
        this.ID = ID;
    }

    public element(String name, int key) {
        switch (key) {
            case CHINESE_NAME:
                for (int i = 0; i < tableOfTheElements.ElementsChineseNameTable.length; i++) {
                    if (name.toLowerCase().equals(tableOfTheElements.ElementsChineseNameTable[i].toLowerCase())) {
                        this.ID = i + 1;
                        return;
                    }
                }
                throw new NullPointerException("找不到指定的元素: " + name);
            case ENGLISH_NAME:
                for (int i = 0; i < tableOfTheElements.ElementsEnglishNameTable.length; i++) {
                    if (name.toLowerCase().equals(tableOfTheElements.ElementsEnglishNameTable[i].toLowerCase())) {
                        this.ID = i + 1;
                        return;
                    }
                }
                throw new NullPointerException("找不到指定的元素: " + name);
        }
    }

    public int getID() {
        return ID;
    }

    public double getMess() {
        return tableOfTheElements.ElementsMassTable[ID - 1];
    }

    public double getMessForStudent() {
        if (this.ID == 17) {
            return 35.5;
        }
        int temp = (int) this.getMess();
        if (this.getMess() - temp > 0.5) {
            return temp + 1;
        } else {
            return temp;
        }
    }

    public String getChineseName() {
        return tableOfTheElements.ElementsChineseNameTable[ID - 1];
    }


    public String getEnglishName() {
        return tableOfTheElements.ElementsEnglishNameTable[ID - 1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        element element = (element) o;

        return ID == element.ID;
    }

    @Override
    public int hashCode() {
        return ID;
    }

    public interface elementToString {
        String apply(int ID, String EnglishName, String ChineseName);
    }

    @Override
    public String toString() {
        return this.toString((id, englishname, chinesename) -> this.ID + "号元素 " + this.getEnglishName() + " " + this.getChineseName());
    }

    public String toString(elementToString toString) {
        return toString.apply(this.ID, getEnglishName(), getChineseName());
    }


}
