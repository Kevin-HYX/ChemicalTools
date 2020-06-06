package org.kevin.objects.entity;

import java.io.Serializable;
import java.util.Scanner;

/**
 * @author 18145
 * @version 1.0
 */
public class Element implements Serializable {
    @Deprecated
    public static Scanner scanner = new Scanner(System.in);
    public static final long serialVersionUID = 1;
    public static final int CHINESE_NAME = 0;
    public static final int ENGLISH_NAME = 1;
    private final int ID;

    public Element(int ID) {
        this.ID = ID;
    }

    public Element(String name, int key) {
        switch (key) {
            case CHINESE_NAME:
                for (int i = 0; i < TableOfTheElements.ElementsChineseNameTable.length; i++) {
                    if (name.toLowerCase().equals(TableOfTheElements.ElementsChineseNameTable[i].toLowerCase())) {
                        this.ID = i + 1;
                        return;
                    }
                }
                throw new NullPointerException("找不到指定的元素: " + name);
            case ENGLISH_NAME:
                for (int i = 0; i < TableOfTheElements.ElementsEnglishNameTable.length; i++) {
                    if (name.toLowerCase().equals(TableOfTheElements.ElementsEnglishNameTable[i].toLowerCase())) {
                        this.ID = i + 1;
                        return;
                    }
                }
                throw new NullPointerException("找不到指定的元素: " + name);
            default:
                throw new NullPointerException("没有指定的选项");

        }
    }

    @Deprecated
    public static Element getAnElementFromScanner() {
        return getAnElementFromScanner("输入一个元素的原子符号");
    }

    @Deprecated
    public static Element getAnElementFromScanner(String tip) {
        System.out.println(tip);
        return new Element(scanner.next(), Element.ENGLISH_NAME);
    }

    public int getID() {
        return ID;
    }

    public double getMass() {
        return TableOfTheElements.ElementsMassTable[ID - 1];
    }

    public double getMassForStudent() {
        if (this.ID == 17) {
            return 35.5;
        }
        int temp = (int) this.getMass();
        if (this.getMass() - temp > 0.5) {
            return temp + 1;
        } else {
            return temp;
        }
    }

    public String getChineseName() {
        return TableOfTheElements.ElementsChineseNameTable[ID - 1];
    }


    public String getEnglishName() {
        return TableOfTheElements.ElementsEnglishNameTable[ID - 1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Element element = (Element) o;

        return ID == element.ID;
    }

    @Override
    public int hashCode() {
        return ID;
    }

    @Override
    public String toString() {
        return this.toString((id, EnglishName, ChineseName) -> this.ID + "号元素 " + this.getEnglishName() + " " + this.getChineseName());
    }

    public String toString(ElementToString toString) {
        return toString.apply(this.ID, getEnglishName(), getChineseName());
    }

    public interface ElementToString {
        /**
         * 使用指定的方法将<code>Element</code>转换为<code>String</code>
         *
         * @param ID          元素符号
         * @param EnglishName 中文名 如 氢
         * @param ChineseName 英文名 如 H
         * @return 一个<code>String</code>,用于描述该元素
         */
        String apply(int ID, String EnglishName, String ChineseName);
    }


}
