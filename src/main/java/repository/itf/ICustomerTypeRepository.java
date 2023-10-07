package repository.itf;

import model.customer_bean.CustomerType;

import java.util.List;

public interface ICustomerTypeRepository {
    CustomerType findById(int id);
    List<CustomerType> findAll();
}
