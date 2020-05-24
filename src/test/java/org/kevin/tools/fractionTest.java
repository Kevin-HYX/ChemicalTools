package org.kevin.tools;

import org.junit.jupiter.api.Test;

class fractionTest {

    @Test
    void multiply() {
        fraction fraction = new fraction(80);
        System.out.println(fraction.append(new fraction(100)).doubleValue());
        System.out.println(fraction.divide(org.kevin.tools.fraction.TEN).doubleValue());
        System.out.println(fraction.multiply(new fraction("1.11211312121")).doubleValue());
    }
}