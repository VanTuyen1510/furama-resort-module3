package service.itf;

import model.customer_bean.CustomerType;

import java.util.List;

public interface ICustomerTypeService {
    CustomerType findById(int id);
    List<CustomerType> findAll();
}
