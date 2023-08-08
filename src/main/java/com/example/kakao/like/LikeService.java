package com.example.kakao.like;

import com.example.kakao._core.errors.exception.Exception400;
import com.example.kakao._core.errors.exception.Exception401;
import com.example.kakao._core.errors.exception.Exception404;
import com.example.kakao.product.Product;
import com.example.kakao.product.ProductJPARepository;
import com.example.kakao.product.ProductResponse;
import com.example.kakao.user.User;
import com.example.kakao.user.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikeJPARepository likeJPARepository;
    private final UserJPARepository userJPARepository;
    private final ProductJPARepository productJPARepository;

    @Transactional
    public void likeAdd(LikeRequest.LikeAddDTO requestDTO, User sessionUser){
        int userId = requestDTO.getUserId();
        int productId = requestDTO.getProductId();

        User userPS = userJPARepository.findById(userId).orElseThrow(
                ()-> new Exception400("존재하지 않는 사용자입니다.")
        );

        if(sessionUser.getId() != requestDTO.getUserId()){
            throw new Exception401("인증되지 않은 사용자입니다.");
        }

        //이미 좋아요 누른 상품인데 또 눌렀을 경우
        Optional<Like> likeOP  = likeJPARepository.findByProductId(productId, userId);
        if(likeOP.isPresent()){
            throw new Exception400("이미 좋아요를 누른 상품입니다.");
        }

        //존재하지 않는 상품 id로 좋아요를 시도했을 때
        Product productPS = productJPARepository.findById(productId).orElseThrow(
                ()-> new Exception404("존재하지 않는 상품입니다")
        );

        Like like = Like.builder()
                .user(userPS)
                .product(productPS)
                .build();

        likeJPARepository.save(like);
    }

    @Transactional
    public List<LikeResponse.FindAllDTO> findAllLikes(User sessionUser, int id){
        if(sessionUser.getId()!= id){
            throw new Exception401("권한이 없습니다.");
        }

        //userId 가 2인 user의 likeList를 가져온다.
        List<Like> likeList = likeJPARepository.findProductsByUserId(id);

        List<LikeResponse.FindAllDTO> responseDTOs = likeList.stream()
                .map(like -> new LikeResponse.FindAllDTO(like))
                .collect(Collectors.toList());

        return responseDTOs;

    }
}
