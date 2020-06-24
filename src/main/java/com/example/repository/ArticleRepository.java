package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * articlesテーブルを操作するリポジトリ.
 * 
 * @author kohei.takasaki
 *
 */
@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private CommentRepository commentRepository;
	
	private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, i) -> {
		Article article = new Article();
		article.setId(rs.getInt("id"));
		article.setName(rs.getString("name"));
		article.setContent(rs.getString("content"));
		
		// TODO: 
		List<Comment> commentList = new ArrayList<>();
		article.setCommentList(commentList);
		return article;
	};
	
	/**
	 * 記事を全件取得する.
	 * 
	 * @return すべての記事のリスト
	 */
	public List<Article> findAll() {
		String sql = "DELETE\r\n" + 
				"FROM\r\n" + 
				"  comments\r\n" + 
				"WHERE\r\n" + 
				"  article_id = :articleId\r\n" + 
				";";
		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
		for(Article article: articleList) {
			int articleId = article.getId();
			List<Comment> commentList = commentRepository.findByArticleId(articleId);
			article.setCommentList(commentList);
		}
		return articleList;
	}
	
	/**
	 * 引数の記事をarticleテーブルにインサートする.
	 * 
	 * @param article インサートする記事情報
	 */
	public void insert(Article article) {
		String sql = "INSERT INTO\r\n" + 
				"  articles(name, content)\r\n" + 
				"VALUES\r\n" + 
				"  (name, :content)\r\n" + 
				";";
		SqlParameterSource param = new MapSqlParameterSource()
										.addValue("name", article.getName())
										.addValue("content", article.getContent());
		int insNum = template.update(sql, param);
		System.out.println(insNum + "件のデータを挿入しました");
	}
	
	/**
	 * 引数のIDと一致する記事を削除する.
	 * 
	 * @param id ID
	 */
	public void deleteById(int id) {
		String sql = "DELETE\r\n" + 
				"FROM\r\n" + 
				"  articles\r\n" + 
				"WHERE\r\n" + 
				"  id = :id\r\n" + 
				";";
		SqlParameterSource param = new MapSqlParameterSource()
										.addValue("id", id);
		int delNum = template.update(sql, param);
		System.out.println(delNum + "件のデータを削除しました");
	}
}
