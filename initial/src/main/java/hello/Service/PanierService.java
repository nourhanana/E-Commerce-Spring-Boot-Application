package hello.Service;


import hello.Domain.Book;
import hello.Domain.Customer;
import hello.Domain.Panier;
import hello.Repository.CustomerRepository;
import hello.Repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PanierService {

    @Autowired
    PanierRepository panierRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Float totalPrice(long idPanier) {
        Float total = new Float(0);
        Panier panier = panierRepository.findById(idPanier).get();
        for (Book book : panier.getBooks())
            total = total + book.getPrice();
        return (total);
    }

    @Transactional
    public Float reduction(long idCustomer) {
        Customer customer = customerRepository.findById(idCustomer).get();
        Float price;
        if (customer != null) {
            Panier panier = panierRepository.findById(customer.getPanier().getId()).get();
            List<Book> bookList = panier.getBooks();
            if (bookList.size() < 4) {
                price = totalPrice(panier.getId());
                panier.setPromotion((float) 10 / 100);
                price = price - price * panier.getPromotion();
                return (price);
            } else if (bookList.size() == 4) {
                price = totalPrice(panier.getId());
                panier.setPromotion((float) 30 / 100);
                price = price - price * panier.getPromotion();
                return (price);
            } else if (bookList.size() < 10) {
                price = totalPrice(panier.getId());
                panier.setPromotion((float) 50 / 100);
                price = price - price * panier.getPromotion();
                return (price);
            } else
                price = totalPrice(panier.getId());
            panier.setPromotion((float) 70 / 100);
            price = price - price * panier.getPromotion();
            return (price);


        }
        return null ;

    }


}







