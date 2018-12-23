package hello.Domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Author extends Person{
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Book> books = new ArrayList<>();


    public Author() {
    }

    public Author(String firstName, String lastName, List<Book> books) {
        super(firstName, lastName);
        this.books = books;
    }
}
