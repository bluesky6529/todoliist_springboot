package com.todolist.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.todolist.app.entity.Todo;
import com.todolist.app.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo() {
        return ResponseEntity.status(HttpStatus.OK).body(this.todoService.getAllTodo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable("id") final Integer id) {
        final Optional<Todo> todoOpt = this.todoService.getTodoById(id);
        if (!todoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(todoOpt.get());
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody @Valid final Todo todo) {
        this.todoService.addTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") final Integer id, @RequestBody @Valid final Todo todo) {
        final Optional<Todo> todoOpt = this.todoService.getTodoById(id);
        if (!todoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.todoService.updateTodoById(id, todo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable("id") final Integer id) {
        final Optional<Todo> todoOpt = this.todoService.getTodoById(id);
        if (!todoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        this.todoService.deleteTodoById(id);
        return ResponseEntity.noContent().build();
    }
}