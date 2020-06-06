package org.kevin.objects.entity;

import java.io.Serializable;
import java.util.Map;

@FunctionalInterface
public interface MoleculeToString extends Serializable {
    long serialVersionUID = 1;

    String toString(Map<Element, Integer> map);
}
