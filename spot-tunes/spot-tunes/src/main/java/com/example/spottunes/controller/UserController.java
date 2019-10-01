package com.example.spottunes.controller;

import com.example.spottunes.config.JwtUtil;
import com.example.spottunes.model.JwtResponse;
import com.example.spottunes.model.User;
import com.example.spottunes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        return ResponseEntity.ok(new JwtResponse(userService.createUser(newUser)));
    }

    @DeleteMapping("/user/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {
        return userService.deleteById(userId);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!!";
    }


    @PutMapping("/user/{username}/add/{song_id}")
    public User addSong(@PathVariable String username, @PathVariable int song_id) {
        return userService.addSongs(username, song_id);
    }

    @DeleteMapping("/user/{username}/delete/{song_id}")
    public User deleteSongFromPlaylist(@PathVariable String username, @PathVariable int song_id) {
        return userService.deleteSongFromPlaylist(username, song_id);
    }

}