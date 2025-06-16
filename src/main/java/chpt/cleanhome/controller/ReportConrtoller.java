package chpt.cleanhome.controller;

import chpt.cleanhome.entity.Order;
import chpt.cleanhome.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class ReportConrtoller {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/report")
    public String getReportParams() {
        return "report" ;
    }

    // Обробка дат звіту
    @PostMapping("/report")
    public String processReportParams(@RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate, Model model) throws  IOException {
        List<Order> orders = orderRepository.findAll();
        //createReport(startdate, enddate, orders);
        return "report";
    }
}
