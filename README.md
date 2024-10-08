2023년 카카오뱅크 Android 개발자 (대규모 채용) 코딩테스트 과제

```
1. 과제 설명
이미지를 검색해서 보관함에 수집하는 안드로이드 앱을 작성해주세요.

* 검색은 키워드 하나에 이미지 검색과 동영상 검색을 동시에 사용, 두 검색 결과를 합친 리스트를 사용합니다.
구체적인 사용 필드는 아래와 같습니다.
이미지 검색 API ( https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-image ) 의 thumbnail_url 필드
동영상 검색 API ( https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-video ) 의 thumbnail 필드
* 두 검색 결과를 datetime 필드를 이용해 정렬하여 출력합니다. (최신부터 나타나도록)

* UI는 fragment 2개를 사용합니다. (버튼이나 탭 선택 시 전환)
* 첫 번째 fragment : 검색 결과
검색어를 입력할 수 있습니다.
검색된 이미지 리스트가 나타납니다. 각 아이템에는 이미지와 함께 날짜와 시간을 표시합니다.
스크롤을 통해 다음 페이지를 불러옵니다.
리스트에서 특정 이미지를 선택하여 '내 보관함'으로 저장할 수 있습니다.
이미 보관된 이미지는 특별한 표시를 보여줍니다. (좋아요/별표/하트 등)
보관된 이미지를 다시 선택하여 보관함에서 제거 가능합니다.
* 두 번째 fragment : 내 보관함
검색 결과에서 보관했던 이미지들이 보관한 순서대로 보입니다.
보관한 이미지 리스트는 앱 재시작 후 다시 보여야 합니다. (DB 관련 라이브러리 사용 금지. SharedPreferences 사용 권장)

적혀있지 않은 내용은 자유롭게 작성하시면 됩니다. (요건을 침해하지 않는 범위에서 기능 추가 등)

2. 개발 요건
* 검색 데이터는 https://developers.kakao.com/product/search 의 Open API를 사용합니다.
오픈 소스 사용 가능합니다. 참고로 카카오뱅크에서는 retrofit, kotlinx-coroutines-android, rxjava 등을 사용하고 있습니다.
```

## 애플리케이션 기능

|이미지 검색 및 보관|무한스크롤|
|---|---|
|<img src="https://s6.ezgif.com/tmp/ezgif-6-07bba86181.gif" width="300" height="420"/>|<img src="https://github.com/user-attachments/assets/dca2dd51-a278-41f0-a12b-36bacec062ef" width="300" height="420"/>|
