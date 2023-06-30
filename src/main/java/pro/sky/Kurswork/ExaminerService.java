package pro.sky.Kurswork;

import pro.sky.Kurswork.Model.Question;

import java.util.Collection;


public interface ExaminerService {
        Collection<Question> getQuestions(int amount);
    }

