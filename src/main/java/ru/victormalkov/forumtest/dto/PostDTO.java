package ru.victormalkov.forumtest.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PostDTO {
    @NotNull
    @NotEmpty
    private String text;
}
