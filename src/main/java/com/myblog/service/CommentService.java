package com.myblog.service;

import com.myblog.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
}
