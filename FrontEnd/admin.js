const url = 'http://localhost:8080/';
const userActions = document.getElementById('user-actions');
const createUserFormDiv = document.getElementById('create-user-form-div');
const userTbody = document.getElementById('user-tbody');
let userTable = document.getElementById('user-table-container');
let userBtn = document.getElementById('user-btn');
let createUserForm = document.getElementById('create-user-form');
const rmbTbody = document.getElementById('rmb-tbody');
const rmbTable = document.getElementById('rmb-table');
const rmbDiv = document.getElementById('rmb-table-container');
const rmbActions = document.getElementById('rmb-actions');
let rmbBtn = document.getElementById('rmb-btn');
let updateRmbBtn = document.getElementById('update-rmb-btn');
let updateRmbFormDiv = document.getElementById('update-rmb-form-div');

const rmbItems = [];

//USER-----------------------------------------------------------------

// click event to grab get all user data
userBtn.addEventListener('click', () => {
	// userActions.classList.remove('hide');
	userTable.classList.remove('hide');
	console.log('clicked');
	userTbody.innerHTML = '';
	fetch(`${url}admin/users`)
		.then((res) => res.json())
		.then((data) => getUserData(data));
});
// function to dynamically create table row elemnts in table---------------
getUserData = (data) => {
	for (user of data) {
		let tr = document.createElement('tr');

		tr.innerHTML = `
		<td>    <img src="https://randomuser.me/portraits/men/${user.userId}.jpg"></td>
		<td>
		100${user.userId}
		</td>
			<td>
			${user.firstName}
			</td>
			<td>
			${user.lastName}
			</td>
			<td>
			${user.email}
			</td>
        `;

		userTbody.append(tr);
		// click the newly generated button
	}
};

//user actions row-------------------------
//show user options
let createUserBtn = document.getElementById('create-user-btn');
createUserBtn.addEventListener('click', () => {
	console.log('ive been clicked');
	// createUserFormDiv.classList.remove('hide');
});

//create user

createUserForm.addEventListener('submit', (e) => {
	e.preventDefault();
	const formData = new FormData(createUserForm);
	let postData = {};
	console.log('SUBMIT', formData);
	console.log(createUserForm);

	//convert formData from object to JSON object that the back end will accept
	formData.forEach((value, key) => (postData[key] = value));
	// let userObj = JSON.parse(localStorage.getItem('userObj'));

	postData = {
		...postData,
		username,
		password,
		firstName,
		lastName,
		email,
		role,
	};

	console.log(postData);

	fetch(`${url}admin/users`, {
		method: 'POST',
		body: JSON.stringify(postData),
		// credentials: 'include',
	}).then((res) => console.log(res.status));
});

// function createUser(e) {
// 	e.preventDefault();
// let username = document.getElementById('username').value;
// let password = document.getElementById('password').value;
// let firstname = document.getElementById('firstname').value;
// let lastname = document.getElementById('lastname').value;
// let email = document.getElementById('email').value;
// // let role = document.getElementById('role').value;

// console.log(username, password, firstname, lastname, email);

// let userObj = {
// 	username,
// 	password,
// 	firstname,
// 	lastname,
// 	email,
// 	// role,
// };

// fetch(`${url}admin/users`, {
// 	method: 'POST',
// 	headers: {
// 		Accept: 'application/json, text/plain, */*',
// 		'Content-Type': 'application/json',
// 	},

// 	body: JSON.stringify(userObj),
// 	body: JSON.parse(JSON.stringify(userObj)),
// })
// 	.then((res) => res.json())
// 	.then((data) => console.log(data))
// 	.catch((err) => console.log(err));
// }

//REIMBURSEMENTS-------------------------------------------------------

//click event to grab all rmb data
rmbBtn.addEventListener('click', () => {
	rmbActions.classList.remove('hide');
	rmbDiv.classList.remove('hide');
	console.log('clicked');
	rmbTbody.innerHTML = '';
	fetch(`${url}admin/reimbursement`)
		.then((res) => res.json())
		.then((data) => getRmbData(data));
});
//show rmb data
getRmbData = (data) => {
	for (user of data) {
		let tr = document.createElement('tr');

		rmbItems.push(tr);
		tr.innerHTML = `
	<td>    <img src="https://randomuser.me/portraits/men/${user.userId}.jpg"></td>
		<td>
		100${user.userId}
		</td>
			<td>
			${user.reimbursementType}
			
			</td>
			<td>
			${user.reimbursementStatus}
				

			</td>
			<td>
			$${user.reimbursementAmount}0.00
			</td>
			<td>
			${user.isReimbursementSubmitted}
			</td>
			<td>
			${user.isReimbursementResolved}
			</td>
			<td>
			${user.description}
			</td>
			<td>
			${user.reimbursementReceipt}
			</td>
        `;

		rmbTbody.append(tr);
		// click the newly generated button
	}
};
const filter = document.getElementById('filter');
filter.addEventListener('input', (e) => filterData(e.target.value));

const filterData = (searchTerm) => {
	rmbItems.forEach((item) => {
		if (item.innerText.toLowerCase().includes(searchTerm.toLowerCase())) {
			item.classList.remove('hide');
		} else {
			item.classList.add('hide');
		}
	});
};

updateRmbBtn.addEventListener('click', () => {
	updateRmbFormDiv.classList.remove('hide');
});
// create rmb
// update rmb
// delete rmb

//FORM LOGIC-------------------------------------------
