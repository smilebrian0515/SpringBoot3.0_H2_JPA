package com.example.demo.unittest;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ForTryTest {

    @Test
    public void hasLength_String_null() {
        // Arrange
        String str = null;
        boolean excepted = false;

        // Act
        boolean actual = StringUtils.hasLength(str);

        // Assert
        assertThat(actual).isEqualTo(excepted);
    }

    @Test
    public void Case4(){
        int actual1 = 5;
        String actual2 = "10";

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actual1).isLessThan(6);

        softAssertions.assertThat(actual2).isEqualTo("10");

        softAssertions.assertAll();
    }

    @Test
    public void example_Exception() {
        Throwable throwable = catchThrowable(()-> throwException());

        assertThat(throwable)
                .isExactlyInstanceOf(Exception.class)
                .hasMessageContaining("Magic");
    }

    private void throwException() throws Exception {
        throw new Exception("Magic");
    }

    @Test
    public void Test() throws IOException {
        Path path = Paths.get("src/test/resources/response.json");
        String s = Files.readString(path);
        System.out.println(s);
    }
}
