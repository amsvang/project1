let dataContainer = document.getElementById('data-container');

getUserData = (data) => {
	for (user of data) {
		let userList = document.createElement('li');

		userList.innerHTML = `
		
        <h3>${user.firstName} ${user.lastName}</h3>
		<h5> user id: ${user.userId}</h5>
		<h5> ${user.email}</h5>
        `;

		dataContainer.append(userList);
	}
};

const url = 'http://localhost:8080/';

let apiUrl = `${url}admin/users`;

let button = document.getElementById('user-btn');

// button.addEventListener('click', () => {
// 	console.log('clicked');
// 	fetch('https://randomuser.me/api/')
// 		.then((res) => res.json())
// 		.then((data) => console.log(data));
// });

button.addEventListener('click', () => {
	console.log('clicked');
	fetch(apiUrl)
		.then((res) => res.json())
		// .then((data) => console.log(data));
		.then((data) => getUserData(data));
});
