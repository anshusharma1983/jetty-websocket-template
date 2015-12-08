$(document).ready(function(){
	initializeWebSocket();
	$("#submit").click(function(){
		var msg = $("#input").val();
		ws.send(msg);
		$("#input").val('');
	});
});

function initializeWebSocket() {
		if (!("WebSocket" in window)) {
			alert("The browser does not support websockets. Please install latest chrome or firefox.");
			return;
		}
		var wsURL = "ws://localhost:9999/WebSocketChat/anything";
		ws = new WebSocket(wsURL);
		var html = "";
		ws.onopen = function(event) {
			html = html + ("<br>Connection opened");
			$("#main").html(html);
			ws.send("Hi there from client !!");
		}
		ws.onmessage = function(event) {
			html = html + ("<br>Received response from server");
			html = html + "<br>Server says: " + event.data;
			$("#main").html(html);			
		}
		ws.onclose = function(event) {
			console.log("Lost connection to server");
		}
	}
