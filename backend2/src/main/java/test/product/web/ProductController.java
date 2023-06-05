package test.product.web;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
		
		String searchOrderBy = request.getParameter("searchOrderBy");
		String searchOrderGb = request.getParameter("searchOrderGb");
		
		productvo.setSearchOrderBy(searchOrderBy);
		productvo.setSearchOrderGb(searchOrderGb);
		
		if(searchOrderGb == null) {
			productvo.setSearchOrderGb("DESC");
		} else {
			productvo.setSearchOrderGb(searchOrderGb);
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
			
			// 비슷한 상품 리뷰 리스트
			List<ProductVO> resultNotList = productService.selectProductNotList(productvo);
			
			model.addAttribute("result", result);
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("resultNotList", resultNotList);
		}
		
		return "/product/view";
	}
	
	@RequestMapping(value = "/product/insert.do")
	public String productInsert(
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session, 
		ModelMap model, 
		@ModelAttribute("productVO") ProductVO productvo 
	) throws Exception {
		return "/product/insert";
	}
	
	@RequestMapping(value = "/product/edit.do")
	public String productEdit(
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
			
			// 비슷한 상품 리뷰 리스트
			List<ProductVO> resultNotList = productService.selectProductNotList(productvo);
			
			model.addAttribute("result", result);
			model.addAttribute("reviewList", reviewList);
			model.addAttribute("resultNotList", resultNotList);
		}
		
		return "/product/edit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/product/fileDel.do")
	public Map<String, Object> productFileDel(
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session, 
		ModelMap model, 
		@ModelAttribute("productVO") ProductVO productvo 
	) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		// 상품 조회수 업데이트
		ProductVO result = productService.getProduct(productvo);
		
		if(result != null) {
			File file = new File("C:/project3/test/WebContent" + result.getProduct_img());
			
			if(file.exists()) {
				if(file.delete()) {
					int imgDelCnt = productService.updateProductImgDel(productvo);
					
					if(imgDelCnt > 0) {
						map.put("success", "true");
					} else {
						map.put("success", "false");
					}
				} else {
					int imgDelCnt = productService.updateProductImgDel(productvo);
					
					if(imgDelCnt > 0) {
						map.put("success", "true");
					} else {
						map.put("success", "false");
					}
				}
			} else {
				int imgDelCnt = productService.updateProductImgDel(productvo);
				
				if(imgDelCnt > 0) {
					map.put("success", "true");
				} else {
					map.put("success", "false");
				}
			}
		} else {
			map.put("success", "false");
		}
		
		return map;
	}
	
	@RequestMapping(value = "/product/insert_proc.do")
	public String productInsertProc(
		MultipartHttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session, 
		ModelMap model
	) throws Exception {
		// 상품정보 추가
		ProductVO productvo = new ProductVO();
		String product_price = request.getParameter("product_price");
		
		if(product_price != null) {
			product_price = product_price.replaceAll(" ", "");
		}
		
		productvo.setProduct_name(request.getParameter("product_name"));
		productvo.setPrecaution(request.getParameter("precaution"));
		productvo.setProduct_content(request.getParameter("product_content"));
		productvo.setProduct_address(request.getParameter("product_address"));
		productvo.setProduct_price(product_price);
		
		MultipartFile imgFile = null;
		
		try {
			Iterator<String> iterator = request.getFileNames();
			imgFile = null;
			
			String filePath = "C:\\project3\\test\\WebContent\\assets\\img";
			
			if(iterator.hasNext()) {
				imgFile = request.getFile(iterator.next());
			}
			
			if(imgFile != null && imgFile.getOriginalFilename() != null) {
				productvo.setProduct_img("/assets/img/product/" + imgFile.getOriginalFilename());
				
				String saveFilePath = filePath + File.separator + imgFile.getOriginalFilename();
				imgFile.transferTo(new File(saveFilePath));
			}
		} catch(Exception e) {
			System.out.println("e ---->>> " + e.getMessage());
		}
		
		productvo.setProduct_address(request.getParameter("product_address"));
		productvo.setProduct_content(request.getParameter("product_content"));
		productvo.setProduct_hash_tag(request.getParameter("product_hash_tag"));
		
		int insertCnt = productService.insertProduct(productvo);
		
		if(insertCnt > 0) {
			return "redirect:/product/list.do";
		} else {
			return "redirect:/product/insert.do";
		}
	}
	
	@RequestMapping(value = "/product/edit_proc.do")
	public String productEditProc(
		MultipartHttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session, 
		ModelMap model
	) throws Exception {
		// 상품정보 업데이트
		ProductVO productvo = new ProductVO();
		
		productvo.setProduct_idx(Integer.parseInt(request.getParameter("product_idx")));
		String product_price = request.getParameter("product_price");
		
		if(product_price != null) {
			product_price = product_price.replaceAll(" ", "");
		}
		
		productvo.setProduct_name(request.getParameter("product_name"));
		productvo.setPrecaution(request.getParameter("precaution"));
		productvo.setProduct_content(request.getParameter("product_content"));
		productvo.setProduct_address(request.getParameter("product_address"));
		productvo.setProduct_price(product_price);
		
		MultipartFile imgFile = null;
		
		try {
			Iterator<String> iterator = request.getFileNames();
			imgFile = null;
			
			String filePath = "C:\\project3\\test\\WebContent\\assets\\img";
			
			if(iterator.hasNext()) {
				imgFile = request.getFile(iterator.next());
			}
			
			if(imgFile != null && imgFile.getOriginalFilename() != null) {
				productvo.setProduct_img("/assets/img/product/" + imgFile.getOriginalFilename());
				
				String saveFilePath = filePath + File.separator + imgFile.getOriginalFilename();
				imgFile.transferTo(new File(saveFilePath));
			}
		} catch(Exception e) {
			System.out.println("e ---->>> " + e.getMessage());
		}
		
		productvo.setProduct_address(request.getParameter("product_address"));
		productvo.setProduct_content(request.getParameter("product_content"));
		productvo.setProduct_hash_tag(request.getParameter("product_hash_tag"));
		
		int updateCnt = productService.updateProduct(productvo);
		
		if(updateCnt > 0) {
			return "redirect:/product/edit.do?product_idx=" + productvo.getProduct_idx();
		} else {
			return "redirect:/product/edit.do?product_idx=" + productvo.getProduct_idx();
		}
	}
	
	@RequestMapping(value = "/product/delete_proc.do")
	public String productDeleteProc(
		HttpServletRequest request, 
		HttpServletResponse response, 
		HttpSession session, 
		ModelMap model, 
		@ModelAttribute("productVO") ProductVO productvo 
	) throws Exception {
		// 상품 조회수 업데이트
		int updateCnt = productService.deleteProduct(productvo);
		
		if(updateCnt > 0) {
			return "redirect:/product/list.do";
		} else {
			return "redirect:/product/list.do";
		}
	}
	
}
