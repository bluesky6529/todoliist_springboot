package com.todolist.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.todolist.app.entity.Todo;

import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private List<Todo> todoList = new ArrayList<>();

    public List<Todo> getAllTodo() {
        return this.todoList;
    }

    public Optional<Todo> getTodoById(int id) {
        return this.todoList.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public void addTodo(Todo todo) {
        if (this.todoList.size() == 0) {
            todo.setId(0);
        } else {
            todo.setId(this.todoList.get(this.todoList.size() - 1).getId() + 1);
        }
        this.todoList.add(todo);
    }

    public void updateTodoById(int id, Todo todo) {
        final int idx = this.todoList.indexOf(this.getTodoById(id).get());
        todo.setId(id);
        this.todoList.set(idx, todo);
    }

    public void deleteTodoById(int id) {
        final int idx = this.todoList.indexOf(this.getTodoById(id).get());
        this.todoList.remove(idx);
    }
}