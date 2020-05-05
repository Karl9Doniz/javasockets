let connection = new WebSocket('ws://127.0.0.1:1111');

//localStorage.setItem('user', 'Andrew');
//alert(localStorage.getItem('user'));

connection.onopen = function() {
 console.log('Connected');
 connection.send('Hello, Server!');
};

connection.onerror = function (error) {
 console.log('WebSocket Error ' + error);
};

connection.onmessage = function (e) {
 console.log(e.data);
 if (e.data.startsWith("{")) {
  if (e.data.includes("newKey")) {
    localStorage.setItem('user', e.data.split(":")[1]);
  }
 }
};

function onRegButtonClicked() {	
    let usernameHolder = document.getElementById("usernameHolder").value;
    let passwordHolder = document.getElementById("passwordHolder").value;
		let userRegister = {
			type: "registration",
			name: usernameHolder,
			pass: passwordHolder
		}

		let json1 = JSON.stringify(userRegister);

		connection.send(json1);
	}

function onLogButtonClicked() {	
    let usernameHolder = document.getElementById("usernameHolder").value;
    let passwordHolder = document.getElementById("passwordHolder").value;
		let userLogin = {
			type: "login",
			name: usernameHolder,
			pass: passwordHolder
		}

		let json2 = JSON.stringify(userLogin);

		connection.send(json2);
		
}
