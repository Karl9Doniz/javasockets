let message;
 if (localStorage.getItem("token") != null) {
  message = localStorage.getItem("token");
 } else {
  message = "";
 }


let connection = new WebSocket("ws://127.0.0.1:1111/index/" + message);
//localStorage.setItem('user', 'Andrew');
//alert(localStorage.getItem('user'));

connection.onopen = function() {
 console.log('Connected');
 connection.send(message);
};

connection.onerror = function (error) {
 console.log('WebSocket Error ' + error);
};

connection.onmessage = function (e) {
 console.log(e.data);
 if (e.data.startsWith("{")) {
 let user = JSON.parse(e.data);
 console.log(user.name);
}
};
