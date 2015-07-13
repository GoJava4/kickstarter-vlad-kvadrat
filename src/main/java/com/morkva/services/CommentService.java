package com.morkva.services;

import com.morkva.model.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentService {


    @Qualifier("commentDao")
    @Autowired
    private CommentDao commentDao;
}
