package com.sbs.untact.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.untact.dto.Article;
import com.sbs.untact.util.ResultData;
import com.sbs.untact.util.Util;

@Controller
public class MapUsrArticleController {
	private List<Article> articles;
	private int articlesLastId;
	
	public MapUsrArticleController() {
		articles = new ArrayList<>();
		makeTestData();
	}
	
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public List<Article> doWrite() {
		return articles;
	}
	
	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		Article article = getArticleById(id);
		if (article == null) {
			return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
		}
		return new ResultData("S-1", article.getId() + "번 글 입니다.", "article", article);
	}
	
	
	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		boolean deleteArticleRs = deleteArticle(id);
		
		Map<String, Object> map = new HashMap<>();
		
		if(deleteArticleRs == false) {
			return new ResultData("F-1", "해당 게시물은 존재하지 않습니다.");
		}
		
		return new ResultData("S-1", id + "번 삭제를 성공하였습니다.", "id", id);
	}
	
	private boolean deleteArticle(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}
	
	private int writeArticle(String title, String body) {
		int id = articlesLastId + 1;
		String regDate = Util.getNoewDaeStr();
		String updateDate = Util.getNoewDaeStr();

		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);

		articlesLastId = id;

		return id;
	}
	
	private void makeTestData() {
		for ( int i = 0; i < 3; i++ ) {
			writeArticle("제목1", "내용1");			
		}
	}
	
	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {
		Article selArticle = null;
		
		
		for(Article article : articles) {
			if(article.getId() == id) {
				selArticle = article;
				break;
			}
		}
		
		
		if(selArticle == null) {
			return new ResultData("F-1","해당 게시물은 존재하지 않습니다.");
		}
		
		selArticle.setUpdateDate(Util.getNoewDaeStr());
		selArticle.setTitle(title);
		selArticle.setBody(body);
		
		return new ResultData("S-1", id + "번 수정되었습니다.", "id", id);
	}
}

