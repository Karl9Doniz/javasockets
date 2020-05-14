let connection = new WebSocket('ws://127.0.0.1:1111');

connection.onopen = function() {
 console.log('Connected');
 connection.send('hello, Server!');
};

connection.onerror = function (error) {
 console.log('WebSocket Error ' + error);
};

connection.onmessage = function (e) {
 let chatField = document.getElementById("chatField");
 console.log(e.data);
 chatField.innerHTML = e.data + "\n";
};

function onSendRegButtonClicked() {	
    let usernameHolder = document.getElementById("usernameHolder").value;
    let passwordHolder = document.getElementById("passwordHolder").value;
    let passwordSecondaryHolder = document.getElementById("passwordSecondaryHolder").value;
	//connection.send("{\nuser:\nname: " + usernameHolder + "\npassword: " + passwordHolder + "\n}");
	if (passwordHolder == passwordSecondaryHolder) {
		let userRegister = {
			type: "registration",
			name: usernameHolder,
			pass: passwordHolder
		}

		let json1 = JSON.stringify(userRegister);

		connection.send(json1);

	} else {
		alert("Passwords not matching");
	}
}

function onSendLogButtonClicked() {	
    let usernameHolder = document.getElementById("usernameLogHolder").value;
    let passwordHolder = document.getElementById("passwordLogHolder").value;
	//connection.send("{\nuser:\nname: " + usernameHolder + "\npassword: " + passwordHolder + "\n}");
		let userLogin = {
			type: "login",
			name: usernameHolder,
			pass: passwordHolder
		}

		let json2 = JSON.stringify(userLogin);

		connection.send(json2);
		
}

function onSendMesButtonClicked() {
	let messageHolder = document.getElementById("messageHolder").value;
	let usernameHolder = document.getElementById("usernameLogHolder").value;
	
	let userMessage = {
	 	type: "message",
	 	name: usernameHolder,
	 	text: messageHolder
	 }

	 chatField.innerHTML = usernameHolder + ": " + messageHolder + "\n";

	let json3 = JSON.stringify(userMessage);

	connection.send(json3);
}


function myFunction() {
  	let x = document.getElementById("passwordHolder");
  	let y = document.getElementById("passwordSecondaryHolder");
  		if (x.type === "password" && y.type === "password") {
    		x.type = "text";
    		y.type = "text";
  		} else {
    		x.type = "password";
    		y.type = "password";
  		}
	}
	function openTab(evt, tabName) {
  	var i, tabcontent, tablinks;
  	tabcontent = document.getElementsByClassName("tabcontent");
  	for (i = 0; i < tabcontent.length; i++) {
    	tabcontent[i].style.display = "none";
  	}
  		tablinks = document.getElementsByClassName("tablinks");
  		for (i = 0; i < tablinks.length; i++) {
    		tablinks[i].className = tablinks[i].className.replace(" active", "");
  	}
  		document.getElementById(tabName).style.display = "block";
  		evt.currentTarget.className += " active";
	}

function testFunction() {
  let x = document.getElementById("tabPanel");
  let y = document.getElementById("Register");
  let z = document.getElementById("Login");
  let t = document.getElementById("Chat");
    // x.style.display = "block";
    // y.style.display = "block";
    // z.style.display = "block";
    x.style.display = "none";
    y.style.display = "none";
    z.style.display = "none";
    t.style.display = "block";
  }