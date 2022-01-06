package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Users;
import com.stripe.model.Order;

public interface PaymentService {

    public String createCustomer(Users user);

    public void chargeCreditCard(Order order);

}
