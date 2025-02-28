package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

//@Data //get/set, toString코드 자동 생성
@NoArgsConstructor //기본 생성자 자동 생성
@AllArgsConstructor //모든 멤버변수 생성자 자동 생성
//@Builder
@Entity
public class Product {
    @Id
    private long productId;

    @NotNull(message = "Name cannot be null") // 필수 값
    private String name;

    @Size(max = 500, message = "Content must be less than 500 characters") // 길이 제한
    private String content;

    @NonNull
    @NotBlank(message = "Company cannot be blank") // 비어 있을 수 없음
    private String company;

    @Min(value = 1, message = "Price must be at least 1") // 최소값 설정
    private int price;
}
