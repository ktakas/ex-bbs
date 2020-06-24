package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.service.ArticleService;

/**
 * 記事情報についてのコントローラ.
 * 
 * @author kohei.takasaki
 *
 */
@Controller
@RequestMapping("article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleServ;
	
	/**
	 * フォーム入力内容をインサートする.
	 * 
	 * @param form 記事情報のフォーム
	 * @return 記事一覧へのリダイレクト
	 */
	@RequestMapping("insert")
	public String insert(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		List<Comment> emptyCommentList = new ArrayList<>();
		article.setCommentList(emptyCommentList);
		
		articleServ.insert(article);
		System.out.println("OK");
		return "redirect:/";
	}
	
	/**
	 * 記事IDと一致する記事とコメントを削除する.
	 * 
	 * @param articleId 記事ID
	 * @return 記事一覧へのリダイレクト
	 */
	@RequestMapping("delete")
	public String delete(Integer articleId) {
		articleServ.deleteById(articleId);
		return "redirect:/";
	}
}
