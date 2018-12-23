package hello.Controllers;


import hello.Domain.Book;
import hello.Domain.Customer;
import hello.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {


    private CustomerService customerService ;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public List<Customer> getAllCustomer(){
        return this.customerService.getAllCustomer();
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") long customerId){
        return this.customerService.getbyId(customerId);
    }


    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable long customerId) {
        this.customerService.delete(customerId);
    }

    @PutMapping("/{customerId}")
    public void updateCustomer(@PathVariable long customerId , @RequestBody Customer customer){
        customerService.update(customerId,customer);
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer newCustomer) {
        return this.customerService.addCustomer(newCustomer);
    }

    @PutMapping("/{idCustomer}/addBook/{idBook}")
    public void addBookToPanier(@PathVariable("idCustomer") long idCustomer ,@PathVariable("idBook") long idBook ){
            this.customerService.addBookToPanier(idCustomer,idBook);
    }

    @PutMapping("/{idCustomer}/removeBook/{idBook}")
    public void removeFromPanier(@PathVariable("idCustomer") long idCustomer , @PathVariable("idBook") long idBook){
        this.customerService.removeBookFromPanier(idCustomer,idBook);
    }

    @GetMapping("/Panier/{idCustomer}")
    public List<Book> getAllBooksFromPanier(@PathVariable("idCustomer") long idCustomer){
        return this.customerService.getAllBookFromPanier(idCustomer);
    }

    @PutMapping("/confirmer/{idCustomer}")
    public void confirmer(@PathVariable("idCustomer") long idCustomer) throws Exception {
        this.customerService.confirmer(idCustomer);
    }

}
