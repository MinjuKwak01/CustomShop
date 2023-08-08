package com.example.kakao.like;

import com.example.kakao._core.security.CustomUserDetails;
import com.example.kakao._core.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeRestController {

    private final LikeService likeService;

    @PostMapping("/like/add")
    public ResponseEntity<?> likeAdd(@RequestBody @Valid LikeRequest.LikeAddDTO requestDTO, Errors errors, @AuthenticationPrincipal CustomUserDetails userDetails){
		likeService.likeAdd(requestDTO, userDetails.getUser());
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(null);
        return ResponseEntity.ok(apiResult);
    }

    @GetMapping("/like/{id}")
    public ResponseEntity<?> findAllLikes(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable int id){
        List<LikeResponse.FindAllDTO> responseDTO = likeService.findAllLikes(userDetails.getUser(),id);
        ApiUtils.ApiResult<?> apiResult = ApiUtils.success(responseDTO);
        return ResponseEntity.ok(apiResult);
    }
}
