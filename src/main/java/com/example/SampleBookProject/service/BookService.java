package com.example.SampleBookProject.service;


import com.example.SampleBookProject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SampleBookProject.repository.BookRepository;

import java.util.List;

@Service
public class BookService  {
    @Autowired
    private BookRepository bookRepository;


    public void createBook(Book book){
bookRepository.save(book);
    }
    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    public Book getBookById(Long book){

        return bookRepository.getOne(book);
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
}
