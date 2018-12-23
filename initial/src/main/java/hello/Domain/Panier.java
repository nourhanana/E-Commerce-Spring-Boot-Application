package hello.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="Panier")
@SequenceGenerator(name="seq2", initialValue=1, allocationSize=100)
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq2")
    private long id ;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Book> books ;
    @JsonIgnore
    @OneToOne (mappedBy = "panier")
    private  Customer customer ;
    @Transient
    private Float promotion ;


}
