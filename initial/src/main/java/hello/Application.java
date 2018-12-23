package hello;

import hello.Domain.Author;
import hello.Domain.Book;
import hello.Domain.Customer;
import hello.Service.AuthorService;
import hello.Service.BookService;
import hello.Service.CustomerService;
import hello.Service.PanierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@SpringBootApplication
@EnableSwagger2
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public CommandLineRunner demo(CustomerService customerService, BookService bookService, AuthorService authorService, PanierService panierService) {
        return (args) -> {

            // save a couple of customers
          /*  repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler")); */
            List<Book> books = Collections.emptyList();
            Customer newC =new Customer("Hanana" , "Nour",1900F);
            Customer newC1 =new Customer("Ayed" , "Miras",1000F);
            Customer newC2 =new Customer("Nouisser" , "KHalil",500f);
            customerService.addCustomer(newC);
            customerService.addCustomer(newC1);
            customerService.addCustomer(newC2);
            for (Customer customer : customerService.getAllCustomer()) {
                log.info(customer.toString());
            }


            // Customer newC4 =customerService.getbyId(2l);
            //newC4.setLastName("Rofusette");
            //customerService.update(newC4);
            log.info("------- final update -------");
            for (Customer customer : customerService.getAllCustomer()) {
                log.info(customer.toString());


            }
            List<Author> authors =new ArrayList<Author>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date=sdf.parse("01/08/2000");
            Book  book1 = new Book("Spring" , "Framework" , "English" , date , 5,authors , 500F);
            Book  book2 = new Book("SpringBoot" , "Framework" , "English" , date , 9,authors, 300F);
            Book  book3 = new Book("French" , "Language" , "French" , date , 1,authors, 200F);
            Book  book4 = new Book("UML" , "Design Patterns" , "English" , date , 9,authors, 100F);
            bookService.addBook(book1);
            bookService.addBook(book2);
            bookService.addBook(book3);
            bookService.addBook(book4);
            customerService.addBookToPanier((long) 1,1l);
            customerService.addBookToPanier((long) 1, 2l);
            customerService.addBookToPanier((long) 1, 3l);
            customerService.addBookToPanier((long) 1, 4l);
           /* System.out.println(bookService.getBookById((long)1).getAvailabilities());
            System.out.println("TOTAL avant " +panierService.totalPrice(customerService.getbyId((long)1).getPanier().getId()));
            //customerService.removeBookFromPanier((long) 1,1L);
            System.out.println("after"+bookService.getBookById((long)1).getAvailabilities());
            panierService.reduction(newC.getId());
            System.out.println("TOTAL " +panierService.totalPrice(customerService.getbyId((long)1).getPanier().getId()));
            System.out.println("promotion "+panierService.reduction(newC.getId()));
            //customerService.confirmer((long)1);
            System.out.println(customerService.getbyId((long)1).getSolde());*/
            List<Book> bookList = new ArrayList<Book>();
            Author author1 =new Author("Taaaaha","Hsine",null);
            Author author2 =new Author("author2","ben foulen",bookList);
            authorService.addAuthor(author1);
            authorService.addAuthor(author2);
            //authorService.addBookToAuthor(authorService.findAuthorById(4L),2L);
            //System.out.println(authorService.findAuthorById(4l).getFirstName());




            //customerService.getbyId((long)1).toString();

           // customerService.save(newC1);

            /*Author author1 =repository.save(new Author("Michelle", "Dessler", new ArrayList<Book>()));
            Author author2 =repository.save(new Author("David", "Palmer"));
            List<Author> list = Collections.emptyList();
            list.add(author1);
            list.add(author2);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date=sdf.parse("01/08/2000");

           // repository.save(new Book("dear john","Romance" , "English" , date ,list));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Person customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L)
                    .ifPresent(customer -> {
                        log.info("Customer found with findById(1L):");
                        log.info("--------------------------------");
                        log.info(customer.toString());
                        log.info("");
                    });

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            // for (Customer bauer : repository.findByLastName("Bauer")) {
            // 	log.info(bauer.toString());
            // }
            log.info(""); */
        };


}
}
