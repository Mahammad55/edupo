package az.gigroup.edupo.controller;

import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.exception.CustomerAlreadyExistsException;
import az.gigroup.edupo.exception.CustomerNotFoundException;
import az.gigroup.edupo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import az.gigroup.edupo.entity.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody Customer customer) {
        try {
            CustomerResponse customerResponse = customerService.createCustomerResponse(customer);
            return ResponseEntity.ok(customerResponse);
        } catch (CustomerAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Customer>> getCustomerById(@PathVariable Long id) {
        try {
            Optional<Customer> customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customer);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (CustomerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> findCustomersByName(@RequestParam String name) {
        List<Customer> customers = customerService.findCustomersByName(name);
        return ResponseEntity.ok(customers);
    }
}
