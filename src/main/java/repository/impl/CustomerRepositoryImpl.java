package repository.impl;

import model.customer_bean.Customer;
import model.customer_bean.CustomerType;
import repository.itf.ICustomerRepository;
import repository.itf.ICustomerTypeRepository;
import util.Constants;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements ICustomerRepository {
    private ICustomerTypeRepository customerTypeRepository = new CustomerTypeRepositoryImpl();
//    int id, String name, java.util.Date birthday, int gender, String idCard, String phone, String email, String address, CustomerType customerType
    @Override
    public void add(Customer customer) {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(Constants.CREATE_CUSTOMER)){
            statement.setInt(1,customer.getId());
            statement.setString(2,customer.getName());
            statement.setDate(3,new Date(customer.getBirthday().getTime()));
            statement.setInt(4,customer.getGender());
            statement.setString(5,customer.getIdCard());
            statement.setString(6,customer.getPhone());
            statement.setString(7,customer.getEmail());
            statement.setString(8,customer.getAddress());
            statement.setInt(9,customer.getCustomerType().getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void edit(Customer customer) {
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(Constants.UPDATE_CUSTOMER)){
            statement.setInt(9,customer.getId());
            statement.setString(1,customer.getName());
            statement.setDate(2,new Date(customer.getBirthday().getTime()));
            statement.setInt(3,customer.getGender());
            statement.setString(4,customer.getIdCard());
            statement.setString(5,customer.getPhone());
            statement.setString(6,customer.getEmail());
            statement.setString(7,customer.getAddress());
            statement.setInt(8,customer.getCustomerType().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Customer customer) {
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(Constants.DELETE_CUSTOMER_BY_ID);
        ){
            statement.setInt(1,customer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //int id, String name, Date birthday, String idCard, String phone, String email, String address, CustomerType customerType
    @Override
    public Customer findByIdCustomer(int id) {
        Customer customer = null;
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(Constants.FIND_CUSTOMER_BY_ID)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("customer_name");
                Date birthday = new Date(resultSet.getDate("customer_birthday").getTime());
                int gender = resultSet.getInt("customer_gender");
                String idCard = resultSet.getString("customer_id_card");
                String phone = resultSet.getString("customer_phone");
                String email = resultSet.getString("customer_email");
                String address = resultSet.getString("customer_address");
                int customerTypeId = resultSet.getInt("customer_type_id");
                CustomerType customerType = customerTypeRepository.findById(customerTypeId);
                customer = new Customer(id,name,birthday,gender,idCard,phone,email,address,customerType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> searchByName(String name) {
        List<Customer> customers = new ArrayList<>();
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(Constants.SEARCH_BY_NAME)) {
            statement.setString(1,"%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            //int id, String name, Date birthday, int gender, String idCard, String phone, String email, String address, CustomerType customerType
            while (resultSet.next()){
                int id = resultSet.getInt("customer_id");
                String name2 = resultSet.getString("customer_name");
                Date birthday = new Date(resultSet.getDate("customer_birthday").getTime());
                int gender = resultSet.getInt("customer_gender");
                String idCard = resultSet.getString("customer_id_card");
                String phone = resultSet.getString("customer_phone");
                String email = resultSet.getString("customer_email");
                String address = resultSet.getString("customer_address");
                int customerType = resultSet.getInt("customer_type_id");
                CustomerType customer_type_id = customerTypeRepository.findById(customerType);

                Customer customer = new Customer(id,name2,birthday,gender,idCard,phone,email,address,customer_type_id);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> findAll() {
      List<Customer> customers = new ArrayList<>();
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(Constants.FIND_ALL_CUSTOMER)){

            ResultSet resultSet = statement.executeQuery();
            Customer customer ;
            while (resultSet.next()){
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("customer_name");
                Date birthday = new Date(resultSet.getDate("customer_birthday").getTime());
                int gender = resultSet.getInt("customer_gender");
                String idCard = resultSet.getString("customer_id_card");
                String phone = resultSet.getString("customer_phone");
                String email = resultSet.getString("customer_email");
                String address = resultSet.getString("customer_address");
                int customerTypeId = resultSet.getInt("customer_type_id");
                CustomerType customerType = customerTypeRepository.findById(customerTypeId);
                customer = new Customer(id,name,birthday,gender,idCard,phone,email,address,customerType);
                customers.add(customer);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> findAllWithPaging(int page, int record) {
        List<Customer> customers = new ArrayList<>();
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(Constants.FIND_ALL_CUSTOMER_PAGING)) {
            statement.setInt(1,record);
            statement.setInt(2,(page-1)*5);
            ResultSet resultSet = statement.executeQuery();
            Customer customer;
            //int id, String name, Date birthday, int gender, String idCard, String phone, String email, String address, CustomerType customerType
            while (resultSet.next()){
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("customer_name");
                Date birthday = new Date(resultSet.getDate("customer_birthday").getTime());
                int gender = resultSet.getInt("customer_gender");
                String idCard = resultSet.getString("customer_idCard");
                String phone = resultSet.getString("customer_phone");
                String email = resultSet.getString("customer_email");
                String address = resultSet.getString("customer_address");
                int customerTypeId = resultSet.getInt("customer_type_id");
                CustomerType customerType = customerTypeRepository.findById(customerTypeId);
                customer = new Customer(id,name,birthday,gender,idCard,phone,email,address,customerType);
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
