package com.example.library.DTO;

import lombok.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddBookDTO {

    @NotNull
    String title;

    @NotNull
    String author;

    String description;
}
