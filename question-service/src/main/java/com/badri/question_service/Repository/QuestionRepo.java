package com.badri.question_service.Repository;

import com.badri.question_service.Model.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);


    @Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RANDOM()", nativeQuery = true)
    List<Integer> findRandomByCategory(@Param("category") String category, Pageable pageable);

}
