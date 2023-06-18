package pro.sky.Kurswork;

import pro.sky.Kurswork.Model.Question;

import java.util.Collection;

public interface QuestionService {
    Question add(String question, String answer);

    Question find(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();


}
