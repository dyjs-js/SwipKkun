-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.3.29-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 테이블 test.product_info 구조 내보내기
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE IF NOT EXISTS `product_info` (
  `product_idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '상품아이디',
  `created_idx` int(11) DEFAULT 0 COMMENT '대여아이디',
  `product_name` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '상품명',
  `product_eng_name` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '상품영문명',
  `product_day_bak` varchar(500) DEFAULT NULL COMMENT '상품1박',
  `product_price` varchar(500) DEFAULT NULL COMMENT '상품가격',
  `product_img` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '상품이미지',
  `product_address` varchar(500) CHARACTER SET utf8 DEFAULT NULL COMMENT '상품주소',
  `product_content` text CHARACTER SET utf8 DEFAULT NULL COMMENT '상품설명',
  `product_hash_tag` text CHARACTER SET utf8 DEFAULT NULL COMMENT '상품해시태그',
  `hit_cnt` int(11) DEFAULT NULL COMMENT '조회수',
  `reg_date` datetime DEFAULT NULL COMMENT '등록일',
  `edit_date` datetime DEFAULT NULL COMMENT '수정일',
  `product_status` tinyint(1) DEFAULT NULL COMMENT '상품상태',
  PRIMARY KEY (`product_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COMMENT='상품정보';

-- 테이블 데이터 test.product_info:~13 rows (대략적) 내보내기
INSERT INTO `product_info` (`product_idx`, `created_idx`, `product_name`, `product_eng_name`, `product_day_bak`, `product_price`, `product_img`, `product_address`, `product_content`, `product_hash_tag`, `hit_cnt`, `reg_date`, `edit_date`, `product_status`) VALUES
	(1, 0, '테스트1', 'Mari gray and Hail Grey and oak', '1', '100000', '/assets/img/product/product_img01.png', '서울 송파구 방이1동', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 61, '2023-05-16 21:53:28', '2023-05-16 21:53:29', 1),
	(2, 0, '테스트2', 'Mari gray and Hail Grey and oak', '1', '200000', '/assets/img/product/product_img02.png', '경기도 파주시 야당동', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 76, '2023-05-16 21:53:43', '2023-05-16 21:53:44', 1),
	(3, 0, '테스트3', 'Mari gray and Hail Grey and oak', '1', '300000', '/assets/img/product/product_img03.png', '전라북도 김제시', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 11, '2023-05-16 21:54:08', '2023-05-16 21:54:13', 1),
	(4, 0, '테스트4', 'Mari gray and Hail Grey and oak', '1', '100000', '/assets/img/product/product_img04.png', '전라남도', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 3, '2023-05-16 21:54:10', '2023-05-16 21:54:12', 1),
	(5, 0, '테스트5', 'Mari gray and Hail Grey and oak', '1', '20000', '/assets/img/product/product_img05.png', '경상남도', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 3, '2023-05-16 22:05:29', '2023-05-16 22:05:30', 1),
	(6, 0, '테스트6', 'Mari gray and Hail Grey and oak', '1', '11111', '/assets/img/product/product_img06.png', '경상북도', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 6, '2023-05-16 23:38:49', '2023-05-16 23:38:50', 1),
	(7, 0, '테스트7', 'Mari gray and Hail Grey and oak', '1', '22222', '/assets/img/product/product_img07.png', '제주도', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 3, '2023-05-16 23:39:06', '2023-05-16 23:39:07', 1),
	(8, 0, '테스트8', 'Mari gray and Hail Grey and oak', '1', '33333', '/assets/img/product/product_img08.png', '충청남도', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 40, '2023-05-16 23:39:24', '2023-05-16 23:39:25', 1),
	(9, 0, '테스트9', 'Mari gray and Hail Grey and oak', '1', '34444', '/assets/img/product/product_img09.png', '충청북도', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 3, '2023-05-16 23:41:26', '2023-05-16 23:41:27', 1),
	(10, 0, '테스트10', 'Mari gray and Hail Grey and oak', '1', '12312', '/assets/img/product/product_img10.png', '울릉도', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 3, '2023-05-16 23:44:20', '2023-05-16 23:44:21', 1),
	(11, 0, '테스트11', 'Mari gray and Hail Grey and oak', '1', '33334', '/assets/img/product/product_img11.png', '울릉도', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 18, '2023-05-16 23:44:34', '2023-06-03 03:26:47', 1),
	(12, 0, '테스트12', 'Mari gray and Hail Grey and oak', '1', '34444', '/assets/img/product/product_img12.png', '광주광역시', '공기가 잘 통하는 디자인은 봉기성을 제공하여 몇 시간 동안 시원함을 유지하며, 프레임이 없는 3D 인텔리전트 등받이에는 몸과 함께 구부러지는 델라스토어 가닥이 있어 하루 종일 변화하는 자세와 자세를 수용할 수 있는 광범위한 동작 범위를 제공합니다.', '#의자 #사무용품 #사무용의자 #의자추천 #편안한의자 #업무용의자', 28, '2023-05-16 23:44:46', '2023-05-16 23:44:47', 1),
	(13, 0, '11', '22', '33', '444', '/assets/img/product/', NULL, '1231312', '32131', 3, '2023-06-03 03:39:40', NULL, 1);

-- 테이블 test.product_review_info 구조 내보내기
DROP TABLE IF EXISTS `product_review_info`;
CREATE TABLE IF NOT EXISTS `product_review_info` (
  `product_review_idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '리뷰 고유키',
  `product_idx` int(11) DEFAULT NULL COMMENT '상품 고유키',
  `review_score` int(11) DEFAULT NULL COMMENT '리뷰 점수',
  `review_content` text CHARACTER SET utf8 DEFAULT NULL COMMENT '리뷰 내용',
  `review_writer` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '리뷰 등록자',
  `reg_date` datetime DEFAULT NULL COMMENT '등록일',
  `edit_date` datetime DEFAULT NULL COMMENT '수정일',
  `product_review_status` tinyint(1) DEFAULT NULL COMMENT '상태',
  PRIMARY KEY (`product_review_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COMMENT='상품 리뷰';

-- 테이블 데이터 test.product_review_info:~5 rows (대략적) 내보내기
INSERT INTO `product_review_info` (`product_review_idx`, `product_idx`, `review_score`, `review_content`, `review_writer`, `reg_date`, `edit_date`, `product_review_status`) VALUES
	(1, 1, 4, '테스트 123', '김*경', '2023-05-17 02:53:14', '2023-05-17 02:53:15', 1),
	(2, 1, 5, '테스트 1234', '유*민', '2023-05-17 02:53:25', '2023-05-17 02:53:26', 1),
	(3, 1, 5, '테스트 12345', '이*희', '2023-05-17 02:53:38', '2023-05-17 02:53:39', 1),
	(4, 2, 3, '1', '이*희', '2023-05-17 08:35:36', '2023-05-17 08:35:37', 1),
	(5, 2, 4, '2', '유*민', '2023-05-17 08:35:36', '2023-05-17 08:35:37', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
