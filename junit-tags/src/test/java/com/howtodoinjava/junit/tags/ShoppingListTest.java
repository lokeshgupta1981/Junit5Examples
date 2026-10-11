package com.howtodoinjava.junit.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("shopping")
class ShoppingListTest {

    @FastTest
    void mergesSameItem() {
        ShoppingList list = new ShoppingList();
        list.add("eggs", 6);
        list.add("eggs", 4);
        assertEquals(10, list.quantityOf("eggs"));
    }

    @Test
    @Tag("slow")
    @Tag("integration")
    void exportsAsText() {
        ShoppingList list = new ShoppingList();
        list.add("milk", 2);
        list.add("eggs", 6);
        assertEquals("2 x milk\n6 x eggs", list.exportAsText());
    }
}
