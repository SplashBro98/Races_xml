package com.epam.races.entity;

public class Horse {
    private String nickname;
    private int age;
    private HorseBreed breed;

    public Horse() {
    }

    public Horse(String nickname, int age, String breed) {
        this.nickname = nickname;
        this.age = age;
        this.breed = HorseBreed.valueOf(breed.toUpperCase());
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

    public HorseBreed getBreed() {
        return breed;
    }

    public void setBreed(HorseBreed horseBreed) {
        this.breed = horseBreed;
    }

    @Override
    public String toString() {
        return "Horse: " + nickname +
                ", " + age + " years, " + breed + "\n";
    }
}
