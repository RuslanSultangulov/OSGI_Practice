package ru.sultangulov.task4command.implementation;

import ru.sultangulov.task4command.Hello;

public class HelloImpl implements Hello {

    public void hello(String text) {
        System.out.println("Hello, " + text);
    }
}
