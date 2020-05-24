package org.kevin.objects.elements;

import java.util.Map;

@FunctionalInterface
public interface molecuteToString {
    String toString(Map<element, Integer> map);
}
