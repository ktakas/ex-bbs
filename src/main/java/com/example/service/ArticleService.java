package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

/**
 * 記事情報を操作するサービス.
 * 
 * @author kohei.takasaki
 *
 */
@Service
@Transactional
public class ArticleService {

	@Autowired
	private ArticleRepository repository;
	
	@Autowired
	private CommentService commentServ;
	
	/**
	 * 全件検索を行う.
	 * 
	 * @return すべての記事情報のリスト
	 */
	public List<Article> findAll() {
		return repository.findAll();
	}
	
	/**
	 * 引数の記事をインサートする.
	 * 
	 * @param article 記事情報
	 */
	public void insert(Article article) {
		repository.insert(article);
	}
	
	/**
	 * 引数のIDと一致する記事情報を削除する.
	 * 
	 * @param id 記事のID
	 */
	public void deleteById(int id) {
		commentServ.deleteByArticleId(id);
		repository.deleteById(id);
	}
}
