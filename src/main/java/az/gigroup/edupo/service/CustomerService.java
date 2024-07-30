package az.gigroup.edupo.service;

import az.gigroup.edupo.dto.request.CustomerRequest;
import az.gigroup.edupo.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomer();

    CustomerResponse getCustomerById(Long id);

//    Customer updateCustomer(Long id, Customer updatedCustomer);
//
//    void deleteCustomer(Long id);
//
//    List<Customer> findCustomersByName(String name);
//
//    double calculateTotalPrice(Customer customer);
//
//    CustomerResponse createCustomerResponse(CustomerRequest customerRequest);
}