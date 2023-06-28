package pro.sky.Kurswork.Services;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Kurswork.Exceptions.ChooseFewerQuestionsException;
import pro.sky.Kurswork.Model.Question;
import pro.sky.Kurswork.QuestionService;

import java.util.Arrays;
import java.util.Collection;

@ExtendWith(MockitoExtension.class)

class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private ExaminerServiceImpl examinerServiceImpl;

    @Test
    void getQuestions() {

        Question question1 = new Question("question1", "answer1");
        Question question2 = new Question("question2", "answer2");
        Question question3 = new Question("question3", "answer3");

        Collection<Question> questions1 = Arrays.asList(
                new Question("question1", "answer1"),
                new Question("question2", "answer2"),
                new Question("question3", "answer3")


        );
        Mockito.when(questionService.getAll()).thenReturn(questions1);

        Mockito.when(questionService.getRandomQuestion())
                .thenReturn(question1)
                .thenReturn(question2)
                .thenReturn(question3);


        Collection<Question> actual = examinerServiceImpl.getQuestions(3);

        Assertions.assertThat(actual.size()).isEqualTo(3);
        Assertions.assertThat(actual).contains(
                question1, question2, question3 //question4, question5
        );

    }

    @Test
    public void getQuestionsError() {
        Collection<Question> questions = Arrays.asList(
                new Question("question1", "answer1"),
                new Question("question2", "answer2"),
                new Question("question3", "answer3")
        );
        Mockito.when(questionService.getAll()).thenReturn(questions);
        org.junit.jupiter.api.Assertions.assertThrows(ChooseFewerQuestionsException.class, () -> examinerServiceImpl.getQuestions(5));
    }

}