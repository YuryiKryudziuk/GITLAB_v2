package com.data.lambda.part2.exercise;

import com.data.Person;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrowNotationExercise {

    @Test
    public void getAge() {
        // Person -> Integer
        final Function<Person, Integer> getAge = Person::getAge; // TODO

        assertEquals(Integer.valueOf(33), getAge.apply(new Person("", "", 33)));
    }

    @Test
    public void compareAges() {
        // TODO use BiPredicate
        BiPredicate<Person , Person > compareAges = (a,  b)-> {return a.getAge()==b.getAge();};

//        throw new UnsupportedOperationException("Not implemented");

        assertEquals(true, compareAges.test(new Person("a", "b", 22), new Person("c", "d", 22)));
    }



    @Test
    public void getAgeOfPersonWithTheLongestFullName() {
        // TODO
        // getFullName: Person -> String
        final Function<Person, String> getFullName = person -> {
            return person.getFirstName() + person.getLastName();
        };

        // TODO
        // ageOfPersonWithTheLongestFullName: (Person -> String) -> (Person, Person) -> int
        // TODO use ageOfPersonWithTheLongestFullName(getFullName)
        final BiFunction<Person, Person, Integer> ageOfPersonWithTheLongestFullName = (person1, person2) ->
        {if (getFullName.apply(person1).length()>
                getFullName.apply(person2).length())
        {return person1.getAge();}
        else return person2.getAge();};

        assertEquals(
                Integer.valueOf(1),
                ageOfPersonWithTheLongestFullName.apply(
                        new Person("a", "b", 2),
                        new Person("aa", "b", 1)));
    }
}
