package hello.Controllers;


import hello.Domain.Author;
import hello.Domain.Book;
import hello.Service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService authorService ;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable("authorId") long authorId){
        return authorService.findAuthorById(authorId);
    }

    @DeleteMapping("/{authorId}")
    public void deleteAuthor(@PathVariable("authorId") long authorId){
        this.authorService.deleteAuthor(authorId);
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return this.authorService.addAuthor(author);
    }

    @PutMapping("/{idAuthor}")
    public Author updateAuthor(@PathVariable("idAuthor") long idAuthor, @RequestBody Author newAuthor){
        return (this.authorService.updateAuthor(idAuthor,newAuthor));
    }

    @PutMapping("/book/{idBook}")
    public void addBookToAuthor(@RequestBody  Author author , @PathVariable("idBook") long idBook ){
        this.authorService.addBookToAuthor(author,idBook);

    }
}
