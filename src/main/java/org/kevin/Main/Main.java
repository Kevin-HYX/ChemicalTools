import org.kevin.objects.periodicTableOfTheElements;
import org.kevin.tools.in;
import org.kevin.tools.solveSqare;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.println("溶液质量分数计算输入1 化学式分析计算输入2 化学式分析计算(中学版)输入3 化学式质量计算输入4");
            periodicTableOfTheElements table = new periodicTableOfTheElements();
            switch (new in().oneInt()) {
                //溶液
                case 1:
                    System.out.println("求溶剂输入1 求溶质输入2 求质量分数输入3 求溶解度输入4");
                    switch (new in().oneInt()) {
                        case 1:
                            System.out.println("输入溶质和质量分数");
                            double[] arr1 = new in().Double(2);
                            System.out.println("溶剂质量 = " + new solveSqare().solveMainOneOne(arr1[1] / 100, arr1[1] / 100 * arr1[0] - arr1[0]));
                            break;
                        case 2:
                            System.out.println("输入溶液和质量分数");
                            double[] arr2 = new in().Double(2);
                            System.out.println("溶质质量 = " + new solveSqare().solveMainOneOne(arr2[1] / 100 - 1, arr2[1] * arr2[0] / 100));
                            break;
                        case 3:
                            System.out.println("输入溶剂和溶质质量");
                            double[] arr3 =new in().Double(2);
                            System.out.println("质量分数 = " + arr3[1] / (arr3[1] + arr3[0]) * 100 + "%");
                            break;
                        case 4:
                            System.out.println("输入饱和溶液的溶剂和溶质质量");
                            double[] arr4 = new in().Double(2);
                            System.out.println("该类溶质溶剂在该温度下的溶解度 = " + new solveSqare().solveMainOneOne(arr4[0], -100 * arr4[1]));
                            break;
                        default:{
                            System.out.println("错误的输入");
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < table.ElementsMassTable.length; i++) {
                        if (i == 16) {
                            table.ElementsMassTable[i] = 35.5;
                        } else {
                            int temp = (int) table.ElementsMassTable[i];
                            if (table.ElementsMassTable[i] - temp > 0.5) {
                                table.ElementsMassTable[i] = temp + 1;
                            } else {
                                table.ElementsMassTable[i] = temp;
                            }
                        }
                    }
                case 2: {

                    System.out.println("输入该化学式由几种原子构成");
                    int match = new in().oneInt();
                    if (match == 1) {
                        System.out.println("该物是由一种元素组成,输入这种元素的元素符号和原子数量");
                    } else {
                        System.out.println("该物质是由多种元素组成，分组输入元素符号和原子数量（一一对应）");
                    }
                    String[][] dataBase = new String[match][2];
                    //输入
                    for (int a = 0; a <= match - 1; a++) {
                        dataBase[a] = new in().String(2);
                    }
                    int[] dataBase2 = new int[match];
                    for (int a = 0; a <= match - 1; a++) {
                        dataBase2[a] = Integer.parseInt(dataBase[a][1]);
                    }
                    double mass = 0;
                    int[] dataBase3 = new int[match];
                    //转换为原子序数
                    for (int a = 0; a <= match - 1; a++) {
                        for (int b = 0; b <= 117; b++) {
                            if (dataBase[a][0].equals(table.ElementsEnglishNameTable[b])) {
                                dataBase3[a] = b + 1;
                            }
                        }
                    }
                    /*
                    database String[][] 用户输入的元素符号和原子数量
                    database2 int[] 原子数量从String 转为int
                    database3 int [] 通过查表匹配得到原子序数
                    */
                    //求得式量
                    for (int a = 0; a <= match - 1; a++) {
                        mass += table.ElementsMassTable[dataBase3[a] - 1] * dataBase2[a];
                    }
                    System.out.println("该化学式的式量是 " + mass);
                    System.out.println("接下来逐个分析各个元素");
                    System.out.println();
                    for (int a = 0; a <= match - 1; a++) {
                        System.out.println("化学元素 " + dataBase[a][0] + " " + table.ElementsChineseNameTable[dataBase3[a] - 1] + " " + dataBase3[a] + " 号元素" + " 相对原子质量 = " + table.ElementsMassTable[dataBase3[a] - 1]);
                        System.out.println("该元素在该化学式中的质量分数是: " + dataBase2[a] * table.ElementsMassTable[dataBase3[a] - 1] / mass * 100 + "%");
                        System.out.println();
                    }
                }
                    break;
                case 4:
                    Scanner sc = new Scanner(System.in);
                    while (true) {
                        try {
                            System.out.println("输入该化学式等号左边有几种分子，等号右边有几种分子");
                            int left = sc.nextInt();
                            int right = sc.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("检查你的输入！");
                        }
                    }
                    System.out.println("请从左到右依次输入这几个化学式");

                default:{
                    System.out.println("错误的输入");
                }
            }
        }
    }
}
