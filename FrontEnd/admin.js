const url = 'http://localhost:8080/';

let deletBtn = document.getElementById('delete-btn');
const updateUserOptions = document.getElementById('update-user-options');
const userOptionsDiv = document.getElementById('user-options-div');
//USER-----------------------------------------------------------------
const userTbody = document.getElementById('user-tbody');
let dataTable = document.getElementById('data-table');
let userBtn = document.getElementById('user-btn');
let createUserBtn = document.getElementById('create-user-btn');

// click event to grab get all user data
userBtn.addEventListener('click', () => {
	updateUserOptions.classList.remove('hide');
	dataTable.classList.remove('hide');
	console.log('clicked');
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
let rmbBtn = document.getElementById('rmb-btn');

//click event to grab all rmb data
rmbBtn.addEventListener('click', () => {
	rmbTable.classList.remove('hide');
	console.log('clicked');
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

//FORM LOGIC

//show user options
createUserBtn.addEventListener('click', () => {
	userOptionsDiv.classList.remove('hide');
});

//User
//create user
//update user
//delete user
deleteUser = (id) => {};
//button on each row, when clicked will bring up modal with options to//

//Reimbursement
//create rmb
//update rmb
//delete rmb
