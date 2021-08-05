package com.hgk.boot.test;

public class DyTest {

    public void eatTest(Animal animal){
        animal.eat();
    }
    public void huntTest(Huntable huntable){
        huntable.hunt();
    }
}

class Animal{

    public void eat(){
        System.out.println("eat");
    }
}

interface Huntable{
    void hunt();
}
class Dog extends Animal implements Huntable{

    @Override
    public void eat(){
        System.out.println("Dog eat");
    }

    @Override
    public void hunt() {
        System.out.println("Dog hunt");
    }
}

class Cat extends Animal implements Huntable{

    @Override
    public void eat(){
        System.out.println("Cat eat");
    }

    @Override
    public void hunt() {
        System.out.println("Cat hunt");
    }
}
