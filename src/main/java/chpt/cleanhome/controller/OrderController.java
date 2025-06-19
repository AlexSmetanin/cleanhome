package chpt.cleanhome.controller;

import chpt.cleanhome.entity.Order;
import chpt.cleanhome.entity.Product;
import chpt.cleanhome.repository.OrderRepository;
import chpt.cleanhome.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    private List<Product> productsList;

    @Autowired
    private OrderRepository orderRepository;
    private ProductsRepository productsRepository;

    @GetMapping({"/orders"})
    public String showOrdersList(Model model) {
        List<Order> orders = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("orders", orders);
        return "orders/orders";
    }

    @PostMapping({"/orders/addToCart"})
    public String addProductToCart(
            Model model,
            @RequestParam int id) {
            try {
                Product product = productsRepository.findById((long) id).get();
                productsList.add(product);
            }
            catch (Exception ex) {
                System.out.println("Exception:" + ex.getMessage());
            }
        return "main";
    }

}
