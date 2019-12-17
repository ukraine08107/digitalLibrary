package com.example.SampleBookProject.controler;

import com.example.SampleBookProject.model.Book;
import com.example.SampleBookProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/index")
    public String showAllBooks(Model model){
        List<Book> listAllBooks = bookService.findAll();
        model.addAttribute("allBooks", listAllBooks);
        return "index";
    }

    @RequestMapping(value = "/addBook")
    public String saveBookInDatabase(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("isbn") int isbn,
            @RequestParam("description") String description){
        Book book = new Book(title,author,isbn,description);
        bookService.createBook(book);
        return "redirect:/index";
    }
    @RequestMapping(value = "/retrieveInfo/{id}")
    public String showExisting(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "update";
    }
    @RequestMapping(value = "/updateBook/{id}")
    public String updateTask(@PathVariable("id") Long id,
                             @RequestParam("author") String author,
                             @RequestParam("title") String title,
                             @RequestParam("isbn") int isbn,
                             @RequestParam("description") String description){
        Book book = bookService.getBookById(id);
        book.setAuthor(author);
        book.setTitle(title);
        book.setISBN(isbn);
        book.setDescription(description);
        bookService.createBook(book);
        return "redirect:/index";
    }
    @RequestMapping(value = "/deleteBook/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        Book bookToDelete = bookService.getBookById(id);
        bookService.deleteBook(bookToDelete);
        return "redirect:/index";
    }
}