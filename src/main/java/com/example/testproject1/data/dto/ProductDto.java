package com.example.testproject1.data.dto;

import com.example.testproject1.data.entity.ProductEntity;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {

    @NotNull  // 검증(validation)
    private String productId;

    @NotNull
    private String productName;

    @NotNull
    @Min(value = 500)
    @Max(value = 3000000)
    private int productPrice;

    @NotNull
    @Min(value = 0)
    @Max(value = 9999)
    private int productStock;

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
    }
}
