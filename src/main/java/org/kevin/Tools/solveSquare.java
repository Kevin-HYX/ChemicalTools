package org.kevin.Tools;

import org.kevin.objects.entity.fraction;

public class solveSquare {
    /*
    1所有的方程参数均按从左到右逐行输入
    2除一元一次方程，一元二次方程，二元一次，三元一次方程提供基本数据类型double的直接输入方式，该类中都采用double[]的方式输入
     */

    public double solveMainOneOne(double a, double b) {
        return -b / a;
    }

    public double solveMainOneOne(double[] arr) {
        double a = arr[2];
        double b = arr[1];
        if (a == 0) {
            System.out.println("一元一次方程错误：X系数不为0");
            return -1;
        } else {
            return -b / a;
        }
    }

    public static fraction solveMainOneOne(fraction a, fraction b) {
        return b.divide(a).multiply(fraction.MINUS_ONE);
    }


    public double[] solveMainTwoOne(double a1, double b1, double c1, double a2, double b2, double c2) {
        // 二元一次方程组一般式ax+by+c = 0
        //                   Ax+By+c = 0

        if ((a1 == 0 && a2 == 0) || (b1 == 0 && b2 == 0)) {
            System.out.println("二元一次方程组错误：Y,X系数不为为0");
            return new double[]{-1, -1};
        } else if ((a1 == 0 && b1 == 0) || a2 == 0 && c2 == 0) {
            System.out.println("二元一次方程组错误：不是方程");
            return new double[]{-1, -1};
        } else if ((a1 == a2 && b1 == b2) && c1 != c2) {
            System.out.println("二元一次方程组无解");
            return new double[]{-1, -1};
        } else if ((a1 == a2 && b1 == b2) && c1 == c2) {
            System.out.println("二元一次方程组有无穷多解");
            return new double[]{-1, -1};
        } else if (a1 == 0) {
            double y = solveMainOneOne(b1, c1);
            return new double[]{solveMainOneOne(a2, b2 * y + c2), y};
        } else if (a2 == 0) {
            double x = solveMainOneOne(a1, c1);
            return new double[]{x, solveMainOneOne(b2, x * a2 + c2)};
        } else {
            double y = solveMainOneOne(b2 - ((a2 * b1) / a1), (c2 - (a2 * c1) / a1));
            return new double[]{(-c1 - b1 * y) / a1, y};
        }

    }

    public double[] solveMainTwoOne(double[] arr) {
        double a1 = arr[0];
        double b1 = arr[1];
        double c1 = arr[2];
        double a2 = arr[3];
        double b2 = arr[4];
        double c2 = arr[5];

        // 二元一次方程组一般式ax+by+c = 0
        //                   Ax+By+c = 0
        if ((a1 == 0 && a2 == 0) || (b1 == 0 && b2 == 0)) {
            System.out.println("二元一次方程组错误：Y,X系数不为为0");
            return new double[]{-1, -1};
        } else if ((a1 == 0 && b1 == 0) || a2 == 0 && c2 == 0) {
            System.out.println("二元一次方程组错误：不是方程");
            return new double[]{-1, -1};
        } else if ((a1 == a2 && b1 == b2) && c1 != c2) {
            System.out.println("二元一次方程组无解");
            return new double[]{-1, -1};
        } else if ((a1 == a2 && b1 == b2) && c1 == c2) {
            System.out.println("二元一次方程组有无穷多解");
            return new double[]{-1, -1};
        } else if (a1 == 0) {
            double y = solveMainOneOne(b1, c1);
            return new double[]{solveMainOneOne(a2, b2 * y + c2), y};
        } else if (a2 == 0) {
            double x = solveMainOneOne(a1, c1);
            return new double[]{x, solveMainOneOne(b2, x * a2 + c2)};
        } else {
            double y = solveMainOneOne(b2 - ((a2 * b1) / a1), (c2 - (a2 * c1) / a1));
            return new double[]{(-c1 - b1 * y) / a1, y};
        }
    }

    public double[] solveMainThreeOne(double[] arr) { //该方法未完成编写
        double a1 = arr[0];
        double b1 = arr[1];
        double c1 = arr[2];
        double d1 = arr[3];
        double a2 = arr[4];
        double b2 = arr[5];
        double c2 = arr[6];
        double d2 = arr[7];
        double a3 = arr[8];
        double b3 = arr[9];
        double c3 = arr[10];
        double d3 = arr[11];
        double[] temp = solveMainTwoOne((-a2 * b1 / a1 + b2), (c2 - c1 * a2 / a1), (d2 - a2 * d1 / a1), (b3 - a3 * b1 / a1), (c3 - a3 * c1 / a1), (d3 - a3 * d1 / a1));
        return new double[]{(-d1 - c1 * temp[1] - b1 * temp[0]) / a1, temp[0], temp[1]};
    }

    public double[] solveMainOneTwo(double a, double b, double c) {
        double delta = b * b - 4 * a * c;
        if (a == 0) {
            System.out.println("一元二次方程错误：X二次项系数不为0");
            return new double[]{-1, -1};
        } else if (delta == 0) {
            System.out.println("方程有一实根");
            return new double[]{(-b - Math.sqrt(delta)) / 2 / a, (-b + Math.sqrt(delta)) / 2 / a};
        } else if (delta < 0) {
            System.out.println("方程无实根");
            return new double[]{-1, -1};
        } else {
            return new double[]{(-b - Math.sqrt(delta)) / 2 / a, (-b + Math.sqrt(delta)) / 2 / a};
        }
    }

    public double[] solveMainOneTwo(double[] arr) {
        double a = arr[0];
        double b = arr[1];
        double c = arr[2];
        double delta = b * b - 4 * a * c;
        if (a == 0) {
            System.out.println("一元二次方程错误：X二次项系数不为0");
            return new double[]{-1, -1};
        } else if (delta == 0) {
            System.out.println("方程有一实根");
            return new double[]{(-b - Math.sqrt(delta)) / 2 / a, (-b + Math.sqrt(delta)) / 2 / a};
        } else if (delta < 0) {
            System.out.println("方程无实根");
            return new double[]{-1, -1};
        } else {
            return new double[]{(-b - Math.sqrt(delta)) / 2 / a, (-b + Math.sqrt(delta)) / 2 / a};
        }
    }
}
