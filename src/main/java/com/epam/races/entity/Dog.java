package com.epam.races.entity;

public class Dog {
    private String nickname;
    private int age;
    private DogBreed breed;

    public Dog(){

    }
    public Dog(String nickname, int age, String dogEnum) {
        this.nickname = nickname;
        this.age = age;
        this.breed = DogBreed.valueOf(dogEnum.toUpperCase());
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

    public DogBreed getBreed() {
        return breed;
    }

    public void setBreed(DogBreed dogBreed) {
        this.breed = dogBreed;
    }

    @Override
    public String toString() {
        return "Dog: " + nickname +
                ", " + age + " years, " + breed + "\n";
    }
}
