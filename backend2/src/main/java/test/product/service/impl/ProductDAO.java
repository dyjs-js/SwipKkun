package test.product.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
import test.product.service.ProductVO;

@Repository("productDAO")
public class ProductDAO extends EgovAbstractMapper {
	
	/**
	 * 상품 목록
	 * @param vo - 조회할 정보가 담긴 CodeVO
	 * @return 조회 데이터
	 * @exception Exception
	 */
	public List<ProductVO> selectProductList(ProductVO vo) throws Exception {
		return selectList("productDAO.selectProductList", vo);
	}
	
	/**
	 * 상품 갯수
	 * @param vo - 갯수가 담긴 ProductVO
	 * @return 갯수 데이터
	 * @exception Exception
	 */
	public int selectProductListCnt(ProductVO vo) throws Exception {
		return (Integer)selectOne("productDAO.selectProductListCnt", vo);
	}
	
	/**
	 * 상품 정보
	 * @param vo - 등록할 정보가 담긴 ProductVO
	 * @return 등록 데이터
	 * @exception Exception
	 */
	public ProductVO getProduct(ProductVO vo) throws Exception {
		return (ProductVO)selectOne("productDAO.getProduct", vo);
	}
	
	/**
	 * 상품 조회수 업데이트
	 * @param vo - 수정할 정보가 담긴 ProductVO
	 * @return 수정 데이터
	 * @exception Exception
	 */
	public int updateProductHitCnt(ProductVO vo) throws Exception {
		return update("productDAO.updateProductHitCnt", vo);
	}
	
	/**
	 * 상품 리뷰 목록
	 * @param vo - 조회할 정보가 담긴 CodeVO
	 * @return 조회 데이터
	 * @exception Exception
	 */
	public List<ProductVO> selectProductReviewList(ProductVO vo) throws Exception {
		return selectList("productDAO.selectProductReviewList", vo);
	}
	
	/**
	 * 비슷한 상품 목록
	 * @param vo - 조회할 정보가 담긴 CodeVO
	 * @return 조회 데이터
	 * @exception Exception
	 */
	public List<ProductVO> selectProductNotList(ProductVO vo) throws Exception {
		return selectList("productDAO.selectProductNotList", vo);
	}
	
	/**
	 * 상품 등록
	 * @param vo - 등록할 정보가 담긴 ProductVO
	 * @return 등록 데이터
	 * @exception Exception
	 */
	public int insertProduct(ProductVO vo) throws Exception {
		return insert("productDAO.insertProduct", vo);
	}
	
	/**
	 * 상품 수정
	 * @param vo - 수정할 정보가 담긴 ProductVO
	 * @return 수정 데이터
	 * @exception Exception
	 */
	public int updateProduct(ProductVO vo) throws Exception {
		return update("productDAO.updateProduct", vo);
	}
	
	/**
	 * 상품 이미지 삭제
	 * @param vo - 이미지 삭제할 정보가 담긴 ProductVO
	 * @return 이미지 삭제 데이터
	 * @exception Exception
	 */
	public int updateProductImgDel(ProductVO vo) throws Exception {
		return update("productDAO.updateProductImgDel", vo);
	}
	
	/**
	 * 상품 삭제
	 * @param vo - 삭제할 정보가 담긴 ProductVO
	 * @return 삭제 데이터
	 * @exception Exception
	 */
	public int deleteProduct(ProductVO vo) throws Exception {
		return delete("productDAO.deleteProduct", vo);
	}
	
}
