package chpt.cleanhome.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
    @NotEmpty(message = "Назва обов'язкове поле")
    private String productName;

    @NotEmpty(message = "Бренд обов'язкове поле")
    private String brand;

    @NotEmpty(message = "Категорія обов'язкове поле")
    private String category;

    @Min(0)
    private Double price;

    @Min(0)
    private Double quantity;

    @Size(min = 10, message = "Опис має бути більше 10 символів")
    @Size(max = 2000, message = "Опис не може перевищувати 2000 символів")
    private String description;

    private MultipartFile imageFile;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
