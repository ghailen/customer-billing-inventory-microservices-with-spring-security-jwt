package org.sid.billingservice.controller;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductItemRestClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@RestController
public class BillingRestController {

    private final  BillRepository billRepository;
    private final ProductItemRestClient productItemRestClient;
    private final ProductItemRepository productItemRepository;
    private  final CustomerRestClient customerRestClient;

    public BillingRestController(BillRepository billRepository, ProductItemRestClient productItemRestClient, ProductItemRepository productItemRepository, CustomerRestClient customerRestClient) {
        this.billRepository = billRepository;
        this.productItemRestClient = productItemRestClient;
        this.productItemRepository = productItemRepository;
        this.customerRestClient = customerRestClient;
    }


    @PostMapping(path="/addBilling")
    public void addBill(@RequestHeader(value = "Authorization", required = true) String authorizationHeader){

        Customer customer = customerRestClient.getCustomerById(authorizationHeader,1L);
        Bill bill1 =billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));

        PagedModel<Product> productPagedModel = productItemRestClient.pageProducts(authorizationHeader);
        productPagedModel.forEach(p->{
            ProductItem productItem = new ProductItem();
            productItem.setPrice(p.getPrice());
            productItem.setQuantity(1+new Random().nextInt(100));
            productItem.setBill(bill1);
            productItem.setProductId(p.getId());
            productItemRepository.save(productItem);
        });
    }

    @GetMapping(path="/fullBill/{id}")
    public Bill getBill(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@PathVariable(name="id")  Long id){
        Bill bill = billRepository.findById(id).get();
       Customer customer = customerRestClient.getCustomerById(authorizationHeader,bill.getCustomerId());
       bill.setCustomer(customer);
       bill.getProductItems().forEach(pi ->{
           Product product=productItemRestClient.getProductById(authorizationHeader,pi.getId());
           pi.setProduct(product);
       });
        return bill;
    }
}
