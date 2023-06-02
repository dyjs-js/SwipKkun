package test.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import test.product.service.ProductService;
import test.product.service.ProductVO;

@Service("productService")
public class ProductServiceImpl extends EgovAbstractServiceImpl implements ProductService {
	
	/** productDAO */
	@Resource(name = "productDAO")
	private ProductDAO productDAO;
	
	/**
	 * 상품 목록
	 * @param vo - 조회할 정보가 담긴 CodeVO
	 * @return 조회 데이터
	 * @exception Exception
	 */
	public List<ProductVO> selectProductList(ProductVO vo) throws Exception {
		return productDAO.selectProductList(vo);
	}
	
	/**
	 * 상품 갯수
	 * @param vo - 갯수가 담긴 ProductVO
	 * @return 갯수 데이터
	 * @exception Exception
	 */
	public int selectProductListCnt(ProductVO vo) throws Exception {
		return productDAO.selectProductListCnt(vo);
	}
	
	/**
	 * 상품 정보
	 * @param vo - 등록할 정보가 담긴 ProductVO
	 * @return 등록 데이터
	 * @exception Exception
	 */
	public ProductVO getProduct(ProductVO vo) throws Exception {
		return productDAO.getProduct(vo);
	}
	
	/**
	 * 상품 조회수 업데이트
	 * @param vo - 수정할 정보가 담긴 ProductVO
	 * @return 수정 데이터
	 * @exception Exception
	 */
	public int updateProductHitCnt(ProductVO vo) throws Exception {
		return productDAO.updateProductHitCnt(vo);
	}
	
	/**
	 * 상품 리뷰 목록
	 * @param vo - 조회할 정보가 담긴 CodeVO
	 * @return 조회 데이터
	 * @exception Exception
	 */
	public List<ProductVO> selectProductReviewList(ProductVO vo) throws Exception {
		return productDAO.selectProductReviewList(vo);
	}
	
	/**
	 * 비슷한 상품 목록
	 * @param vo - 조회할 정보가 담긴 CodeVO
	 * @return 조회 데이터
	 * @exception Exception
	 */
	public List<ProductVO> selectProductNotList(ProductVO vo) throws Exception {
		return productDAO.selectProductNotList(vo);
	}
	
	/**
	 * 상품 등록
	 * @param vo - 등록할 정보가 담긴 ProductVO
	 * @return 등록 데이터
	 * @exception Exception
	 */
	public int insertProduct(ProductVO vo) throws Exception {
		return productDAO.insertProduct(vo);
	}
	
	/**
	 * 상품 수정
	 * @param vo - 수정할 정보가 담긴 ProductVO
	 * @return 수정 데이터
	 * @exception Exception
	 */
	public int updateProduct(ProductVO vo) throws Exception {
		return productDAO.updateProduct(vo);
	}
	
	/**
	 * 상품 이미지 삭제
	 * @param vo - 이미지 삭제할 정보가 담긴 ProductVO
	 * @return 이미지 삭제 데이터
	 * @exception Exception
	 */
	public int updateProductImgDel(ProductVO vo) throws Exception {
		return productDAO.updateProductImgDel(vo);
	}
	
	/**
	 * 상품 삭제
	 * @param vo - 삭제할 정보가 담긴 ProductVO
	 * @return 삭제 데이터
	 * @exception Exception
	 */
	public int deleteProduct(ProductVO vo) throws Exception {
		return productDAO.deleteProduct(vo);
	}
	
}
