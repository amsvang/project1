const url = 'http://localhost:8080/';

//USER-----------------------------------------------------------------
const userActions = document.getElementById('user-actions');
const userOptionsDiv = document.getElementById('user-options-div');
const userTbody = document.getElementById('user-tbody');
let userTable = document.getElementById('user-table-container');
let userBtn = document.getElementById('user-btn');
let createUserBtn = document.getElementById('create-user-btn');

// click event to grab get all user data
userBtn.addEventListener('click', () => {
	userActions.classList.remove('hide');
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
	
		<td>
		${user.userId}
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

//REIMBURSEMENTS-------------------------------------------------------
const rmbTbody = document.getElementById('rmb-tbody');
const rmbTable = document.getElementById('rmb-table');
const rmbDiv = document.getElementById('rmb-table-container');
const rmbActions = document.getElementById('rmb-actions');
let rmbBtn = document.getElementById('rmb-btn');

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
		tr.innerHTML = `
	
		<td>
		${user.userId}
		</td>
			<td>
			${user.reimbursementType}
			
			</td>
			<td>
			${user.reimbursementStatus}
				

			</td>
			<td>
			${user.reimbursementAmount}
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

//FORM LOGIC-------------------------------------------

//show user options
createUserBtn.addEventListener('click', () => {
	userOptionsDiv.classList.remove('hide');
});

//User
//create user

//update user

let addUserForm = document
	.getElementById('user-form')
	.addEventListener('submit', createUser);

function createUser(e) {
	e.preventDefault();
	let username = document.getElementById('username').value;
	let password = document.getElementById('password').value;
	let firstname = document.getElementById('firstname').value;
	let lastname = document.getElementById('lastname').value;
	let email = document.getElementById('email').value;
	let role = document.getElementById('role').value;

	console.log(username, password, firstname, lastname, email, role);

	let userObj = {
		username,
		password,
		firstname,
		lastname,
		email,
		role,
	};

	fetch(`${url}admin/users`, {
		method: 'POST',
		headers: {
			Accept: 'application/json, text/plain, */*',
			'Content-Type': 'application/json',
		},

		body: JSON.parse(JSON.stringify(userObj)),
	})
		.then((res) => res.json())
		.then((data) => console.log(data))
		.catch((err) => console.log(err));
}

//delete user
// deleteUser = (id) => {};
//button on each row, when clicked will bring up modal with options to//

//Reimbursement
//create rmb
//update rmb
//delete rmb
