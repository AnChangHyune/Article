<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle"
	value="<span><i class='far fa-clipboard'></i></span> <span>${board.name} ARTICLE LIST</span>" />

<%@ include file="../common/head.jspf"%>

<div class="section section-article-list">
	<div class="container mx-auto">
		<div class="total-items">
			<span>TOTAL ITEMS : </span> <span>${totalItemsCount}</span>
		</div>

		<div class="total-pages">
			<span>TOTAL PAGES : </span> <span>${totalPage}</span>
		</div>

		<div class="page" style="margin-bottom: 5px;">
			<span>CURRENT PAGE : </span> <span>${page}</span>
		</div>

		<hr />
		<hr />
		
		<a href="../article/doAdd?action=writeArticle">글쓰기</a>

		<div class="articles">
			<c:if test="${articles == null || articles.size() == 0}">
				검색결과가 존재하지 않습니다.
			</c:if>
			<c:forEach items="${articles}" var="article">
				<a href=""> ID : ${article.id}<br> REG DATE :
					${article.regDate}<br> UPDATE DATE : ${article.updateDate}<br>
					TITLE : ${article.title}<br>
				</a>
				<hr />
			</c:forEach>
		</div>
		<div class="pages">
			<c:set var="pageMenuArmSize" value="4" />
			<c:set var="startPage"
				value="${page - pageMenuArmSize >= 1 ? page -pageMenuArmSize : 1}" />
			<c:set var="endPage"
				value="${page + pageMenuArmSize <= totalPage ? page + pageMenuArmSize : totalPage}" />

			<c:set var="url" value="?boardId=${board.id}" />
			<c:set var="url"
				value="${url}&searchKeywordType=${param.searchKeywordType}" />
			<c:set var="url" value="${url}&searchKeyword=${param.searchKeyword}" />

			<c:set var="aClassStr"
				value="px-2 inline-block border border-gray-200 rounded text-lg hover:bg-gray-200" />

			<c:if test="${startPage > 1}">
				<a class="${aClassStr}" href="${urlBass}&page=1">◀◀</a>
				<a class="${aClassStr}" href="${urlBass}&page=${startPage - 1}">◀</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a class="${aClassStr} ${page == i ? 'text-red-500' : ''}"
					href="${urlBase}&page=${i}">${i}</a>
			</c:forEach>

			<c:if test="${endPage < totalPage}">
				<a class="${aClassStr}" href="${urlBase}&page=${endPage + 1}">▶</a>

				<a class="${aClassStr}" href="${urlBase}&page=${totalPage}">▶▶</a>
			</c:if>
		</div>
	</div>
</div>

<%@ include file="../common/foot.jspf"%>
