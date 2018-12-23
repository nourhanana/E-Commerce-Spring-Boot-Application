package hello.Service;

import hello.Domain.Author;
import hello.Domain.Book;
import hello.Repository.AuthorRepository;
import hello.Repository.BookRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    public AuthorRepository authorRepository ;

    @Autowired
    public BookRepository bookRepository;

    public Author addAuthor(Author newAuthor) {
       return  authorRepository.save(newAuthor);
    }

    public void deleteAuthor(long id) {
       authorRepository.deleteById(id);
    }

    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    public Author updateAuthor(long id , Author newAuthor) {
        newAuthor.setId(id);
        return authorRepository.save(newAuthor);
    }

    public Author findAuthorById(Long id){
        return authorRepository.findById(id).get();
    }

    @Transactional
    public void addBookToAuthor(Author author ,long idBook) {
        author= authorRepository.findById(author.getId()).get();
        List<Book> listBook = author.getBooks();
        Book book=bookRepository.findById(idBook).get();
        listBook.add(book);
        authorRepository.save(author);


    }
}
