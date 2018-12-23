package hello.Controllers;


import hello.Domain.Book;
import hello.Service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService ;


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    private List<Book> getAllBook() {
        return this.bookService.getAllBook();
    }

    @GetMapping("/{idBook}")
    private Book getBookById(@PathVariable("idBook") long id){
        return this.bookService.getBookById(id);
    }

    @DeleteMapping("/{idBook}")
    private void deletebook(@PathVariable("idBook") long id) {
        this.bookService.delelteBook(id);
    }

    @PostMapping
    private Book addBook(@RequestBody Book newBook) {
        return (this.bookService.addBook(newBook));
    }

    @PutMapping("/{idBook}")
    private Book updateBook(long id , @RequestBody Book newBook){
            return bookService.updateBook(id,newBook);
    }
}
