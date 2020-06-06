package org.kevin.objects.entity;

import java.io.Serializable;
import java.util.Map;

@FunctionalInterface
public interface moleculeToString extends Serializable {
    long serialVersionUID = 1;

    String toString(Map<element, Integer> map);
}
