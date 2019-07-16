<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>마커 클러스터러에 클릭이벤트 추가하기</title>
  <style>
    table {
      width: 100%;
      border: 1px solid #444444;
    }

    th,
    td {
      border: 1px solid #444444;
    }
  </style>
</head>
<body>
  <p style="margin-top:-12px">
    사용한 데이터를 보시려면
    <em class="link">
      <a href="/board-place-infos" target="_blank">여기를 클릭하세요!</a>
    </em>
  </p>
  <div id="map" style="width:100%;height:350px;"></div>

  <table id="board_table">
    <thead>
      <tr>
        <th>번호</th>
        <th>작성자</th>
        <th>경기 날짜</th>
        <th>경기 시작 시간</th>
        <th>경기 형태</th>
        <th>경기 인원</th>
        <th>경기장 이름</th>
        <th>경기장 주소</th>
        <th>세부 내용</th>
        <th>글 등록일</th>
      </tr>
    </thead>
    <tbody>

    </tbody>
  </table>


  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${kakaoMapJavascriptKey}&libraries=clusterer"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script>
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
      center: new kakao.maps.LatLng(36.2683, 127.6358), // 지도의 중심좌표
      level: 14 // 지도의 확대 레벨
    });

    // 마커 클러스터러를 생성합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom 값을 true로 지정하지 않은 경우
    // 클러스터 마커를 클릭했을 때 클러스터 객체가 포함하는 마커들이 모두 잘 보이도록 지도의 레벨과 영역을 변경합니다
    // 이 예제에서는 disableClickZoom 값을 true로 설정하여 기본 클릭 동작을 막고
    // 클러스터 마커를 클릭했을 때 클릭된 클러스터 마커의 위치를 기준으로 지도를 1레벨씩 확대합니다
    var clusterer = new kakao.maps.MarkerClusterer({
      map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
      averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
      minLevel: 10, // 클러스터 할 최소 지도 레벨
      disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
    });

    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    $.get("/board-place-infos", function(data) {
      // 데이터에서 좌표 값을 가지고 마커를 표시합니다
      // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
      var markers = $(data.positions).map(function(i, position) {
        return new kakao.maps.Marker({
          position: new kakao.maps.LatLng(position.lat, position.lng)
        });
      });

      // 클러스터러에 마커들을 추가합니다
      clusterer.addMarkers(markers);
    });

    // 마커 클러스터러에 클릭이벤트를 등록합니다
    // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
    // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
    kakao.maps.event.addListener(clusterer, 'clusterclick', function(cluster) {

      // 현재 지도 레벨에서 1레벨 확대한 레벨
      var level = map.getLevel() - 1;

      // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
      map.setLevel(level, {
        anchor: cluster.getCenter()
      });
    });

    function getInfo() {
      // 지도의 현재 중심좌표를 얻어옵니다
      var center = map.getCenter();

      // 지도의 현재 레벨을 얻어옵니다
      var level = map.getLevel();

      // 지도타입을 얻어옵니다
      var mapTypeId = map.getMapTypeId();

      // 지도의 현재 영역을 얻어옵니다
      var bounds = map.getBounds();

      // 영역의 남서쪽 좌표를 얻어옵니다
      var swLatLng = bounds.getSouthWest();

      // 영역의 북동쪽 좌표를 얻어옵니다
      var neLatLng = bounds.getNorthEast();

      // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
      var boundsStr = bounds.toString();
      
      var message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
      message += '경도 ' + center.getLng() + ' 이고 <br>';
      message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
      message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
      message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
      message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';

      // 개발자도구를 통해 직접 message 내용을 확인해 보세요.
      console.log(message);

      $.get("/boards?southLat=" + swLatLng.getLat() + "&northLat=" + neLatLng.getLat() + "&westLng=" + swLatLng.getLng() + "&eastLng=" + neLatLng.getLng(), function(data) {
        // 데이터에서 좌표 값을 가지고 마커를 표시합니다
        // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다

        var tbody = $('#board_table tbody');
        tbody.children().remove();

        $.each(data, function(i, value) {
          console.log(i + " : " + value.number + ", " + value.gameType);

          tbody.append('<tr><td>' + value.number + '</td>' + '<td>' + value.author + '</td>' + '<td>' + value.gameDate + '</td>' + '<td>' + value.gameStartTime + '<td>' + value.gameType + '</td>' + '<td>' +
            value.gameGenderType + '</td>' + '<td>' + value.placeName + '</td>' + '<td>' + value.address + '</td>' + '<td>' + value.content + '</td>' + '<td>' + value.registerDate + '</td></tr>');
        });
      });
    }

    kakao.maps.event.addListener(map, 'zoom_changed', function() {
      getInfo();
    });
  </script>
</body>

</html>
