package com.greenart.flo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistGroupInfoInsertVO {
    private String name;
    private Integer debutYear;
    private Long company;
}
