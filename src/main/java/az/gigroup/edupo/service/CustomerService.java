package az.gigroup.edupo.service;

import az.gigroup.edupo.dto.response.CustomerResponse;
import az.gigroup.edupo.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long id);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Long id, Customer updatedCustomer);
    void deleteCustomer(Long id);
    List<Customer> findCustomersByName(String name);
    double calculateTotalPrice(Customer customer);
    CustomerResponse createCustomerResponse(Customer customer);

}
