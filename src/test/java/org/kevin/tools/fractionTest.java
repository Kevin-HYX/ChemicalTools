package org.kevin.tools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class fractionTest {

    @Test
    void multiply() {
        fraction f1 = new fraction(1.1);
        fraction f2 = new fraction(1.2);
        System.out.println(f1.append(f2).doubleValue());
        System.out.println(f1.multiply(f2).doubleValue());
        System.out.println(f1.append(f2).doubleValue());
        System.out.println(f1.subtract(f2).doubleValue());

    }
}