package com.example.kakao.like;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class LikeRequest {

    @Getter
    @Setter
    public static class LikeAddDTO{
        @NotNull
        private int userId;
        @NotNull
        private int productId;
    }
}
