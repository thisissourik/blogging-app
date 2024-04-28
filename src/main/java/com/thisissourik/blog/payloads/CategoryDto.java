package com.thisissourik.blog.payloads;

import com.thisissourik.blog.entitites.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int categoryId;
    private String categoryTitle;
    private String categoryDescription;
}
