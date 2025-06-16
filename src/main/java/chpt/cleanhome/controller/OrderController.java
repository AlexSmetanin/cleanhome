package chpt.cleanhome.controller;

import chpt.cleanhome.entity.Order;
import chpt.cleanhome.entity.Product;
import chpt.cleanhome.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping({"/orders"})
    public String showOrdersList(Model model) {
        List<Order> orders = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("orders", orders);
        return "orders/orders";
    }
}
