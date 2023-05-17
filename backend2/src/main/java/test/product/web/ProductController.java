package test.product.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import test.product.service.ProductService;
import test.product.service.ProductVO;

@Controller
public class ProductController {
	
	@Resource(name="propertiesService")
	protected EgovPropertyService propertyService;
	
	/** ProductService */
	@Resource(name = "productService")
	private ProductService productService;
	
	@RequestMapping(value = "/product/list.do")
	public String productList(
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session, 
		ModelMap model, 
		@ModelAttribute("productVO") ProductVO productvo 
	) throws Exception {
		productvo.setPageUnit(productvo.getPageUnit());
		productvo.setPageSize(productvo.getPageSize());
		productvo.setRecordCountPerPage(productvo.getPageUnit());
		
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(productvo.getPageIndex());
		paginationInfo.setRecordCountPerPage(productvo.getPageUnit());
		paginationInfo.setPageSize(productvo.getPageSize());

		productvo.setFirstIndex(paginationInfo.getFirstRecordIndex());
		productvo.setLastIndex(paginationInfo.getLastRecordIndex());
		productvo.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		if(productvo.getSearchOrderGb() == null) {
			productvo.setSearchOrderGb("DESC");
		}
		
		List<ProductVO> list = productService.selectProductList(productvo);
		paginationInfo.setTotalRecordCount(productService.selectProductListCnt(productvo));
		
		model.addAttribute("result", productvo);
		model.addAttribute("resultList", list);
		model.addAttribute("cnt", (paginationInfo.getTotalRecordCount() - (productvo.getPageIndex() * productvo.getPageUnit())) + productvo.getPageUnit());
		model.addAttribute("paginationInfo", paginationInfo);
		return "/product/list";
	}
	
	
	@RequestMapping(value = "/product/view.do")
	public String productView(
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session, 
		ModelMap model, 
		@ModelAttribute("productVO") ProductVO productvo 
	) throws Exception {
		// 상품 조회수 업데이트
		int updateHitCnt = productService.updateProductHitCnt(productvo);
		
		if(updateHitCnt > 0) {
			// 상품 상세정보
			ProductVO result = productService.getProduct(productvo);
			
			// 상품 리뷰 리ㅅ트
			List<ProductVO> reviewList = productService.selectProductReviewList(productvo);
			
			model.addAttribute("result", result);
			model.addAttribute("reviewList", reviewList);
		}
		
		return "/product/view";
	}
	
}
