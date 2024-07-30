package az.gigroup.edupo.service;

import az.gigroup.edupo.dto.request.CustomerRequest;
import az.gigroup.edupo.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomer();

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomer(Long id, CustomerRequest customerRequest);

    void deleteCustomer(Long id);

//    void deleteCustomer(Long id);

//    List<Customer> findCustomersByName(String name);

}