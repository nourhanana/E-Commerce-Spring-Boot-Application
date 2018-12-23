package hello.Domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Commande {
    @Id
    @SequenceGenerator(name = "commandeSeq", initialValue = 1 ,allocationSize = 200)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "commandeSeq")
    private Long id ;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;
    @OneToMany
    private List<Book> books ;

    private Date  date;

    protected  Commande(){

    }

    public Commande(Customer customer, List<Book> books, Date date) {
        this.customer = customer;
        this.books = books;
        this.date = date;
    }
}
