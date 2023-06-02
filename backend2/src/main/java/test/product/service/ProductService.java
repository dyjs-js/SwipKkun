package test.product.service;

import java.util.List;

public interface ProductService {
	
	/**
	 * 상품 목록
	 * @param vo - 조회할 정보가 담긴 CodeVO
	 * @return 조회 데이터
	 * @exception Exception
	 */
	List<ProductVO> selectProductList(ProductVO vo) throws Exception;
	
	/**
	 * 상품 갯수
	 * @param vo - 갯수가 담긴 ProductVO
	 * @return 갯수 데이터
	 * @exception Exception
	 */
	int selectProductListCnt(ProductVO vo) throws Exception;
	
	/**
	 * 상품 정보
	 * @param vo - 등록할 정보가 담긴 ProductVO
	 * @return 등록 데이터
	 * @exception Exception
	 */
	ProductVO getProduct(ProductVO vo) throws Exception;
	
	/**
	 * 상품 조회수 업데이트
	 * @param vo - 수정할 정보가 담긴 ProductVO
	 * @return 수정 데이터
	 * @exception Exception
	 */
	int updateProductHitCnt(ProductVO vo) throws Exception;
	
	/**
	 * 상품 리뷰 목록
	 * @param vo - 조회할 정보가 담긴 CodeVO
	 * @return 조회 데이터
	 * @exception Exception
	 */
	List<ProductVO> selectProductReviewList(ProductVO vo) throws Exception;
	
	/**
	 * 비슷한 상품 목록
	 * @param vo - 조회할 정보가 담긴 CodeVO
	 * @return 조회 데이터
	 * @exception Exception
	 */
	List<ProductVO> selectProductNotList(ProductVO vo) throws Exception;
	
	/**
	 * 상품 등록
	 * @param vo - 등록할 정보가 담긴 ProductVO
	 * @return 등록 데이터
	 * @exception Exception
	 */
	int insertProduct(ProductVO vo) throws Exception;
	
	/**
	 * 상품 수정
	 * @param vo - 수정할 정보가 담긴 ProductVO
	 * @return 수정 데이터
	 * @exception Exception
	 */
	int updateProduct(ProductVO vo) throws Exception;

	/**
	 * 상품 이미지 삭제
	 * @param vo - 이미지 삭제할 정보가 담긴 ProductVO
	 * @return 이미지 삭제 데이터
	 * @exception Exception
	 */
	int updateProductImgDel(ProductVO vo) throws Exception;
	
	/**
	 * 상품 삭제
	 * @param vo - 삭제할 정보가 담긴 ProductVO
	 * @return 삭제 데이터
	 * @exception Exception
	 */
	int deleteProduct(ProductVO vo) throws Exception;
	
}
