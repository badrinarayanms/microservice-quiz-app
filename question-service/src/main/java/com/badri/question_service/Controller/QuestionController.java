package com.badri.question_service.Controller;

import com.badri.question_service.Model.Question;
import com.badri.question_service.Model.QuizWrapper;
import com.badri.question_service.Model.Response;
import com.badri.question_service.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public  ResponseEntity<List<Question>>  getQuestionByCaregory(@PathVariable String category){
        return questionService.getQuestionByCaregory(category);
    }

    @PostMapping("add")
    public  ResponseEntity<String>  addQuestion(@RequestBody Question question){
         questionService.addQuestion(question);
         return  new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,@RequestParam Integer numQ){
        return questionService.getQuestionsForQuiz(category,numQ);
    }

    @PostMapping("getquestions")
    public ResponseEntity<List<QuizWrapper>> getQuestionFromId(@RequestBody List<Integer> questionids){
        return  questionService.getQuestionFromId(questionids);

    }

    @PostMapping("score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
