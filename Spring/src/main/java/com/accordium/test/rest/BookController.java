package com.accordium.test.rest;

import com.accordium.test.dto.BookDto;
import com.accordium.test.entity.Book;
import com.accordium.test.repository.BookRepository;
import com.accordium.test.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/search")
    public RestResponse<List<BookDto>> searchBooksByAuthorOrName(
            HttpServletRequest request,
            @RequestParam(value = "keyword") String keyword
    ){
        log.info("Request: {},param:{}",request.getRequestURI(),keyword);
        List<Book> bookList = bookRepository.findByAuthorOrNameContaining(keyword);
        log.info("Found books : {}",bookList.size());
        List<BookDto> bookDtoList = bookList.stream().map(b-> convertToDTO(b)).collect(Collectors.toList());
        return new RestResponse<>(RestResponse.STATUS_OK,"Successful",bookDtoList);
    }

    private BookDto convertToDTO (Book book){

        BookDto dto = modelMapper.map(book,BookDto.class);

        return dto;
    };

}
