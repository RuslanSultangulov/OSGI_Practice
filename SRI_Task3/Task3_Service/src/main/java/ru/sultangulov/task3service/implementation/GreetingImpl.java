package ru.sultangulov.task3service.implementation;

import org.osgi.service.component.annotations.Component;

import ru.sultangulov.task3service.Greeting;

@Component(service = Greeting.class)
public class GreetingImpl implements Greeting {

    private final String name;

    public GreetingImpl() {
        this.name = "Ruslan";
    }

    public void sayHello() {
        System.out.println("Hello, " + name + "!");
    }

}
