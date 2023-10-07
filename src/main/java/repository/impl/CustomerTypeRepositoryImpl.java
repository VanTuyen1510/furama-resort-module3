package repository.impl;

import model.customer_bean.Customer;
import model.customer_bean.CustomerType;
import repository.itf.ICustomerTypeRepository;
import util.Constants;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerTypeRepositoryImpl implements ICustomerTypeRepository {
    @Override
    public CustomerType findById(int id) {
        CustomerType customerType = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(Constants.FIND_CUSTOMER_TYPE_BY_ID))
        {
                statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("customer_type_name");
                customerType = new CustomerType(id,name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customerType;
    }

    @Override
    public List<CustomerType> findAll() {
        List<CustomerType> customerTypes = new ArrayList<>();
        try(Connection connection = DatabaseConnection.getConnection();
           Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(Constants.FIND_ALL_CUSTOMER_TYPE);
            CustomerType customerType ;
            while (resultSet.next()){
                int id = resultSet.getInt("customer_type_id");
                String name = resultSet.getString("customer_type_name");
                customerType = new CustomerType(id,name);
                customerTypes.add(customerType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customerTypes;
    }
}
