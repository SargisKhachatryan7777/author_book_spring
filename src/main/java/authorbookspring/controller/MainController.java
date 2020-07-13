package main.java.authorbookspring.controller;

import authorbookspring.model.Author;
import authorbookspring.model.Book;
import authorbookspring.repository.AuthorRepository;
import authorbookspring.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final  AuthorRepository authorRepository;
    private final  BookRepository bookRepository;


    @GetMapping("/")
    public String homePage(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);

        return "home";
    }

    @PostMapping("/addAuthor")
    public String addUser(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/authorList")
    public String allAuthorList(ModelMap modelMap) {
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "authorList";
    }

    @GetMapping("/bookList")
    public String allBookList(ModelMap modelMap) {
        List<Book> allBook = bookRepository.findAll();
        modelMap.addAttribute("books", allBook);
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        return "bookList";
    }

    @GetMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam("id") int id) {
        authorRepository.deleteById(id);
        return "redirect:/authorList";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/bookList";
    }

    @GetMapping("/bookById")
    public String bookByIde(@RequestParam("id") int id, ModelMap map) {
        Book book = bookRepository.getOne(id);
        map.addAttribute("book", book);
        List<Author> all = authorRepository.findAll();
        map.addAttribute("authors", all);
        return "editBook";
    }

    @PostMapping("/editBook")
    public String editBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/";
    }

    @GetMapping("/authorById")
    public String authorByIde(@RequestParam("id") int id, ModelMap map) {
        Author author = authorRepository.getOne(id);
        map.addAttribute("author", author);
        return "editAuthor";
    }

    @PostMapping("/editAuthor")
    public String changeAuthor(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/";
    }
}