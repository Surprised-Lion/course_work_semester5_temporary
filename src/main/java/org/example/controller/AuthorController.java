package org.example.controller;

import org.example.entities.Author;
import org.example.entities.Like;
import org.example.entities.Poem;
import org.example.entities.User;
import org.example.service.AuthorService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAll());
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(authorService.getById(authorId));
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN_ACTIONS')")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return ResponseEntity.ok(authorService.create(author));
    }

    @GetMapping("/{authorId}/poems")
    public ResponseEntity<List<Poem>> getPoems(@PathVariable Long authorId) {
        return ResponseEntity.ok(authorService.getPoemsByAuthorId(authorId));
    }

    @PostMapping("/{authorId}/like")
    @PreAuthorize("hasAuthority('WRITING')")
    public ResponseEntity<Like> likeAuthor(@PathVariable Long authorId) {
        User user = (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok(authorService.likeAuthor(authorId, user));
    }

    @DeleteMapping("/{authorId}/like")
    @PreAuthorize("hasAuthority('WRITING')")
    public ResponseEntity<String> deleteLike(@PathVariable Long authorId) {
        User user = (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        authorService.deleteAuthorLike(authorId, user);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{authorId}")
    @PreAuthorize("hasAuthority('ADMIN_ACTIONS')")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteById(authorId);
        return ResponseEntity.ok("Success");
    }
}

