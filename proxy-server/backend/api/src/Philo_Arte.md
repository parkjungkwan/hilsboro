# Philo_Arte 전체 기능 및 담당 역할 기능 시연 영상

1. 프로젝트 명 : Philo_Arte(예술을 사랑하라)

2. 비전 : 예술학부 학생들을 위한 포트폴리오 제작 환경 지원 및 작품 저작권 보호와 지속적인 창작활동을 위한 크라우드 펀딩서비스

3. 제작 기간 : 2021.05.01 ~ 2021.06.10

4. 참여 인원 : 5명

5. 사용 기술

![사용기술](https://user-images.githubusercontent.com/75829369/121810291-340a0080-cc9b-11eb-96df-9fc2ab45573e.JPG)

6. 담당 역할
- 아티스트(예술 학부 학생)들의 작품 업로드 및 공유, 서로 의견을 나누고 응원하는 리뷰 및 댓글 기능 구현

[전체 시연 영상]
![프로젝트 전체 시연 영상](https://user-images.githubusercontent.com/75829369/121810419-c9a59000-cc9b-11eb-91b0-8874e9b4c7a9.gif)

[Review / Reply 전체 시연 영상]
![jaemin review](https://user-images.githubusercontent.com/75829369/121810423-d0cc9e00-cc9b-11eb-9b81-cf4fb187aa6b.gif)

[Review List]
- QueryDSL 적용 페이지 네이션 및 검색 기능 구현
  ![리뷰 페이지네이션 및 검색](https://user-images.githubusercontent.com/75829369/121810517-299c3680-cc9c-11eb-8cfa-54358caae6e4.gif)

[Review Register]
- Review List에서 Register 버튼 등록 시 페이지 이동
- 다중 파일 업로드 기능 구현
  ![리뷰 등록](https://user-images.githubusercontent.com/75829369/121810554-57817b00-cc9c-11eb-8bb8-1018f582b558.gif)

[Review Read]
- Review List에서 '더 보기' 버튼을 클릭하여 페이지 이동
  ![리뷰 리드](https://user-images.githubusercontent.com/75829369/121810658-ad562300-cc9c-11eb-98ed-7dc538d47d42.gif)

[Review Modify]
- Review Read에서 Modify 버튼 클릭 후 페이지 이동
- Upload Cancel 버튼으로 기존 파일 삭제 후 다른 사진 파일 업로드 기능 구현
  ![리뷰 수정](https://user-images.githubusercontent.com/75829369/121810993-d4f9bb00-cc9d-11eb-8823-982609bc827d.gif)

[Reply Register]
- Review Read의  'comments' 버튼 클릭 후 페이지 이동
- 댓글 미 등록 시 '첫 번째 댓글을 입력해주세요'에서 등록 시 작성 된 댓글 내용이 나오는 조건부 렌더링 적용
  ![댓글 등록](https://user-images.githubusercontent.com/75829369/121811062-14c0a280-cc9e-11eb-8644-631b3aa9800a.gif)

[Reply List]
- 댓글 사진은 다중 파일 업로드 기능을 구현 했으나 List가 Read역할도 하기에
  map() 사용으로 다중 업로드 시 댓글이 2개가 등록되어 1개 사진만 업로드 가능
- thumbnaillator 적용하여 썸네일 사진으로 등록, 썸네일 사진 클릭 시 원본 이미지 모달 창 렌더링

1) Reply modify
- Reply List에서 수정 할 댓글에 'Modify' 클릭하면 모달 창이 렌더링 되어 댓글 수정 가능

2) Reply Remove
- 삭제 할 댓글에 'Remove'를 클릭하여 삭제
  ![댓글 수정](https://user-images.githubusercontent.com/75829369/121811317-fc04bc80-cc9e-11eb-9a6b-6893b47aeac5.gif)

[Review Remove]
- Review Read에서 'Remove' 클릭 후 삭제 여부를 확인하고 Review List 페이지로 이동
  ![리뷰 삭제](https://user-images.githubusercontent.com/75829369/121811737-3c186f00-cca0-11eb-81e6-0d890f140f84.gif)

