package org.jeremy;

import com.google.common.collect.Lists;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Customer customer = new Customer();
        customer.setAge(50);
        customer.setName("Dude");

        List<String> friends = Lists.newArrayList("Jeremy", "Jeremy", "Claire");
        List<String> friends2 = Lists.newArrayList("Jeremy", "Claire", "Claire");
        customer.setFriends(friends);

        List<List<String>> friendsOfFriends = new ArrayList<>();
        friendsOfFriends.add(friends);
        friendsOfFriends.add(friends2);
        customer.setFriendsOfFriends(friendsOfFriends);

        customer.setScores(Lists.newArrayList(1, 2));


        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        violations.forEach(violation -> System.out.println(violation.getMessage()));
    }
}
