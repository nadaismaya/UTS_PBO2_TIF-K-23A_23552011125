package com.example.demo.controller;

import com.example.demo.model.ToDo;
import com.example.demo.model.User;
import com.example.demo.service.ToDoService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserService userService;

    // Menampilkan semua todo
    @GetMapping
    public String listTodos(@RequestParam(value = "filter", required = false, defaultValue = "all") String filter,
                            Authentication auth, Model model) {
        String username = auth.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ToDo> todos;
        switch (filter) {
            case "completed" -> todos = toDoService.findCompletedByUser(user);
            case "incomplete" -> todos = toDoService.findIncompleteByUser(user);
            default -> todos = toDoService.findAllByUser(user);
        }

        model.addAttribute("todos", todos);
        model.addAttribute("filter", filter);
        model.addAttribute("username", username);
        return "todo";
    }

    // Menambahkan todo baru
    @PostMapping("/create")
    public String createTodo(@RequestParam("task") String task, Authentication auth) {
        String username = auth.getName();
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        toDoService.createTodo(task, user);
        return "redirect:/todos";
    }

    // Menampilkan form edit
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model, Authentication auth) {
        ToDo todo = toDoService.findByIdAndUser(id, auth.getName());
        model.addAttribute("todo", todo);
        String username = auth.getName();
        model.addAttribute("username", username);
        return "edit_todo";
    }

    // Menyimpan perubahan setelah diedit
    @PostMapping("/edit/{id}")
    public String updateTodo(@PathVariable Long id,
                             @RequestParam("task") String task,
                             Authentication auth) {
        toDoService.updateTask(id, task, auth.getName());
        return "redirect:/todos";
    }

    // Menandai selesai/belum selesai
    @PostMapping("/update/{id}")
    public String updateStatus(@PathVariable Long id, Authentication auth) {
        toDoService.toggleCompleted(id, auth.getName());
        return "redirect:/todos";
    }

    // Menghapus todo
    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id, Authentication auth) {
        toDoService.deleteByIdAndUser(id, auth.getName());
        return "redirect:/todos";
    }
}

