package com.badri.quiz_service.Controller;

import com.badri.quiz_service.Model.QuizDto;
import com.badri.quiz_service.Model.QuizWrapper;
import com.badri.quiz_service.Model.Response;
import com.badri.quiz_service.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategory(),quizDto.getTitle(),quizDto.getNumQ());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuizWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getResult(@PathVariable Integer id, @RequestBody List<Response> response){
        return quizService.getResut(id,response);
    }
}
