package com.covid.api.admin;


import com.covid.model.CommentDTO;
import com.covid.model.query.CommonQuery;
import com.covid.processor.CommentProcessor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/comments")
public class CommentAdminApi {

    CommentProcessor processor;

    public CommentAdminApi(CommentProcessor processor) {
        this.processor = processor;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        processor.delete(id);
    }

    @GetMapping("/{id}")
    public Page<CommentDTO> get(CommonQuery model, @PathVariable Long id) {
        return processor.get(model, id);
    }

}
