package com.ruoyi.games.mapper;

import com.ruoyi.games.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerMapper {

    List<Customer> getCustomerList(Customer customer);

    List<Customer> getCustomers();

    List<Customer> getCustomerType();

    Customer getCustomerInfoById(Integer id);

    void updateCustomer(Customer customer);

    void addCustomer(Customer customer);

}
