package com.greenart.flo_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "genre_info")
@Builder public class GenreEntity {
    @Id @GeneratedValue
    (strategy = GenerationType.IDENTITY)
    @Column(name = "genre_seq") 
    private Long genreSeq;
    @Column(name = "genre_name") 
    private String genreName;
}
