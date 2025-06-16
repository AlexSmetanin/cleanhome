package chpt.cleanhome.controller;

import chpt.cleanhome.entity.Product;
import chpt.cleanhome.entity.ProductDto;
import chpt.cleanhome.repository.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @GetMapping({"/products"})
    public String showProductsList(Model model) {
        List<Product> products = productsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "products/products";
    }

    @GetMapping("/products/create")
    public String showCreateProductForm(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/createProduct";
    }

    @PostMapping("/products/create")
    public String createProduct(
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result) {
        if (productDto.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDto", "imageFile", "Файл зображення обов'язковий"));
        }

        if (result.hasErrors()) {
            return "products/createProduct";
        }

        // Зберігаємо файл картинки
        MultipartFile image = productDto.getImageFile();
        Date createAt = new Date();
        String storageFileName = createAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir= "public/images";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            System.out.println("Exception:" + ex.getMessage());
        }

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        product.setImageFileName(storageFileName);

        productsRepository.save(product);

        return "redirect:/products";
    }

    @GetMapping("/products/edit")
    public String showEditProductForm(
            Model model,
            @RequestParam int id) {
        try {
            Product product = productsRepository.findById((long) id).get();
            model.addAttribute("product", product);

            ProductDto productDto = new ProductDto();
            productDto.setProductName(product.getProductName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setQuantity(product.getQuantity());
            productDto.setDescription(product.getDescription());

            model.addAttribute("productDto", productDto);
        }
        catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
            return "products";
        }

        return "products/editProduct";
    }

    @PostMapping("/products/edit")
    public String updateProduct(
            Model model,
            @RequestParam int id,
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
    ) {

        try {
            Product product = productsRepository.findById((long) id).get();
            model.addAttribute("product", product);

            if (result.hasErrors()) {
                return "products/editProduct";
            }

            if (!productDto.getImageFile().isEmpty()) {
                // видалити старе зображення
                String uploadDir= "public/images";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception ex) {
                    System.out.println("Exception:" + ex.getMessage());
                }

                // зберегти новий файл зображення
                MultipartFile image = productDto.getImageFile();
                Date updateAt = new Date();
                String storageFileName = updateAt.getTime() + "_" + image.getOriginalFilename();

                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir+storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                product.setImageFileName(storageFileName);
            }
            product.setProductName(productDto.getProductName());
            product.setBrand(productDto.getBrand());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setQuantity(productDto.getQuantity());
            product.setDescription(productDto.getDescription());
            productsRepository.save(product);
        }
        catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
        }

        return "redirect:/products";
    }

    @GetMapping("/products/delete")
    public String deleteProduct(
            @RequestParam int id
    ) {
        try {
            Product product = productsRepository.findById((long) id).get();
            // вилучення зображення товару
            Path imagePath = Paths.get("public/images/" + product.getImageFileName());
            try {
                Files.delete(imagePath);
            } catch (Exception ex) {
                System.out.println("Exception:" + ex.getMessage());
            }

            // вилучення товару
            productsRepository.delete(product);

        } catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/products/details")
    public String showProductDetailForm(
            Model model,
            @RequestParam int id) {
        try {
            Product product = productsRepository.findById((long) id).get();
            model.addAttribute("product", product);
        }
        catch (Exception ex) {
            System.out.println("Exception:" + ex.getMessage());
            return "main";
        }

        return "products/productDetails";
    }

}
