package com.todolist.app.entity;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Todo {
    private Integer id;

    @NotBlank
    private String name;
}