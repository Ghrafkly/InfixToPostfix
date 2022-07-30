import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InfixToPostfixTest {

    InfixToPostfix itp;

    @BeforeEach
    void setUp() {
        itp = new InfixToPostfix();
    }

    @ParameterizedTest(name = "Test #{index}: {0} = {1}")
    @MethodSource("infixBasicOperators")
    void testInfixToPostfix_Basic(String infix, String postfix) {
        assertEquals(postfix, itp.evaluate(infix));
    }

    static Stream<Arguments> infixBasicOperators() {
        return Stream.of(
                Arguments.of("1+2+3", "1 2 + 3 +"),
                Arguments.of("1-2-3", "1 2 - 3 -"),
                Arguments.of("1*2*3", "1 2 * 3 *"),
                Arguments.of("1/2/3", "1 2 / 3 /"),
                Arguments.of("1%2%3", "1 2 % 3 %"),
                Arguments.of("1^2^3", "1 2 ^ 3 ^")
        );
    }

    @ParameterizedTest(name = "Test #{index}: {0} = {1}")
    @MethodSource("infixParenthesesAndNegatives")
    void testInfixToPostfix_ParenthesesAndNegatives(String infix, String postfix) {
        assertEquals(postfix, itp.evaluate(infix));
    }

    static Stream<Arguments> infixParenthesesAndNegatives() {
        return Stream.of(
                Arguments.of("(1+2)+3", "1 2 + 3 +"),
                Arguments.of("1-(2-3)", "1 2 3 - -"),
                Arguments.of("1*2+(3--4)-3", "1 2 * 3 -4 - + 3 -"),
                Arguments.of("1*2-(-2-3)-3", "1 2 * -2 3 - - 3 -"),
                Arguments.of("1*2-(2-3)--3", "1 2 * 2 3 - - -3 -")
        );
    }
}