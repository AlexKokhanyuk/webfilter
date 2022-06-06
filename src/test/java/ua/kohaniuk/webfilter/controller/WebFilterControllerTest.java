package ua.kohaniuk.webfilter.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebFilterControllerTest {

    @Test
    void getAccess() {
        WebFilterController webFilterController=new WebFilterController();
        String result=webFilterController.getAccess();
        assertEquals(result,"index");
    }
}