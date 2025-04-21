# community_feed_service

## 📌 기술 스택

- **Java**: 17
- **Spring Boot**: 3.3.1
- **MySQL**: 8.0.11
- **테스트**: JUnit 5, Mockito


## 🧑‍🤝‍🧑 유저 메인 서비스

### Flow

1. **유저 생성**
    - 고유 `id`로 사용자 구분
    - `name`, `profileImage` 입력 및 저장
    - 검증: `name`은 빈 값일 수 없음 (예외 처리)

2. **팔로우 기능**
    - 다른 사용자 팔로우/언팔로우
    - 검증: 자기 자신은 팔로우 불가

3. **프로필 노출**
    - 프로필 클릭 시 노출 정보
        - `name`, `profileImage`
        - 팔로잉 수, 팔로워 수
        - 팔로잉/팔로워 리스트
            - 각 사용자 `name`, `profileImage`
        - 클릭 시 상세 리스트 노출

<br>

### 책임 주도 설계

- **유저 생성**
    - Controller: 요청 수신 및 매핑
    - Service: 로직 처리
    - Domain: `name` 검증
    - Repository: DB 저장

- **팔로우 기능**
    - Controller: 요청 수신
    - Service: 팔로우 로직
    - Domain: 방지/검증
    - Repository: 상태 저장 및 카운트 증감

- **프로필 노출**
    - Controller: 요청 수신
    - Service: 데이터 조회
    - Repository: DB 조회

---

## 📰 피드 서비스

### Flow

1. **게시물 작성**
    - 텍스트 입력 (5~500자)
    - 공개 대상 선택 (`ALL`, `FOLLOWERS`)
    - 게시물 제출 및 DB 저장
    - 검증: 텍스트 길이 예외 처리

2. **댓글 작성**
    - 텍스트 입력 (최대 100자)
    - 댓글 제출 및 DB 저장
    - 검증: 텍스트 길이 예외 처리

3. **게시물 상호작용**
    - 좋아요 (본인 게시물 불가)
    - 댓글 등록
    - 통계 조회 (좋아요/댓글 수 클릭)
    - 수정 기능
        - 글: 작성자만, `edited`, `editedAt`
        - 댓글: 작성자만, `edited`, `editedAt`

4. **피드 보기**
    - 팔로우한 사용자 게시물 시간 순 조회
    - 무한 스크롤 지원
    - 표시 항목: 글 내용, 작성자 `name`/`profileImage`, 좋아요 수, 댓글 수, 수정 여부, 발행 일자, 나의 좋아요 여부

5. **댓글 리스트**
    - 표시 항목: 작성자 `name`/`profileImage`, 댓글 내용, 좋아요 수, 수정 여부, 발행 일자, 나의 좋아요 여부
    - 댓글 좋아요 기능

<br>

### 책임 주도 설계

- **게시물 작성**
    - Controller: 요청 수신 및 매핑
    - Service: 유효성 확인
    - Domain: 텍스트 검증
    - Repository: DB 저장

- **댓글 작성**
    - Controller: 요청 수신
    - Service: 유효성 확인
    - Domain: 텍스트 검증
    - Repository: DB 저장

- **게시물 상호작용**
    - Controller: 요청 수신
    - Service: 상호작용 로직
    - Domain: 검증 (본인 여부)
    - Repository: 상태 업데이트

- **피드 보기**
    - Controller: 요청 수신
    - Service: 페이징 조회 로직
    - Repository: DB 조회

- **댓글 리스트**
    - Controller: 요청 수신
    - Service: 목록 조회 로직
    - Repository: DB 조회

---
