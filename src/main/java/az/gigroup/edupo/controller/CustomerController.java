package az.gigroup.edupo.controller;

import az.gigroup.edupo.dto.criteria.CustomerSearchCriteria;
import az.gigroup.edupo.dto.request.CustomerRequest;
import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/customers")
@Tag(name = "Customer Controller")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Validated @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.status(CREATED).body(customerService.createCustomer(customerRequest));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAllCustomer(@PageableDefault(sort = "id") Pageable pageable, CustomerSearchCriteria criteria) {
        return ResponseEntity.ok(new PageImpl<>(customerService.getAllCustomer(pageable,criteria)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long id, @Validated @RequestBody CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponse>> getCustomerByName(@RequestParam String name) {
        return ResponseEntity.ok(customerService.getCustomerByName(name));
    }
}
