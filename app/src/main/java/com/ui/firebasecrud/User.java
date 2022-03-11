package com.ui.firebasecrud;

public class User {


    public User(){

    }


    public User(String name, String image, String phone, String age) {
        Name = name;
        Image = image;
        Phone = phone;
        Age = age;
    }

    String Name;
    String Image;
    String Phone;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    String Age;
}

