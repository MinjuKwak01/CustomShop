package com.example.kakao.like;

import com.example.kakao.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class LikeResponse {

    @Getter
    @Setter
    public static class FindAllDTO{
        private int id;
        private String productName;
        private String description;
        private String image;
        private int price;

        public FindAllDTO(Like like) {
            this.id = like.getProduct().getId();
            this.productName = like.getProduct().getProductName();
            this.description = like.getProduct().getDescription();
            this.image = like.getProduct().getImage();
            this.price = like.getProduct().getPrice();
        }

    }


}
