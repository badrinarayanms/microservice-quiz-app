package com.badri.question_service.Service;

import com.badri.question_service.Model.Question;
import com.badri.question_service.Model.QuizWrapper;
import com.badri.question_service.Model.Response;
import com.badri.question_service.Repository.QuestionRepo;

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
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionByCaregory(String category) {


        try{
            return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionRepo.findByCategory(category), HttpStatus.BAD_REQUEST);
    }

    public Question addQuestion(Question question) {
        return questionRepo.save(question);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQ) {
        Pageable limit = PageRequest.of(0, numQ);
        List<Integer> questions = questionRepo.findRandomByCategory(category, limit);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuizWrapper>> getQuestionFromId(List<Integer> questionids) {


        List<QuizWrapper> quesforuser=new ArrayList<>();
        for(Integer i:questionids){
            Question q=questionRepo.findById(i).get();
            QuizWrapper qw=new QuizWrapper(q.getId(),q.getQuestiontitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            quesforuser.add(qw);
        }
        return new ResponseEntity<>(quesforuser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {


        int score=0;

        for(Response r:responses){
            Question question=questionRepo.findById(r.getId()).get();
            if(r.getResponse().equals(question.getRightanswer())){
                score++;
            }
        }
        return  new ResponseEntity<>(score,HttpStatus.OK);
    }
}
