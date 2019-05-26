package com.accordium.test.service;

import com.accordium.test.entity.Book;
import com.accordium.test.repository.BookRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class SampleDataService {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private BookRepository bookRepository;

    @Value("${load.sample.data}")
    private boolean loadSampleData;

    @PostConstruct
    public void populateDummyData() throws Exception{
        if(loadSampleData){
            log.info("Loading sample data");
            ObjectMapper mapper = new ObjectMapper();

            Resource resource = resourceLoader.getResource("classpath:Booklist.json");
            InputStream inputStream = resource.getInputStream();

            List<Book> books = mapper.readValue(inputStream,new TypeReference<List<Book>>(){});
            log.info("Going to insert {} sample books details into DB",books.size());

            bookRepository.saveAll(books);

            log.info("Successfully inserted sample data");
        }
    }
}
