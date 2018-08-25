package org.jeremy;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Customer {

    @NotNull(message = "Name cannot be null")
    private String name;

    @Size(min = 10, max = 200, message
            = "About Me must be between 10 and 200 characters")
    private String aboutMe;

    @Min(value = 1, message = "Age should not be less than 1")
    @Max(value = 130, message = "Age should not be greater than 130")
    private int age;

    @UniqueElements(message = "This list contains duplicated elements: {duplicates}")
    @ContainsStrings(values = {"Jeremy", "Claire", "Andrew"}, message = "The list misses required elements: {missingValues}")
    List<String> friends;

    List<List<String>> friendsOfFriends;

    @ContainsInts(values = {1, 2, 3, 4}, message = "The list misses required elements: {missingValues}")
    List<Integer> scores;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public List<List<String>> getFriendsOfFriends() {
        return friendsOfFriends;
    }

    public void setFriendsOfFriends(List<List<String>> friendsOfFriends) {
        this.friendsOfFriends = friendsOfFriends;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }
}
