package com.example.kakao.like;

import com.example.kakao.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeJPARepository extends JpaRepository<Like, Integer> {

    @Query("select l from Like l where l.product.id = :productId")
    Optional<Like> findByProductId(@Param("productId") int productId);
}
