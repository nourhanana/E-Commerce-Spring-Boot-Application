package hello.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Books")
@Data
@SequenceGenerator(name="seq3", initialValue=1, allocationSize=100)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq3")
    private long id ;
    private String title;
    private String category;
    private String language;
    private Date publishDate;
    private int availabilities ;


    @ManyToMany(mappedBy = "books",cascade = CascadeType.ALL)
    private List<Author> authors;

    private Float price;

    @JsonIgnore
    @ManyToOne
    private Panier panier ;

    @JsonIgnore
    @ManyToOne
    private Commande commande ;

    public Book(String title, String category, String language, Date publishDate, int availabilities, List<Author> authors,Float price) {
        this.title = title;
        this.category = category;
        this.language = language;
        this.publishDate = publishDate;
        this.availabilities = availabilities;
        this.authors = authors;
        this.price = price;
    }

    public Book(String title, String category, String language, Date publishDate, int availabilities, List<Author> authors, Float price, Panier panier) {
        this.title = title;
        this.category = category;
        this.language = language;
        this.publishDate = publishDate;
        this.availabilities = availabilities;
        this.authors = authors;
        this.price = price;
        this.panier = panier;
    }

    protected Book() {}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", language='" + language + '\'' +
                ", publishDate=" + publishDate +
                ", availabilities=" + availabilities +
                ", authors=" + authors +
                '}';
    }
}
