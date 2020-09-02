package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class StringCalculator {
    private String base;
    private int result;
    private String operator;

    @DisplayName("정수 여부 판단함수")
    static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @ParameterizedTest
    @CsvSource(value = {"+:20"}, delimiter = ':')
    void operateTest(String operator, String operand) {
        operate(operator, operand);
        assertThat(result).isEqualTo(20);
    }

    void operate(String operator, String operand) {
        int input = Integer.parseInt(operand);
        switch (operator) {
            case "+":
                result = result + input;
                break;
            case "-":
                result = result - input;
                break;
            case "*":
                result = result * input;
                break;
            case "/":
                result = result / input;
                break;
        }
    }

    @BeforeEach
    void setUp () {
        base = "2 + 3 * 4 / 2";
        result = 0;
        operator = "";
    }

    @DisplayName("1. 입력 String 분배")
    String[] splitter(String input) {
        String[] elements = input.split(" ");
        elementCheck(elements);
        return elements;
    }

    @Test
    @DisplayName("TEST 1. 입력 String 분배 테스트")
    void splitterTest() {
        try {
            assertThat(splitter(base)).contains("2");
        } catch (Exception e) {
            assertThatIllegalArgumentException();
        }
    }

    @DisplayName("내부 Parameters 확인")
    void elementCheck(String[] input) {
        if (input == null || input.length < 1)
            throw new IllegalArgumentException("IllegalArgumentException : input is not ready");
        for (int i = 0; i < input.length; i++) {
            String element = input[i];
            if (element == null || element.equals("") || element.equals(" "))
                throw new IllegalArgumentException("IllegalArgumentException : element is Empty");
            else if (i % 2 == 0 && !this.isNumeric(element))
                throw new IllegalArgumentException("IllegalArgumentException : element is not valid");
            else if (i % 2 == 1 && !"+-/*".contains(element))
                throw new IllegalArgumentException("IllegalArgumentException : element is not valid");
        }
    }

    @Test
    @DisplayName("결과 확인")
    void executeCalculator() {
        String[] strArr = splitter(base);
        result = Integer.parseInt(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            if (isNumeric(strArr[i]))
                operate(operator, strArr[i]);
            else
                operator = strArr[i];
        }
        assertThat(result).isEqualTo(10);
    }
}
