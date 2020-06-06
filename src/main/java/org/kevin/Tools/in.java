package org.kevin.Tools;

import java.util.Scanner;
@Deprecated
public class in {
    Scanner sc = new Scanner(System.in);

    public int oneInt() {
        return sc.nextInt();
    }

    public int[] Int(int length) {
        int[] arr = new int[length];
        for (int a = 0; a <= length - 1; a++) {
            arr[a] = sc.nextInt();
        }
        return arr;
    }

    public int[] IntWithTip(int length) {
        int[] arr = new int[length];
        for (int a = 0; a <= length - 1; a++) {
            System.out.println("输入第" + (a + 1) + "个数");
            arr[a] = sc.nextInt();
        }
        return arr;
    }

    public byte oneByte() {
        return sc.nextByte();
    }

    public byte[] Byte(int length) {
        byte[] arr = new byte[length];
        for (int a = 0; a <= length - 1; a++) {
            arr[a] = sc.nextByte();
        }
        return arr;
    }

    public byte[] ByteWithTip(int length) {
        byte[] arr = new byte[length];
        for (int a = 0; a <= length - 1; a++) {
            System.out.println("输入第" + (a + 1) + "个数");
            arr[a] = sc.nextByte();
        }
        return arr;
    }


    public short oneShort() {
        return sc.nextShort();
    }

    public short[] Short(int length) {
        short[] arr = new short[length];
        for (int a = 0; a <= length - 1; a++) {
            arr[a] = sc.nextShort();
        }
        return arr;
    }

    public short[] ShortWithTip(int length) {
        short[] arr = new short[length];
        for (int a = 0; a <= length - 1; a++) {
            System.out.println("输入第" + (a + 1) + "个数");
            arr[a] = sc.nextShort();
        }
        return arr;
    }

    public long oneLong() {
        return sc.nextLong();
    }

    public long[] Long(int length) {
        long[] arr = new long[length];
        for (int a = 0; a <= length - 1; a++) {
            arr[a] = sc.nextLong();
        }
        return arr;
    }

    public long[] LongWithTip(int length) {
        long[] arr = new long[length];
        for (int a = 0; a <= length - 1; a++) {
            System.out.println("输入第" + (a + 1) + "个数");
            arr[a] = sc.nextLong();
        }
        return arr;
    }

    public float oneFloat() {
        return sc.nextFloat();
    }

    public float[] Float(int length) {
        float[] arr = new float[length];
        for (int a = 0; a <= length - 1; a++) {
            arr[a] = sc.nextFloat();
        }
        return arr;
    }

    public float[] FloatWithTip(int length) {
        float[] arr = new float[length];
        for (int a = 0; a <= length - 1; a++) {
            System.out.println("输入第" + (a + 1) + "个数");
            arr[a] = sc.nextFloat();
        }
        return arr;
    }

    public double oneDouble() {
        return sc.nextDouble();
    }

    public double[] Double(int length) {
        double[] arr = new double[length];
        for (int a = 0; a <= length - 1; a++) {
            arr[a] = sc.nextDouble();
        }
        return arr;
    }

    public double[] DoubleWithTip(int length) {
        double[] arr = new double[length];
        for (int a = 0; a <= length - 1; a++) {
            System.out.println("输入第" + (a + 1) + "个数");
            arr[a] = sc.nextDouble();
        }
        return arr;
    }

    public String oneString() {
        return sc.next();
    }

    public String[] String(int length) {
        String[] arr = new String[length];
        for (int a = 0; a <= length-1; a++){
            arr[a] = sc.next();
        }
        return arr;
    }

    public String[] StringWithTip(int length) {
        String[] arr = new String[length];
        for (int a = 0; a <= length-1; a++){
            System.out.println("输入第" + (a + 1) + "个字符或字符串");
            arr[a] = sc.next();
        }
        return arr;
    }


}
