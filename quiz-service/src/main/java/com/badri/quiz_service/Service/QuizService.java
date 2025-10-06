package com.badri.quiz_service.Service;

import com.badri.quiz_service.Model.Quiz;
import com.badri.quiz_service.Model.QuizWrapper;
import com.badri.quiz_service.Model.Response;
import com.badri.quiz_service.Repository.QuizRepo;
import com.badri.quiz_service.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    QuizInterface quizInterface;
    public ResponseEntity<String> createQuiz(String category,String title, Integer numQ) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuizWrapper>> getQuiz(int id) {
        Quiz quiz=quizRepo.findById(id).get();
        List<QuizWrapper> quesforuser =quizInterface.getQuestionFromId(quiz.getQuestionIds()).getBody();

        return new ResponseEntity<>(quesforuser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getResut(Integer id, List<Response> response) {
        int score=quizInterface.getScore(response).getBody();
        return  new ResponseEntity<>(score,HttpStatus.OK);
    }
}
