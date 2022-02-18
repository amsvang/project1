const url = 'http://localhost:8080/';
const table = document.getElementById('table');
const tbody = document.getElementById('tbody');

let apiUrl = `${url}admin/users`;
let button = document.getElementById('user-btn');

// clcik event to grab get all user data--------------------------------
button.addEventListener('click', () => {
	let userDiv = document.getElementById('userDiv');

	console.log('clicked');
	fetch(apiUrl)
		.then((res) => res.json())
		// .then((data) => console.log(data));
		.then((data) => getUserData(data));
});

// function to dynamically create table row elemnts in table------------
getUserData = (data) => {
	for (user of data) {
		let tr = document.createElement('tr');
		tr.innerHTML = `
	
		<td>
		${user.userId}
		</td>
			<td>
			${user.firstName}
			</td>
			<td>
			${user.lastName}
			</td>
			<td><button>update</button></td>
        `;
		tbody.append(tr);
	}
};

//button on each row, when clicked will bring up modal with options to
