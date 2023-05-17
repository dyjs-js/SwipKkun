package test.example.cmmn.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class EgovImgPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware {

	private ServletContext servletContext;

	/**
	* PaginationRenderer
	*
	* @see 개발프레임웍크 실행환경 개발팀
	*/
	public void initVariables() {
		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" + "<image src='" + servletContext.getContextPath() + "/static/images/common/btn_pagination_first.png' border=0/></a>&#160;";
		previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" + "<image src='" + servletContext.getContextPath() + "/static/images/common/btn_pagination_prev.png' border=0/></a>&#160;";
		currentPageLabel = "<a href=\"#\" aria-current='page' onclick=\"({0}); return false;\"><span>{0}</span></a>&#160;";
		otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>&#160;";
		nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" + "<image src='" + servletContext.getContextPath() + "/static/images/common/btn_pagination_next.png' border=0/></a>&#160;";
		lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" + "<image src='" + servletContext.getContextPath() + "/static/images/common/btn_pagination_last.png' border=0/></a>&#160;";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}
}
