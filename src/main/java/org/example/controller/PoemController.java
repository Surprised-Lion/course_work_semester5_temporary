package org.example.controller;

import org.example.dto.PoemDto;
import org.example.entities.*;
import org.example.service.AuthorService;
import org.example.service.PoemService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poem")
public class PoemController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PoemService poemService;

    @GetMapping("/all")
    public ResponseEntity<List<Poem>> getAllPoems() {
        return ResponseEntity.ok(poemService.getAll());
    }

    @GetMapping("/{poemId}")
    public ResponseEntity<Poem> getPoem(@PathVariable Long poemId) {
        return ResponseEntity.ok(poemService.getById(poemId));
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN_ACTIONS')")
    public ResponseEntity<Poem> createPoem(@RequestBody PoemDto poemDto) {
        Author author = authorService.getById(poemDto.getAuthorId());
        Poem poem = new Poem(poemDto.getText(), poemDto.getPublishDate(), author);
        return ResponseEntity.ok(poemService.create(poem));
    }

    @GetMapping("/{poemId}/author")
    public ResponseEntity<Author> getPoemAuthor(@PathVariable Long poemId) {
        Poem poem = poemService.getById(poemId);
        return ResponseEntity.ok(poem.getAuthor());
    }

    @PostMapping("/{poemId}/like")
    @PreAuthorize("hasAuthority('WRITING')")
    public ResponseEntity<Like> likePoem(@PathVariable Long poemId) {
        User user = (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok(poemService.likePoem(poemId, user));
    }

    @DeleteMapping("/{poemId}/like")
    @PreAuthorize("hasAuthority('WRITING')")
    public ResponseEntity<String> deleteLike(@PathVariable Long poemId) {
        User user = (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        poemService.deletePoemLike(poemId, user);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{poemId}")
    @PreAuthorize("hasAuthority('ADMIN_ACTIONS')")
    public ResponseEntity<String> deletePoem(@PathVariable Long poemId) {
        poemService.deleteById(poemId);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/{poemId}/comment")
    public ResponseEntity<List<Comment>> getCommnets(@PathVariable Long poemId) {
        return ResponseEntity.ok(poemService.getAllComments(poemId));
    }

    @PostMapping("/{poemId}/comment")
    @PreAuthorize("hasAuthority('WRITING')")
    public ResponseEntity<Comment> commentPoem(@PathVariable Long poemId, @RequestBody String strComment) {
        User user = (User) userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Poem poem = poemService.getById(poemId);
        Comment comment = new Comment(strComment, user, poem);
        return ResponseEntity.ok(poemService.createComment(comment));
    }

    @DeleteMapping("/{poemId}/comment/{commentId}")
    @PreAuthorize("hasAuthority('ADMIN_ACTIONS')")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        poemService.deleteCommentById(commentId);
        return ResponseEntity.ok("Success");
    }
}
