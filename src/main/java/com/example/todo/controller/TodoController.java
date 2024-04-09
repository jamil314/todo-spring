package com.example.todo.controller;

import com.example.todo.model.TodoItem;
import com.example.todo.repo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping(value = "/todos")
public class TodoController {
    @Autowired
    private TodoRepo todoRepo;

    @GetMapping
    public String findAll(Model model) {
        System.out.println("Catching GET");
        List<TodoItem> todoList = todoRepo.findAll();
        model.addAttribute("todoList", todoList);
        return "todos";
    }

    @GetMapping("/create")
    public String newTodo() {
            return "createTodo";
    }

    @GetMapping("/edit/{id}")
    public String findById(@PathVariable("id") long id, Model model) {
        System.out.println("editing " + id);
        TodoItem todoItem = todoRepo.findById(id).orElse(null);
        if (todoItem != null) {
            model.addAttribute("todoItem", todoItem);
            return "editTodoItem";
        } else {
            return "redirect:/todos";
        }
    }

    @PostMapping
    public String create( @ModelAttribute TodoItem todoItem, Model model) {
        System.out.println("Adding: " + todoItem.toString());
        todoRepo.save(todoItem);
        model.addAttribute("todoItem", todoItem);
        return "redirect:/todos";    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute TodoItem updatedTodoItem) {
        TodoItem todoItem = todoRepo.findById(id).orElse(null);
        if (todoItem != null) {
            todoItem.setTitle(updatedTodoItem.getTitle());
            todoItem.setDescription(updatedTodoItem.getDescription());
            todoItem.setStarred(updatedTodoItem.isStarred());
            todoItem.setDone(updatedTodoItem.isDone());
            todoRepo.save(todoItem);
        }
        return "redirect:/todos";
    }

    @PostMapping("/{id}/toggle-starred")
    public String toggleStarred(@PathVariable("id") long id) {
        TodoItem todoItem = todoRepo.findById(id).orElse(null);
        if (todoItem != null) {
            todoItem.setStarred(!todoItem.isStarred());
            todoRepo.save(todoItem);
        }
        System.out.println("after toggling star:\n" + todoItem.toString());
        return "redirect:/todos";
    }

    @PostMapping("/{id}/toggle-done")
    public String toggleDone(@PathVariable("id") long id) {
        TodoItem todoItem = todoRepo.findById(id).orElse(null);
        if (todoItem != null) {
            todoItem.setDone(!todoItem.isDone());
            todoRepo.save(todoItem);
        }
        return "redirect:/todos";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, @ModelAttribute TodoItem todoItem) {
        System.out.println("New value: " + todoItem.toString());
        TodoItem oldItem = todoRepo.findById(id).orElse(null);
        if (oldItem != null) {
            oldItem.setTitle(todoItem.getTitle());
            oldItem.setDescription(todoItem.getDescription());
            oldItem.setStarred(todoItem.isStarred());
            oldItem.setDone(todoItem.isDone());
            todoRepo.save(oldItem);
        }
        return "redirect:/todos";
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        todoRepo.deleteById(id);
        return "redirect:/todos";
    }
}
