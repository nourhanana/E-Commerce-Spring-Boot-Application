package hello.Service;


import hello.Domain.Book;
import hello.Domain.Commande;
import hello.Domain.Customer;
import hello.Domain.Panier;
import hello.Repository.BookRepository;
import hello.Repository.CommandeRepository;
import hello.Repository.CustomerRepository;
import hello.Repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    public CustomerRepository customerRep ;

    @Autowired
    public BookRepository bookRep ;

    @Autowired
    public PanierRepository panierRep;

    @Autowired
    public CommandeRepository commandeRep;

    @Autowired
    public PanierService panierService;


    public Customer addCustomer(Customer newCustomer) {
        Panier panier = new Panier();
        newCustomer.setPanier(panier);
        customerRep.save(newCustomer);
        return newCustomer;

    }

    public List<Customer> getAllCustomer() {
        return (List<Customer>) customerRep.findAll();
    }

    public void delete(Long id) {
        customerRep.deleteById(id);
    }

    public void update(long idCustomer, Customer newCustomer) {
        /*if(!customerRep.existsById(newCustomer.getId())){
            return false;
        }
        customerRep.save(newCustomer);
        return true; */
        Customer customer = customerRep.findById(idCustomer).get();
        customer.setFirstName(newCustomer.getFirstName());
        customer.setLastName(newCustomer.getLastName());
        customer.setSolde(newCustomer.getSolde());
        customerRep.save(customer);

    }
    public void up(Customer newCustomer){
        customerRep.save(newCustomer);
    }



    public Customer getbyId(Long id) {
        return  customerRep.findById(id).get();
    }

    @Transactional
    public void addBookToPanier(Long id,long idBook) {
        Customer customer = customerRep.findById(id).get();
        Book bookDb = bookRep.findById(idBook).get();
        if(customer != null) {
        bookDb.setAvailabilities(bookDb.getAvailabilities()-1);
        List<Book> books=customer.getPanier().getBooks();
        books.add(bookDb);
        customerRep.save(customer);
        bookRep.save(bookDb);
            System.out.println("availabilities"+bookDb.getAvailabilities()+"id book"+bookDb.getId());}

    }

    @Transactional
    public void removeBookFromPanier(Long id, long idBook) {
        Customer customer = customerRep.findById(id).get();
        if(customer != null) {
            Long idPanier = customer.getPanier().getId();
            Panier panier = panierRep.findById(idPanier).get();
            List<Book> bookList = panier.getBooks();
            System.out.println(bookList.size());
            Book removeBook = bookRep.findById(idBook).get();
            int index = bookList.indexOf(removeBook);
            bookList.remove(index);
            removeBook.setAvailabilities(removeBook.getAvailabilities()+1);
            customerRep.save(customer);
            panierRep.save(panier);
            bookRep.save(removeBook);
        }
    }

    @Transactional
    public void confirmer(long id) throws Exception {
        Customer customer = customerRep.findById(id).orElseThrow(() -> new NullPointerException("No user found with customer id "+ id ));
        long idPanier = customer.getPanier().getId();
        Panier panier = panierRep.findById(idPanier).get();
        Float reduction = panierService.reduction(id);
        if (customer.getSolde() > reduction) {
            customer.setSolde(customer.getSolde() - reduction);
            Commande commande = new Commande(customer,panier.getBooks(),new Date());
            List<Commande> commandeList = customer.getCommandes();
            commandeRep.save(commande);
            commandeList.add(commande);
            customerRep.save(customer);
            panier.setBooks(null);
            panierRep.save(panier);
            System.out.println(commande.toString());

        }
        else
        {
            throw new Exception("Solde insuffisant");
        }

    }

     public List<Book> getAllBookFromPanier(Long idCustomer){
        Customer customer = customerRep.findById(idCustomer).get();
        if(customer != null) {
            Panier panier = customer.getPanier();
            return panier.getBooks();
        }
        return null ;

     }




}
