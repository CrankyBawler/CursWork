package pro.sky.Kurswork.Services;

import org.springframework.stereotype.Service;
import pro.sky.Kurswork.ExaminerService;
import pro.sky.Kurswork.Exceptions.ChooseFewerQuestionsException;
import pro.sky.Kurswork.Model.Question;
import pro.sky.Kurswork.QuestionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {

        Set<Question> set = new HashSet<>();
        if (amount > questionService.getAll().size()) {
            throw new ChooseFewerQuestionsException("choose fewer questions");
        }
        int ii = 0;
        while (ii < amount) {
            Question question = questionService.getRandomQuestion();
            if (!set.contains(question)) {
                set.add(question);
                ii += 1;
            }
        }

        return set;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExaminerServiceImpl that = (ExaminerServiceImpl) o;
        return Objects.equals(questionService, that.questionService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionService);
    }
}
