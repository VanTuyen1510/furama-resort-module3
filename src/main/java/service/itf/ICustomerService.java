package service.itf;

import model.customer_bean.Customer;

import java.util.List;

public interface ICustomerService {
    void add (Customer customer);
    void edit(Customer customer);
    void delete(Customer customer);
    Customer findByIdCustomer(int id);
    List<Customer> searchByName(String name);
    List<Customer> findAll();
    List<Customer> findAllWithPaging(int page,int record);

}
