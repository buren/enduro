package controllers;

import org.junit.Test;

import java.io.FileNotFoundException;

public class FormatterControllerTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalRaceType() throws FileNotFoundException {
        String[] ettArray = {"hej"};
        new FormatterController().result(ettArray, ettArray, "på", 42, "dig", 3, FormatterController.DONT_SORT);
    }

}
