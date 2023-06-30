package pro.sky.Kurswork.Services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.Kurswork.Exceptions.QuestionNotFoundException;
import pro.sky.Kurswork.Model.Question;
import pro.sky.Kurswork.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {

    public static final String QUESTION1 = "Question1";
    public static final String QUESTION2 = "Question2";
    public static final String QUESTION3 = "Question3";
    public static final String QUESTION7 = "Question7";

    public static final String ANSWER4 = "Answer4";
    public static final String ANSWER5 = "Answer5";
    public static final String ANSWER6 = "Answer6";
    public static final Question NOTFOUNDQUESTION = new Question("Question50", "Answer50");
    public static final Question QUESTION4 = new Question(QUESTION1, ANSWER4);
    public static final Question QUESTION5 = new Question(QUESTION2, ANSWER5);
    public static final Question QUESTION6 = new Question(QUESTION3, ANSWER6);
    private QuestionService questionService;

    public static Stream<Arguments> findTestParams() {
        return Stream.of(
                Arguments.of(QUESTION4),
                Arguments.of(QUESTION5),
                Arguments.of(QUESTION6));
    }

    @BeforeEach
    public void beforeEach() {
        questionService = new JavaQuestionService();

        questionService.add(QUESTION1, ANSWER4);
        questionService.add(QUESTION2, ANSWER5);
        questionService.add(QUESTION3, ANSWER6);
    }

    @Test
    public void addTest() {
        Question expected = new Question(QUESTION7, ANSWER4);

        Question actual = questionService.add(QUESTION7, ANSWER4);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("findTestParams")
    public void findTest(Question question) {

        Question actual = questionService.find(question);

        assertEquals(question, actual);
    }

    @Test
    public void findNegativeTest() {
        QuestionNotFoundException exception = assertThrows(QuestionNotFoundException.class,
                () -> questionService.find(NOTFOUNDQUESTION));

        assertEquals("Вопрос не найден", exception.getMessage());

    }

    @Test
    public void removeTest() {
        Question expected = new Question(QUESTION2, ANSWER5);

        Question actual = questionService.remove(QUESTION5);

        assertEquals(expected, actual);
    }

    @Test
    public void removeNegativeTest() {
        assertThrows(QuestionNotFoundException.class, () -> questionService.remove(NOTFOUNDQUESTION));
    }

    @Test
    public void getAllTest() {

        List<Question> expected = new ArrayList<>();
        expected.add(QUESTION4);
        expected.add(QUESTION5);
        expected.add(QUESTION6);

        Collection<Question> actual = questionService.getAll();

        assertEquals(expected, actual);
    }

}