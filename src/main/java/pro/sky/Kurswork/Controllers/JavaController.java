package pro.sky.Kurswork.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Kurswork.Model.Question;
import pro.sky.Kurswork.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("exam/java")
public class JavaController {
    private final QuestionService questionService;

    public JavaController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(path = "/add")
    public Question addQuestion(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping(path = "/find")
    public Question findQuestion(Question question) {
        return questionService.find(question);
    }

    @GetMapping(path = "/remove")
    public Question removeQuestion(Question question) {
        return questionService.remove(question);
    }

    @GetMapping()
    public Collection<Question> getAll() {
        return questionService.getAll();
    }
}

