package com.example;

public class HelloWorld {

    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }

    public static void main(String[] args) {
        HelloWorld hw = new HelloWorld();
        System.out.println(hw.sayHello("World"));
    }
}
