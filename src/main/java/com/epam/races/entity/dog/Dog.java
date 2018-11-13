package com.epam.races.entity.dog;

public class Dog {
    private String nickname;
    private int age;
    private DogBreed dogEnum;

    public Dog(String nickname, int age, String dogEnum) {
        this.nickname = nickname;
        this.age = age;
        this.dogEnum = DogBreed.valueOf(dogEnum.toUpperCase());
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public DogBreed getDogEnum() {
        return dogEnum;
    }

    public void setDogEnum(DogBreed dogBreed) {
        this.dogEnum = dogBreed;
    }
}
