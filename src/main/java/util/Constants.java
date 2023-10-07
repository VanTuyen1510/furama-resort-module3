package util;

import java.util.Date;

public class Constants {
    public static final String FIND_ALL_CUSTOMER_TYPE = "select * from customer_type";
    public static final String FIND_CUSTOMER_TYPE_BY_ID = "select * from customer_type where customer_type_id = ?";
    public static final String FIND_CUSTOMER_BY_ID = "select * from customer where customer_id = ?";
    public static final String FIND_ALL_CUSTOMER = "select * from customer";
    public static final String CREATE_CUSTOMER = "insert into customer values(?,?,?,?,?,?,?,?,?)";
    public static final String UPDATE_CUSTOMER = "update customer set customer_name = ? , customer_birthday = ? , customer_gender = ? , customer_id_card = ? , customer_phone = ? , customer_email = ? , customer_address = ? , customer_type_id = ? where customer_id = ?";
    //    int id, String name, Date birthday, int gender, String idCard, String phone, String email, String address, CustomerType customerType
    public static final String DELETE_CUSTOMER_BY_ID = "delete from customer where customer_id = ?";
    public static final String SEARCH_BY_NAME = "select * from customer where customer_name like ? " ;
    public static final String FIND_ALL_CUSTOMER_PAGING = "select * from customer LIMIT ? OFFSET ?";
    public static final String COUNT_ALL_CUSTOMER = "select count(1) from customer";


}
