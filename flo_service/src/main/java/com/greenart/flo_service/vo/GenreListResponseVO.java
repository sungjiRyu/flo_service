package com.greenart.flo_service.vo;

import java.util.List;

import com.greenart.flo_service.entity.GenreEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenreListResponseVO {
    @Schema(description = "장르 리스트")
    private List<GenreEntity> list;
    @Schema(description = "총 장르 수", example = "121")
    private Long total;
    @Schema(description = "총 페이지 수" , example = "13")
    private Integer totalPage;
    @Schema(description = "조회한 페이지 (1 페이지 -> 0 / 2 페이지 -> 1)", example = "0")
    private Integer currentPage;
}
