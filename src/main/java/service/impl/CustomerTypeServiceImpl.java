package service.impl;

import model.customer_bean.CustomerType;
import repository.impl.CustomerTypeRepositoryImpl;
import repository.itf.ICustomerTypeRepository;
import service.itf.ICustomerTypeService;

import java.util.List;

public class CustomerTypeServiceImpl implements ICustomerTypeService {
    private ICustomerTypeRepository repository = new CustomerTypeRepositoryImpl();
    @Override
    public CustomerType findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<CustomerType> findAll() {
        return repository.findAll();
    }
}
