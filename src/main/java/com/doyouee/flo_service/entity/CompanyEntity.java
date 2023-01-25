// package com.doyouee.flo_service.entity;

// import com.mysql.cj.x.protobuf.MysqlxCrud.Column;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import jakarta.persistence.Column;


// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// @Table(name = "genre_info")
// public class GenreEntity {
//     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @column(name = "genre_seq")     private Long genreSeq;
//     @column(name = "genre_name")    private String genreName;
// }
package com.doyouee.flo_service.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@DynamicInsert
@Table(name = "pub_company_info")
public class CompanyEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pub_seq")         private Long pubSeq;
    @Column(name = "pub_name")          private String pubName;
}
