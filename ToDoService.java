package com.example.demo.service;

import com.example.demo.model.ToDo;
import com.example.demo.model.User;
import com.example.demo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    // Semua todo
    public List<ToDo> getAllTodos() {
        return toDoRepository.findAll();
    }

    public void saveTodo(ToDo todo) {
        toDoRepository.save(todo);
    }

    public void deleteTodo(Long id) {
        toDoRepository.deleteById(id);
    }

    public Optional<ToDo> getTodoById(Long id) {
        return toDoRepository.findById(id);
    }

    public List<ToDo> getCompletedTodos() {
        return toDoRepository.findByCompleted(true);
    }

    public List<ToDo> getIncompleteTodos() {
        return toDoRepository.findByCompleted(false);
    }

    // ✅ Tambahan method untuk controller

    // Ambil semua todo berdasarkan user
    public List<ToDo> findAllByUser(User user) {
        return toDoRepository.findByUser(user);
    }

    // Todo yang selesai berdasarkan user
    public List<ToDo> findCompletedByUser(User user) {
        return toDoRepository.findByUserAndCompleted(user, true);
    }

    // Todo yang belum selesai berdasarkan user
    public List<ToDo> findIncompleteByUser(User user) {
        return toDoRepository.findByUserAndCompleted(user, false);
    }

    // Buat todo baru untuk user
    public void createTodo(String task, User user) {
        ToDo todo = new ToDo();
        todo.setTask(task);
        todo.setCompleted(false);
        todo.setUser(user);
        toDoRepository.save(todo);
    }

    // Cari todo berdasarkan ID dan username
    public ToDo findByIdAndUser(Long id, String username) {
        Optional<ToDo> optionalTodo = toDoRepository.findById(id);
        return optionalTodo.filter(todo -> todo.getUser().getUsername().equals(username)).orElse(null);
    }

    // Toggle status selesai
    public void toggleCompleted(Long id, String username) {
        Optional<ToDo> optionalTodo = toDoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            ToDo todo = optionalTodo.get();
            if (todo.getUser().getUsername().equals(username)) {
                todo.setCompleted(!todo.isCompleted());
                toDoRepository.save(todo);
            }
        }
    }

    // Hapus todo jika milik user
    public void deleteByIdAndUser(Long id, String username) {
        Optional<ToDo> optionalTodo = toDoRepository.findById(id);
        if (optionalTodo.isPresent()) {
            ToDo todo = optionalTodo.get();
            if (todo.getUser().getUsername().equals(username)) {
                toDoRepository.delete(todo);
            }
        }
    }
    public void updateTask(Long id, String newTask, String username) {
    ToDo todo = findByIdAndUser(id, username);
    todo.setTask(newTask);
    toDoRepository.save(todo);
}

}
