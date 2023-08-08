package com.example.kakao.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeJPARepository extends JpaRepository<Like, Integer> {

    @Query("select l from Like l where l.product.id = :productId and l.user.id = :userId")
    Optional<Like> findByProductId(@Param("productId") int productId, @Param("userId") int userId);

    @Query("select l from Like l join fetch l.product p where l.user.id = :userId")
    List<Like> findProductsByUserId(@Param("userId") int userId);
}
