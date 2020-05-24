package org.kevin.objects.elements;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class elementTest {

    @Test
    void testToString() {
        System.out.println(new element(12).toString());
    }

}