package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDto>createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping
public PostResponse getAllPosts(@RequestParam(value="pageNo",defaultValue = "0",required = false) int pageNo,
                                @RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize,
                                @RequestParam(value="sortBy", defaultValue = "id", required = false) String sortBy,
                                @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
    return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);

}
@GetMapping("/{id}")
public ResponseEntity<PostDto>createPost(@PathVariable(name = "id") long id){
    return ResponseEntity.ok(postService.getPostById(id));

}
@PutMapping("/{id}")
public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name = "id") long id){
    PostDto dto = postService.updatePost(postDto, id);
    return new ResponseEntity<>(dto,HttpStatus.OK);


}
@DeleteMapping("/{id}")
public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
    return new ResponseEntity<>("Post entity deleted!!",HttpStatus.OK);

}
}