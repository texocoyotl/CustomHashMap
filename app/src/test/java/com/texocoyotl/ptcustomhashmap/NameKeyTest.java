package com.texocoyotl.ptcustomhashmap;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NameKeyTest {
    @Test
    public void assertHash() throws Exception {
        NameKey a = new NameKey("Benjamin", "Molina");
        NameKey b = new NameKey("Benjamin", "Molina");

        assertTrue(a.equals(b));
        assertTrue(a.hashCode() == b.hashCode());
    }
}
