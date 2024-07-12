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

#### + 예외처리 추가(24/07/08)
리턴타입을 ResponseEntity로 바꾸고 try, catch로 예외처리를 해서 상태값을 리턴하도록 했다.  
비밀번호 대조가 필요한 수정, 삭제에서는 Service에서 Repository를 통해 DB에 저장된 비밀번호와 대조를 해야하기 때문에 서비스에서 Http 요청 메시지에 담긴 비밀번호와 DB의 비밀번호 비교 후 일치하지 않으면 throw Exception으로 발생한 에러를 던져서 컨트롤러에서 잡게 했다.

#### + response 정제 함수 추가(24/07/10)
응답메시지 안에 넣어줄 key 값을 클래스로 만들고, 컨트롤러 메서드에서 앞서 만든 ApiResponse 클래스를 객체생성 후 setter로 값을 설정해 준 후 변수에 담아 리턴하도록 했다.  
&rightarrow; 응답 메시지가 자세해져서 문제가 생겼을 때 원인을 파악하기 쉬워졌다.

#### + 중복코드 함수로 정리(24/07/11)
컨트롤러의 메서드 마다 객체생성 + setter로 값을 설정하는 부분의 중복 코드를 줄이기 위해서 일괄적으로 response를 정제하는 정적 메서드를 만들었다.  
&rightarrow; 중복코드를 함수로 정리해 코드가 훨씬 깔끔해졌다.

#### + 전역 예외 처리와 에러 커스텀(24/07/13)
컨트롤러의 모든 메서드에 try, catch 구문이 들어가는게 지저분해 보여서 Enum을 활용해서 Http 상태코드와 메시지를 만들고 RuntimeException을 상속받은 예외클래스를 만들어 적용했다.  
그 다음 @RestControllerAdvice와 @ExceptionHandler 애노테이션을 사용해서 글로벌 영역인 GlobalExceptionHandler 클래스를 만들어 예외처리를 지정하였다.  
&rightarrow; 예외처리를 따로 해두니 컨트롤러가 더더욱 깔끔해졌다.

### 6. 테스트
### - 전체 목록 조회
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/a8f5b8a5-d9a9-464e-a00a-bbdee68b7843)

### -작곡가 생성
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/7e0ed3bf-cd05-4a74-a1cb-3d709202cde4)

### -작곡가 세부 조회
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/abb08cac-42ba-471f-9392-d8f2a07e479b)

### -작곡가 세부 조회(작곡가가 존재하지 않을 경우)
![image](https://github.com/user-attachments/assets/8bfaedad-e40f-4ed8-a72d-ba8d6ddcf403)

### -작곡가 수정(비밀번호가 일치하지 않을 경우)
![image](https://github.com/user-attachments/assets/490c05a7-096c-476c-8273-ab9ecb2e1b50)

### -작곡가 수정(비밀번호가 일치)
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/edf7c325-15f5-4d7a-82d9-8662f48150a4)

### -작곡가 삭제(비밀번호가 일치하지 않을 경우)
![image](https://github.com/user-attachments/assets/d6703a04-eee3-4c69-bbf3-29ecb8d17b35)

### -작곡가 삭제(비밀번호가 일치)
![image](https://github.com/edelweiss007/CRUD_practice/assets/112394191/1f1082e7-aa7c-4edf-950c-a95a607a5744)





















