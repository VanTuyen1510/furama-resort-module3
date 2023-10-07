package controller;

import model.customer_bean.Customer;
import model.customer_bean.CustomerType;
import service.impl.CustomerServiceImpl;
import service.impl.CustomerTypeServiceImpl;
import service.itf.ICustomerService;
import service.itf.ICustomerTypeService;
import validate.Validate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerController extends HttpServlet {
    private ICustomerService customerService = new CustomerServiceImpl();
    private ICustomerTypeService customerTypeService = new CustomerTypeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null ){
            action = "list";
        }
        switch (action){
            case "list":
                viewList(request,response);
                break;
            case "create":
                viewCreate(request,response);
                break;
            case "delete":
                viewDelete(request,response);
                break;
            case "edit":
                viewEdit(request,response);
                break;
            default:
        }
    }

    private void viewEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.findByIdCustomer(id);
        request.setAttribute("customer",customer);
        request.setAttribute("customerTypes",customerTypeService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/customer/edit.jsp").forward(request,response);

    }

    private void viewDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerService.delete(customerService.findByIdCustomer(id));
        response.sendRedirect("/customer?action=list");
    }

    private void viewCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CustomerType> customerTypes = customerTypeService.findAll();
        request.setAttribute("customerTypes",customerTypes);
        request.getRequestDispatcher("/WEB-INF/views/customer/create.jsp").forward(request,response);

    }

    private void viewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Customer> customers = customerService.findAll();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/WEB-INF/views/customer/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/customer?action=list");
            return;
        }switch (action){
            case "create":
                doCreate(request,response);
                break;
            case "edit":
                doEdit(request,response);
                break;
            case "search":
                doSearch(request,response);
                break;
        }


    }

    private void doSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameCustomer = request.getParameter("customer_name");
        List<Customer> customers =  customerService.searchByName(nameCustomer);
        request.setAttribute("customers",customers);
        request.getRequestDispatcher("/WEB-INF/views/customer/search.jsp").forward(request,response);
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOfBirthday = null;
        try {
            dateOfBirthday = format.parse(birthday);
        }catch (ParseException e){
            throw new RuntimeException(e);
        }
        int gender = Integer.parseInt(request.getParameter("gender"));
        String idCard = request.getParameter("idCard");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int customerTypeId = Integer.parseInt(request.getParameter("customerTypeId"));
        CustomerType customerType = customerTypeService.findById(customerTypeId);
        Customer customer = new Customer(id,name,dateOfBirthday,gender,idCard,phone,email,address,customerType);
        customerService.edit(customer);
        response.sendRedirect("/customer?action=list");
    }

    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  int id, String name, Date birthday, int gender, String idCard, String phone, String email, String address, CustomerType customerType
        boolean idValid = true;
        String phoneError = null;
        String idCardError = null;
        boolean flag = false;

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter  ("name");
        String birthday = request.getParameter("birthday");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dateOfBirthday = null;
        try{
            dateOfBirthday = format.parse(birthday);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        int gender = Integer.parseInt(request.getParameter("gender"));

        String idCard = request.getParameter("idCard");
        if(Validate.regexIdCard(idCard)){
            idCardError = "IdCard không đúng định dạng phải có 12 số";
        }


        String phone = request.getParameter("phone");
        if(Validate.regexPhone(phone)){
            phoneError = "Phone không đúng định dạng (09xxxxxx)";
            flag = true;
        }

        String email = request.getParameter("email");
        String address = request.getParameter("address");


        int customerTypeId = Integer.parseInt(request.getParameter("customerTypeId"));
        CustomerType customerType = customerTypeService.findById(customerTypeId);
        Customer customer = new Customer(id,name,dateOfBirthday,gender,idCard,phone,email,address,customerType);

        if(flag){
            request.setAttribute("idCardError",idCardError);
            request.setAttribute("phoneError",phoneError);
            request.setAttribute("customer",customer);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer/create.jsp");
//            dispatcher.forward(request,response);
            viewCreate(request,response);
        }

        customerService.add(customer);
        response.sendRedirect("/customer?action=list");


    }
}
