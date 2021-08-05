package com.hgk.boot.zdemo14.static_proxy;

import com.hgk.boot.zdemo14.Person;
import com.hgk.boot.zdemo14.PersonImpl;

public class PersonProxy implements Person {

    private PersonImpl personImpl;

    public PersonProxy(PersonImpl personImpl){
        this.personImpl = personImpl;
    }
    @Override
    public void saylove(String msg) {
        System.out.println("before,do something");
        personImpl.saylove(msg);
        System.out.println("after,do something");
    }
}
