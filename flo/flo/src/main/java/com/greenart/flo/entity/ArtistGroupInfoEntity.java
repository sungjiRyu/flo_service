package com.greenart.flo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "artist_group_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistGroupInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "아티스트 그룹 번호", example = "1")
    @Column(name = "agi_seq") private Long agiSeq;
    @Schema(description = "아티스트 그룹 이름", example = "뉴진스")
    @Column(name = "agi_name") private String agiName;
    @Schema(description = "데뷔 연도", example = "2022")
    @Column(name = "agi_debut_year") private Integer agiDebutYear;
    @Schema(description = "아티스트 그룹 대표이미지", example = "newjeans.png")
    @Column(name = "agi_img") private String agiImg;
    @Schema(description = "소속사 번호", example = "1")
    @ManyToOne @JoinColumn(name = "agi_pci_seq") private CompanyEntity company;
    // @Column(name = "agi_pci_seq") private Long agiPciSeq;
}
