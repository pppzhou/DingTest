package com.mininglamp.Util.Entrance;

/**
 * Project: DingTest
 * Package: com.mininglamp.Util.Entrance
 * Description
 * <p>
 * Created by ZhouPeng on 2020/11/26 14:57
 **/
public class Person {
    private String name;
    private String email;
    private String phone;
    private String sex;

    private Person(String name) {
        this.name = name;
    }

    private Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Person(String name, String email, String phone, String sex) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
    }
}
