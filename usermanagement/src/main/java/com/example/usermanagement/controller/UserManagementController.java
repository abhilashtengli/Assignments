package com.example.usermanagement.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.usermanagement.model.UserManagementModel;
import com.example.usermanagement.service.UserManagementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("User/Management")
public class UserManagementController {

    @Autowired
    UserManagementService service;

    @PostMapping("addUser")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserManagementModel user) {
        user.setDate(LocalDate.now());
        user.setTime(LocalTime.now());
        UserManagementModel registeredUser = service.addUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("GetAll")
    public List<UserManagementModel> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById/UserId/{userId}")
    public UserManagementModel getUserById(@PathVariable int userId) {
        return service.getUserById(userId);
    }

    @PutMapping("/update/UserId/{userId}")
    public void updateById(@PathVariable Integer userId, @RequestBody UserManagementModel newModel) {
        service.updateUser(userId, newModel);
    }
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
    //     Map<String, String> errors = new HashMap<>();
    //     ex.getBindingResult().getAllErrors().forEach((error) -> {
    //         String fieldName = ((FieldError) error).getField();
    //         String errorMessage = error.getDefaultMessage();
    //         errors.put(fieldName, errorMessage);
    //     });
    //     return errors;
    // }

    @DeleteMapping("/delete/{id}")
   public void deleteuser(@PathVariable int id) {
      service.deleteuser(id);
   }

}
