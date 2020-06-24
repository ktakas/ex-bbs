package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;

/**
 * 掲示板全体の処理を行うコントローラ.
 * 
 * @author kohei.takasaki
 *
 */
@Controller
@RequestMapping("")
public class BBSController {
	
	@Autowired
	private ArticleRepository articleRepo;
	
	/**
	 * 記事投稿フォームのインスタンス化.
	 * 
	 * @return 記事投稿フォーム
	 */
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	/**
	 * コメント投稿フォームのインスタンス化.
	 * 
	 * @return コメント投稿フォーム
	 */
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	/**
	 * 記事一覧を表示する.
	 * 
	 * @return 記事一覧画面
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepo.findAll();
		model.addAttribute(articleList);
		return "index";
	}
	
}
