package com.howtodoinjava.junit.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(SlowLastOrderer.class)
class CatalogImportTest {

    @BeforeEach
    void printName(TestInfo info) {
        System.out.println("CatalogImportTest > " + info.getDisplayName());
    }

    @Test
    @Tag("slow")
    void importsFullCatalog() {
    }

    @Test
    void parsesIsbn() {
    }

    @Test
    @Tag("slow")
    void downloadsCoverImages() {
    }

    @Test
    void rejectsEmptyTitle() {
    }
}
