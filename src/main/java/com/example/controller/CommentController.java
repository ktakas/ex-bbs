package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Comment;
import com.example.form.CommentForm;
import com.example.service.CommentService;

/**
 * コメント情報についてのコントローラ.
 * 
 * @author kohei.takasaki
 *
 */
@Controller
@RequestMapping("comment")
public class CommentController {
	
	@Autowired
	private CommentService commentServ;
	
	@RequestMapping("insert")
	public String insert(Integer articleId, CommentForm form) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(articleId);
		commentServ.insert(comment);
		return "redirect:/";
	}

}
