package co.edu.javeriana.tg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.tg.entities.Customer;
import co.edu.javeriana.tg.repositories.CustomerRepository;

@RestController
public class Example {
    
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public void main(){
        System.out.println("Hola Mundo");
        Customer c=new Customer();
        c.setId(5L);
        c.setFirst_name("Juan");
        c.setLast_name("Perez");
        customerRepository.save(c);
        for (Customer w:customerRepository.findAll()){
            System.out.println(w.toString());
        }
    }
}
