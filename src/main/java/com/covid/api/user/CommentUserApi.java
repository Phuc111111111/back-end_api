package com.covid.api.user;


import com.covid.model.CommentDTO;
import com.covid.model.query.CommonQuery;
import com.covid.model.request.CommentRequestDTO;
import com.covid.processor.CommentProcessor;
import com.covid.security.service.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/user/comments")
public class CommentUserApi {

    CommentProcessor processor;

    public CommentUserApi(CommentProcessor processor) {
        this.processor = processor;
    }

    @GetMapping("/{id}")
    public Page<CommentDTO> get(CommonQuery model, @PathVariable Long id) {
        return processor.get(model, id);
    }

    @PostMapping
    public void add(@Valid @RequestBody CommentRequestDTO commentDTO) {
         processor.add(commentDTO);
    }
}
