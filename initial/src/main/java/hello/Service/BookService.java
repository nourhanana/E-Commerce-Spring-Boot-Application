package hello.Service;

import hello.Domain.Author;
import hello.Domain.Book;
import hello.Repository.AuthorRepository;
import hello.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository ;

    @Autowired
    public AuthorRepository authorRepository ;

    @Transactional
    public Book addBook(Book newBook) {
        List<Author> authors = newBook.getAuthors();
        bookRepository.save(newBook);
        for(Author auth : authors) {
            auth.getBooks().add(newBook);
            authorRepository.save(auth);

        }
        return newBook ;
    }

    public List<Book> getAllBook(){
        return (List<Book>) bookRepository.findAll();
    }

    public void delelteBook(Long idBook) {
        bookRepository.deleteById(idBook);
    }

    public Book updateBook(Long idBook , Book newBook) {
        Book book = bookRepository.findById(idBook).get();
        List<Author> authors =  book.getAuthors();
        for (Author author : authors) {
          author.getBooks().remove(book);
        }
        book.setAuthors(null);
        newBook.setId(idBook);
        List<Author> authorList = newBook.getAuthors();
        for(Author auth : authorList) {
            auth.getBooks().add(newBook);
            authorRepository.save(auth);

        }
        bookRepository.save(newBook);
        return(newBook);
    }

    public Book getBookById(Long id) {
        return (bookRepository.findById(id).get());
    }

}
