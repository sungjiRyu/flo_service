package com.greenart.flo_service.vo;

import java.util.List;

import com.greenart.flo_service.entity.ArtistGroupInfoEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistGroupResponseVO {
    private List<ArtistGroupInfoEntity> list;

    @Schema(description = "총 아티스트 그룹 수", example = "121")
    private Long total;

    @Schema(description = "총 페이지 수", example = "13")
    private Integer totalPAge;

    @Schema(description = "조회한 페이지(1페이지 -> 0)", example = "121")
    private Integer currentPage;
}
