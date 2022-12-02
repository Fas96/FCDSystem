package com.fas.fcdsystem;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class FcdSystemApplicationTest {
    Calculator underTest = new Calculator();

    @Test
    void contextLoads() {
        System.out.println("Test");
    }
    @Test
    void itShouldAddNumbers() {
        // given
        int number1 = 10;
        int number2 = 5;
        // when
        int result = underTest.add(number1, number2);
        // then
        assertThat(result).isEqualTo(15);

    }
    @Test
    void itShouldSubtractNumbers() {
        // given
        int number1 = 10;
        int number2 = 5;
        // when
        int result = underTest.subtract(number1, number2);
        // then
        assertThat(result).isEqualTo(5);
    }
     class Calculator {
        int add(int a, int b) {
            return a + b;
        }

        int subtract(int a, int b) {
            return a - b;
        }

    }
}