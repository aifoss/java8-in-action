package com.java8_in_action.chap10_using_optional_as_a_better_alternative_to_null;

import java.util.Optional;

/**
 * Created by sofia on 12/23/16.
 */
public class OptionalMain {

    private static class Insurance {
        private String name;

        public String getName() {
            return name;
        }
    }

    private static class Car {
        private Optional<Insurance> insurance;

        public Optional<Insurance> getInsurance() {
            return insurance;
        }
    }

    private static class Person {
        private Optional<Car> car;

        public Optional<Car> getCar() {
            return car;
        }
    }


    public static String getCarInsuranceName(Optional<Person> person) {
        return person
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

}
