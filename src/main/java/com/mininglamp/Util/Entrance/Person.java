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

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Person(String name, String email, String phone, String sex) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
    }

    public Person(String phone, String sex, String name) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }


    public String getPhone() {
        return phone;
    }
    public String getSex() {
        return sex;
    }
}
