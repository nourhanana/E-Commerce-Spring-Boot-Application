package hello.Domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class Customer extends Person {
    @OneToOne (cascade = CascadeType.ALL,optional = false)
    @JsonIgnore
    private Panier panier ;
    private Float solde;
    @JsonIgnore
    @OneToMany
    private List<Commande> commandes ;

    protected Customer() {}

    public Customer(String firstName, String lastName, Float solde) {
        super(firstName, lastName);
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "Customer{" + " First Name " + getFirstName() + " Last Name " + getLastName() + '}';

    }

    /*public String books(List<Book> books) {
        String list= "";
        for (Book book : books) {
            list = list + "book"+ book.toString();

        }
        return list ;
    }*/
}
