package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    // Test Case 구현
    @Test
    @DisplayName("요구사항 1 : Set의 사이즈 구하기")
    public void test1() {
        assertThat(numbers).hasSize(3);
    }

    @DisplayName("요구사항 2 : 1, 2, 3의 값이 존재하는지를 확인하기")
    @ParameterizedTest
    @ValueSource(ints={1,2,3})
    public void test2(int input) {
        assertThat(numbers.contains(input)).isTrue();
    }

    @DisplayName("요구사항 3 : 1, 2, 3 값은 contains 메소드 실행결과 true, 4, 5 값을 넣으면 false 가 반환되는 테스트")
    @ParameterizedTest
    @CsvSource(value = {"true:1,2,3", "false:4,5"}, delimiter = ':')
    public void test3(boolean result, String input) {
        String[] arr = input.split(",");
        for (String str : arr) {
            assertEquals(result, numbers.contains(Integer.parseInt(str)));
        }
    }

}
