-- category
INSERT INTO category (id, category_name) VALUE (1, '액티비티');
INSERT INTO category (id, category_name) VALUE (2, '건강/뷰티');
INSERT INTO category (id, category_name) VALUE (3, '자기개발');
INSERT INTO category (id, category_name) VALUE (4, '모임');

-- hobby
-- 1: 액티비티
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '등산');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '야구');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '축구');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '농구');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '테니스');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '배드민턴');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '클라이밍');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '여행');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '서핑');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '스노우쿨링');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '테마파크');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 1, '댄스');

-- 2: 건강/뷰티
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 2, '헬스');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 2, '크로스핏');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 2, '요가');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 2, '필라테스');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 2, '뷰티');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 2, '마사지');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 2, '심리/상담');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 2, '명상');

-- 3: 자기개발
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 3, '요리');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 3, '음악');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 3, '미술');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 3, '어학');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 3, '재태크');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 3, '교양');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 3, '취업/이직');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 3, '공예/DIY');

-- 4: 모임
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 4, '스터디');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 4, '수다');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 4, '게임');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 4, '술');
INSERT INTO hobby (id, category_id, hobby_name) VALUE (NULL, 4, '클럽/파티');