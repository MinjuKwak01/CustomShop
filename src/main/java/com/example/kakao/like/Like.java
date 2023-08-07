package com.example.kakao.like;

import com.example.kakao.product.Product;
import com.example.kakao.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="like_tb", indexes = {
        @Index(name = "like_user_id_idx", columnList = "user_id"),
        @Index(name = "like_product_id_idx", columnList = "product_id")
})
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne
    private Product product;

    @Builder
    public Like(int id, User user, Product product){
        this.id = id;
        this.user = user;
        this.product = product;
    }
}
