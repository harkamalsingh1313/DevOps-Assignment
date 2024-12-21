package com.example;

import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest {

    @Test
    public void testSayHello() {
        HelloWorld hw = new HelloWorld();
        String result = hw.sayHello("JUnit");
        Assert.assertEquals("Hello, JUnit!", result);
    }

    @Test
    public void testSayHelloEmptyString() {
        HelloWorld hw = new HelloWorld();
        String result = hw.sayHello("");
        Assert.assertEquals("Hello, !", result);
    }
}
