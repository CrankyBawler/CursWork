package pro.sky.Kurswork.Services;

import org.springframework.stereotype.Service;
import pro.sky.Kurswork.Exceptions.QuestionNotFoundException;
import pro.sky.Kurswork.Model.Question;
import pro.sky.Kurswork.QuestionService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JavaQuestionService implements QuestionService {
    Set<Question> questions = new HashSet<>();
    Random random = new Random();


    @Override
    public Question add(String question, String answer) {
        Question question1 = new Question(question, answer);
        questions.add(question1);
        return question1;
    }

    @Override
    public Question find(Question question) {
        if (questions.contains(question)) {
            return question;
        } else {
            throw new QuestionNotFoundException("Вопрос не найден");
        }


    }

    @Override
    public Question remove(Question question) {
        if (questions.contains(question)) {
            questions.remove(question);
            return question;
        }
        throw new QuestionNotFoundException("Вопрос не найден");
    }

    @Override
    public Collection<Question> getAll() {
        return new ArrayList<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> list = questions.stream().toList();
        int a = random.nextInt(questions.size());
        return list.get(a);
    }



}
