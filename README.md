### REST API - CRUD만들기

무엇을 주제로?
제목: 작곡가 이름  
내용: 클래식 작곡가의 출생지, 대표음악

### 1. 기능은 어떻게 정의?

- 전체 게시글 목록 조회 - **get(/composers)**
*제목(작곡가명), 작성자명, 내용, 날짜 조회
(DB table에는 auto increment id컬럼 있어야됨)
- 게시글 작성 - **post(/composers)**
*제목, 작성자명, 내용, 비밀번호를 저장(수정일자, 작성일자컬럼 있어야됨)
*저장된 게시글을 json 형식으로 http message body에 반환
- 게시글 조회 - **get(/composers/{작곡가 이름})**
*제목, 작성자명, 내용, 작성날짜를 조회
- 게시글 수정 - **put(/composers/{작곡가 이름})**
*수정할 데이터와 비밀번호를 같이 보내서 DB의 비밀번호와 request요청의 비밀번호가 일치하지 않으면 에러메세지를 띄운다.
*제목, 작성내용을 수정하고 http message body에 수정된 게시글을 반환
- 게시글 삭제 - **delete(/composers/{작곡가 이름})**
*request에 비밀번호를 같이 보내서 DB와 대조한 후 일치하면 삭제하고 http message body에 “deleted successfully”메시지를 반환

### 2. 프로젝트 생성 및 dependency + database 설정

### 3. Entity 구현
- crud/entity/Composers

### 4. DTO 구현
- crud/dto/CrudRequestDto
- crud/dto/CrudResponseDto

### 5. CRUD 구현
- crud/Controller/CrudController  
- crud/Service/CrudService
  
** @Transactional은 작업이 잘못되었을 시 롤백의 기능도 있지만 이 애노테이션을 붙이면 더티체킹을 통해 JPA가 Entity로 들어온 데이터의 변화를 감지하면 자동으로 데이터베이스에 반영된다. (업데이트 쿼리 작성 필요없음!!)

→ 이런 상태 변경 검사(더티체킹)의 대상은 영속성 컨텍스트가 관리하는 엔티티에만 적용된다.

→ JPA와 연결된  엔티티에만 적용된다는 뜻

아래의 두 경우에는 더티체킹 대상에 적용되지 않는다.  
-detach된 엔티티 (준영속) → JPA와 연결되지 않은 엔티티  
-DB에 반영되기 전 처음 생성된 엔티티 (비영속)  
- crud/Repository/CrudRepository

#### + 예외처리 추가  
리턴타입을 ResponseEntity로 바꾸고 try, catch로 예외처리를 해서 상태값을 리턴하도록 했다.  
비밀번호 대조가 필요한 수정, 삭제에서는 Service에서 Repository를 통해 DB에 저장된 비밀번호와 대조를 해야하기 때문에 서비스에서 Http 요청 메시지에 담긴 비밀번호와 DB의 비밀번호 비교 후 일치하지 않으면 throw Exception으로 발생한 에러를 던져서 컨트롤러에서 잡게 했다.

### 6. 테스트
### - 전체 목록 조회
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/3a4f5cee-4d00-4b4e-8b84-4f6afba95af8)

### -작곡가 생성
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/4eb987a6-63c4-4b3f-86d8-0e12365ece48)

### -작곡가 세부 조회
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/a8f2b9aa-4c33-4b6d-bfbf-63a4d2b71fdb)

### -작곡가 수정(비밀번호가 일치하지 않을 경우)
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/3d046084-5809-4174-8643-8c5d1e569ecc)

### -작곡가 수정(비밀번호가 일치)
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/8997660e-4c82-40c8-9ac7-210bcca0d0cf)

### -작곡가 삭제(비밀번호가 일치하지 않을 경우)
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/d768a8d5-0287-4c0d-92c6-ba8b737df215)

### -작곡가 삭제(비밀번호가 일치)
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/35ab7700-d72c-4631-84c7-9cf869b197de)





















