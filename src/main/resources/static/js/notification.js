// script.js
var modal = document.getElementById("myModal");
var span = document.getElementsByClassName("close")[0];
var modalMessage = document.getElementById("modalMessage");

// WebSocket 연결 설정
var socket = new SockJS('/websocket');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    console.log('Connected: ' + frame);
    // 스프링에서 보내는 메시지를 구독
    stompClient.subscribe('/topic/notification', function (greeting) {
        showModal(greeting.body);
    });
});

function showModal(message) {
    modalMessage.innerText = message; // 메시지 설정
    modal.style.display = "block"; // 모달 표시
}

span.onclick = function() {
    modal.style.display = "none"; // 닫기 버튼
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none"; // 모달 바깥 클릭 시 닫기
    }
}
