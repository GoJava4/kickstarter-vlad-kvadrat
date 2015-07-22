package com.morkva.api.v1;

import com.morkva.entities.Category;
import com.morkva.entities.Comment;
import com.morkva.entities.Project;
import com.morkva.entities.User;
import com.morkva.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    public void updateCommentById(@PathVariable int id,
                                  @RequestParam String comment) {

        Comment com = commentService.getById(id);
        com.setComment(comment);
        commentService.update(com);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createComment(@RequestParam String comment,
                              @RequestBody User user,
                              @RequestBody Project project) {

        Comment com = new Comment();
        com.setComment(comment);
        com.setUser(user);
        com.setProject(project);
        commentService.create(com);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void deleteCommentById(@PathVariable int id) {

        Comment comment = commentService.getById(id);
        commentService.delete(comment);
    }

    @RequestMapping(value = "/getCommentsOfUser/", method = RequestMethod.GET)
    public List<Comment> getCommentsOfUser(@RequestBody User user) {
        return commentService.getCommentsOf(user);
    }

    @RequestMapping(value = "/getCommentsOfProject/", method = RequestMethod.GET)
    public List<Comment> getCommentsOfProject(@RequestBody Project project) {
        return commentService.getCommentsOf(project);
    }
}
