package chpt.cleanhome.controller;

import chpt.cleanhome.entity.Product;
import chpt.cleanhome.entity.ProductDto;
import chpt.cleanhome.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping({"","/"})
    public String showProductsList(Model model) {
        List<Product> products = productsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "products/productsList";
    }

    @GetMapping("/create")
    public String showCreateProductForm(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/createProduct";
    }
}
