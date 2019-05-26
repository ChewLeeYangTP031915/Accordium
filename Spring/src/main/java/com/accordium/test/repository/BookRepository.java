package com.accordium.test.repository;

import com.accordium.test.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book,Long>{

    @Query(value = "SELECT * FROM `book` " +
            "where author like CONCAT(:keyword,'%') " +
            "or name like CONCAT(:keyword,'%')",nativeQuery = true)
    List<Book> findByAuthorOrNameContaining(@Param("keyword") String keyword);
}
