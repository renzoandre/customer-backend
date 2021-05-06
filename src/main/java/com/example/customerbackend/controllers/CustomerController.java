package com.example.customerbackend.controllers;

import java.util.List;

import com.example.customerbackend.documents.CustomerDocument;
import com.example.customerbackend.repositories.CustomerRepository;
import com.example.customerbackend.responses.ResponseCustomer;
import com.mongodb.internal.connection.ClusterDescriptionHelper.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/")
    public List<CustomerDocument> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/pagination")
    public ResponseCustomer getCustomersByPage(@Param(value = "page") int page, @Param(value = "size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerDocument> customers = customerRepository.findAll(pageable);

        System.out.println(customers.getContent().size());

        ResponseCustomer res = new ResponseCustomer(customers.getContent(), customers.getTotalPages(),
                customers.getNumber(), customers.getSize(), customers.getTotalElements());
        return res;
    }

    @GetMapping("/name")
    public ResponseCustomer getCustomersByPageName(@Param(value = "name") String name, @Param(value = "page") int page,
            @Param(value = "size") int size) {
        Pageable pageable = PageRequest.of(page, size);

        CustomerDocument customerDocument = new CustomerDocument();
        // Predicate predicate = customerDocument.getFirstName().startsWith("A");
        // ExampleMatcher matcher =
        // ExampleMatcher.matchingAny().withIgnoreCase().withMatcher("firstName",
        // GenericPropertyMatcher.of(StringMatcher.STARTING))

        Page<CustomerDocument> customers = customerRepository.findByQuery(".*" + name + ".*", pageable);

        System.out.println(customers.getContent().size());

        ResponseCustomer res = new ResponseCustomer(customers.getContent(), customers.getTotalPages(),
                customers.getNumber(), customers.getSize(), customers.getTotalElements());
        return res;
    }

    @PostMapping("/create-customer")
    public CustomerDocument createCustomer(@Validated @RequestBody CustomerDocument c) {
        return customerRepository.insert(c);
    }

    @PutMapping("/update-customer/{id}")
    public CustomerDocument updateCustomer(@PathVariable String id, @Validated @RequestBody CustomerDocument c) {
        c.setId(id);
        return customerRepository.save(c);
    }

}