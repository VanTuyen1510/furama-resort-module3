package service.impl;

import model.customer_bean.Customer;
import repository.impl.CustomerRepositoryImpl;
import repository.itf.ICustomerRepository;
import service.itf.ICustomerService;

import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    private static ICustomerRepository repository = new CustomerRepositoryImpl();

    @Override
    public void add(Customer customer) {
        repository.add(customer);
    }

    @Override
    public void edit(Customer customer) {
       repository.edit(customer);
    }

    @Override
    public void delete(Customer customer) {
       repository.delete(customer);
    }

    @Override
    public Customer findByIdCustomer(int id) {
        return repository.findByIdCustomer(id);
    }

    @Override
    public List<Customer> searchByName(String name) {
        return repository.searchByName(name);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    public List<Customer> findAllWithPaging(int page, int record){
        return repository.findAllWithPaging(page,record);
    }
}
