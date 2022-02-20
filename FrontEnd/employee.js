const baseURL = 'http://localhost:7000';
const route = 'employee';

const form = document.getElementById('reimbursement-form');
const logoutBtn = document.getElementById('logout');
let dataTbody = document.getElementById('data-tbody');
let viewAccountInfoBtn = document.getElementById('view-account-info');
let viewReimbursementsBtn = document.getElementById('view-reimbursements');
let postReimbursementsBtn = document.getElementById('post-reimbursement');
let viewPendingReimbursementsBtn = document.getElementById(
	'view-pending-reimbursements'
);
let viewApprovedReimbursementsBtn = document.getElementById(
	'view-approved-reimbursements'
);
let viewDisapprovedReimbursementsBtn = document.getElementById(
	'view-disapproved-reimbursements'
);
let reimbursementBtnContainer = document.getElementById(
	'reimbursement-btn-container'
);
let reimbursementTableContainer = document.getElementById(
	'reimbursement-table-container'
);
let submitBtn = document.getElementById('reimbursement-submit-btn');
let userTableContainer = document.getElementById('user-table-container');
let userDataTbody = document.getElementById('user-data-tbody');
let welcomeUser = document.getElementById('h1-welcome-user');
let userObj = JSON.parse(localStorage.getItem('userObj'));

// checks userObj logged in otherwise route to login page
if (!userObj) {
	location.replace('http://localhost:7000/login.html');
}
welcomeUser.innerHTML =
	'Welcome ' + userObj.firstName + ' ' + userObj.lastName + '!';

getReimbursementData = (data) => {
	console.log('DATA', data);
	dataTbody.innerHTML = ''; //clear data
	reimbursementTableContainer.classList.remove('hidden');
	form.classList.add('hidden');

	for (let reimbursement of data) {
		let reimbursementTable = document.createElement('tr');

		reimbursementTable.innerHTML = `
		<td>${reimbursement.id}</td>
        <td>${reimbursement.reimbursementAmount}</td>
        <td>${reimbursement.reimbursementStatus}</td>
        <td>${reimbursement.reimbursementType}</td>
        `;

		dataTbody.append(reimbursementTable);
	}
};

// User Info ---------------------------------------------------------------------------------------------------

getUserData = (data) => {
	console.log('DATA', data);
	userDataTbody.innerHTML = ''; //clear data
	userTableContainer.classList.remove('hidden');
	reimbursementBtnContainer.classList.add('hidden');
	reimbursementTableContainer.classList.add('hidden');
	form.classList.add('hidden');

	for (let user of data) {
		let userTable = document.createElement('tr');

		userTable.innerHTML = `
		<td><img src="https://randomuser.me/portraits/men/${user.userId}.jpg"></td>
		<td>${user.userId}</td>
		<td>${user.username}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>
        <td>${user.role}</td>
        `;

		userDataTbody.append(userTable);
	}
};

//------------------------------------------------------------------------------------------------------------

viewReimbursementsBtn.addEventListener('click', () => {
	reimbursementBtnContainer.classList.remove('hidden');
	userTableContainer.classList.add('hidden');
});

postReimbursementsBtn.addEventListener('click', () => {
	form.classList.remove('hidden');
	reimbursementTableContainer.classList.add('hidden');
});

// ------------------------------------------------------------------------------------------------------------

form.addEventListener('submit', (e) => {
	const URL = `${baseURL}/${route}/reimbursement`;
	const formData = new FormData(form); // create form data object from form element/page
	let postData = {}; // declaring postData
	document.getElementById('reimbursement-form').reset(); //clears out the form
	console.log('SUBMIT', formData);
	console.log(form);

	// Convert formData object to JSON object that back end will accept
	formData.forEach((value, key) => (postData[key] = value)); // select/option key value pairs
	let userObj = JSON.parse(localStorage.getItem('userObj'));
	postData = {
		...postData, //copying existing post data key values pairs ex. type and amount and desc
		userId: userObj.userId, //get the id
		reimbursementStatus: 'PENDING',
		isReimbursementSubmitted: true,
		isReimbursementResolved: false,
		reimbursementReceipt: true,
	};

	console.log(postData);

	fetch(URL, {
		method: 'post',
		body: JSON.stringify(postData),
		credentials: 'include',
	}).then((result) => console.log(result.status)); //result.status tells you 201 successful or 400 error

	e.preventDefault();
});

// View user account info ------------------------------------------------------------------------------------

viewAccountInfoBtn.addEventListener('click', () => {
	let userObj = JSON.parse(localStorage.getItem('userObj'));
	let URL = `${baseURL}/employee/user/${userObj.userId}`;

	fetch(URL)
		.then((result) => result.json()) // convert payload(user obj) into json object
		.then((user) => getUserData([user]));
});

// View pending reimbursements -------------------------------------------------------------------------------

viewPendingReimbursementsBtn.addEventListener('click', () => {
	let apiUrl = `${baseURL}/employee/reimbursement/status`;
	let userObj = JSON.parse(localStorage.getItem('userObj'));
	console.log(userObj);
	apiUrl = `${apiUrl}/${userObj.userId}?status=PENDING`;

	fetch(apiUrl)
		.then((res) => res.json())
		.then((data) => getReimbursementData(data));
});

// View approved reimbursements ----------------------------------------------------------------------------

viewApprovedReimbursementsBtn.addEventListener('click', () => {
	let apiUrl = `${baseURL}/employee/reimbursement/status`;
	let userObj = JSON.parse(localStorage.getItem('userObj'));
	console.log(userObj);
	apiUrl = `${apiUrl}/${userObj.userId}?status=APPROVED`;

	fetch(apiUrl)
		.then((res) => res.json())
		.then((data) => getReimbursementData(data));
});

//----------------------------------------------------------------------------------------------------------

viewDisapprovedReimbursementsBtn.addEventListener('click', () => {
	let apiUrl = `${baseURL}/employee/reimbursement/status`;
	let userObj = JSON.parse(localStorage.getItem('userObj'));
	console.log(userObj);
	apiUrl = `${apiUrl}/${userObj.userId}?status=DISAPPROVED`;

	fetch(apiUrl)
		.then((res) => res.json())
		.then((data) => getReimbursementData(data));
});

//------------------------------------------------------------------------------------------------------------

logoutBtn.addEventListener('click', () => {
	let URL = `${baseURL}/logout`;
	let userObj = JSON.parse(localStorage.getItem('userObj'));
	fetch(URL).then(() => {
		localStorage.removeItem('userObj');
		location.replace('http://localhost:7000/login.html');
	});
});
