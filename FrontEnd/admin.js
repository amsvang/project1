const url = 'http://localhost:7000/';
const route = 'admin';

const userTbody = document.getElementById('user-tbody');
const userTableContainer = document.getElementById('user-table-container');
const userTable = document.getElementById('user-table');
const userDataTable = document.getElementById('user-data-table');
const rmbTable = document.getElementById('rmb-table');
const rmbDataTable = document.getElementById('rmb-data-table');
const userBtn = document.getElementById('user-btn');
const rmbTbody = document.getElementById('rmb-tbody');
const rmbTableContainer = document.getElementById('rmb-table-container');
const rmbActions = document.getElementById('rmb-actions');
const rmbBtn = document.getElementById('rmb-btn');
const updateRmbBtn = document.getElementById('update-rmb-btn');
const updateRmbFormDiv = document.getElementById('update-rmb-form-div');
const submitRmbForm = document.getElementById('add-rmb-btn');
const updateRmbForm = document.getElementById('update-rmb-form');
// div buttons
const userToggleBtn = document.getElementById('user-toggle');
const rmbToggleBtn = document.getElementById('rmb-toggle');
const rmbRefresh = document.getElementById('rmb-refresh');
const userRefresh = document.getElementById('user-refresh');
//filter rmb buttons
const viewPending = document.getElementById('view-pending-btn');
const viewApproved = document.getElementById('view-approved-btn');
const viewDenied = document.getElementById('view-disapproved-btn');

const userItems = [];
const rmbItems = [];
const a = 'DIS';

//form
submitRmbForm.addEventListener('submit', (e) => {
	const URL = `${url}/${route}/reimbursement`;
	const formData = new FormData(updateRmbForm); // create form data object from form element/page
	let postData = {}; // declaring postData

	updateRmbForm.reset(); //clears out the form
	console.log('SUBMIT', formData);
	console.log(form);

	// Convert formData object to JSON object that back end will accept
	formData.forEach((value, key) => (postData[key] = value)); // select/option key value pairs 

	// let userObj = JSON.parse(localStorage.getItem('userObj'));
	postData = {
		...postData, //copying existing post data key values pairs ex. type and amount and desc

		userId: usernameValue, //get the id
		reimbursementStatus: statusTypeValue,
		reimbursementType: rmbTypeValue,
		isReimbursementSubmitted: true,
		isReimbursementResolved: false,
		reimbursementReceipt: true,
	};

	console.log(postData);

	fetch(URL, {
		method: 'put',
		body: JSON.stringify(postData),
		credentials: 'include',
	}).then((result) => console.log(result.status)); //result.status tells you 201 successful or 400 error

	e.preventDefault();
});

//USER-------------------------------------------------------------

// click button to grab user data
userBtn.addEventListener('click', () => {
	console.log('user button clicked');
	userDataTable.classList.remove('hide');
	userTbody.innerHTML = '';
	fetch(`${url}admin/users`)
		.then((res) => res.json())
		.then((data) => getUserData(data));
});
// add db data to user table---------------------------------------
getUserData = (data) => {
	for (user of data) {
		let tr = document.createElement('tr');
		userItems.push(tr);

		tr.innerHTML = `
		<td>
			<img src="https://randomuser.me/portraits/men/${user.userId}.jpg">
		</td>
		<td>
			100${user.userId}0
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
	}
};

//user data search filter

const userFilter = document.getElementById('user-filter');
userFilter.addEventListener('input', (e) => filterUserData(e.target.value));

const filterUserData = (searchTerm) => {
	userItems.forEach((item) => {
		if (item.innerText.toLowerCase().includes(searchTerm.toLowerCase())) {
			item.classList.remove('hide');
		} else {
			item.classList.add('hide');
		}
	});
};

//REIMBURSEMENTS------------------------------------------------

//click to rmb button to grab rmb data
rmbBtn.addEventListener('click', () => {
	// rmbActions.classList.remove('hide');
	rmbDataTable.classList.remove('hide');
	console.log('clicked');
	rmbTbody.innerHTML = '';
	fetch(`${url}admin/reimbursement/status/1`)
		.then((res) => res.json())
		.then((data) => getRmbData(data));
	console.log(rmbItems);
});
//add rmb data to rmb table------------------------------------
getRmbData = (data) => {
	for (rmb of data) {
		let tr = document.createElement('tr');
		rmbItems.push(tr);

		tr.innerHTML = `
			<td><img src="https://randomuser.me/portraits/men/${rmb.userId}.jpg"></td>
			<td>
			${rmb.id}
			</td>
			<td>
			${rmb.userId}
			</td>
			<td>
			${rmb.reimbursementType}
			</td>
			<td>
			${rmb.reimbursementStatus}
			</td>
			<td>

			$${rmb.reimbursementAmount}

			</td>
			<td>
			${rmb.isReimbursementSubmitted}
			</td>
			<td>
			${rmb.isReimbursementResolved}
			</td>
			<td>
			${rmb.description}
			</td>
			<td>
			${rmb.reimbursementReceipt}
			</td>
        `;

		rmbTbody.append(tr);
	}
};

//rmb data search filter----------------------------------

const rmbFilter = document.getElementById('rmb-filter');
rmbFilter.addEventListener('input', (e) => filterRmbData(e.target.value));

const filterRmbData = (searchTerm) => {
	rmbItems.forEach((item) => {
		if (item.innerText.toLowerCase().includes(searchTerm.toLowerCase())) {
			item.classList.remove('hide');
		} else {
			item.classList.add('hide');
		}
	});
};

//update rmb-------------------------------------------------

updateRmbBtn.addEventListener('click', () => {
	updateRmbFormDiv.classList.toggle('hide');
});

//Button function-------------------------------------------

userToggleBtn.addEventListener('click', () => {
	userTable.classList.toggle('hide');
});
rmbToggleBtn.addEventListener('click', () => {
	rmbTable.classList.toggle('hide');
});

userRefresh.addEventListener('click', () => {
	userTbody.innerHTML = '';
	fetch(`${url}admin/users`)
		.then((res) => res.json())
		.then((data) => getUserData(data));
});

rmbRefresh.addEventListener('click', () => {
	rmbTbody.innerHTML = '';
	fetch(`${url}admin/reimbursement`)
		.then((res) => res.json())
		.then((data) => getRmbData(data));
});

//rmb filter buttons

const rmbStatusA = (searchTerm) => {
	console.log('clicky');
	rmbItems.forEach((item) => {
		if (
			!item.innerText.toLowerCase().includes(searchTerm.toLowerCase()) &&
			!item.innerText.toLowerCase().includes(a.toLowerCase())
		) {
			item.classList.remove('hide');
		} else {
			item.classList.add('hide');
		}
	});
};
const rmbStatus = (searchTerm) => {
	console.log('clicky');
	rmbItems.forEach((item) => {
		if (item.innerText.toLowerCase().includes(searchTerm.toLowerCase())) {
			item.classList.remove('hide');
		} else {
			item.classList.add('hide');
		}
	});
};
viewPending.addEventListener('click', () => rmbStatus('PENDING'));
viewApproved.addEventListener('click', () => rmbStatusA('PEN'));
viewDenied.addEventListener('click', () => rmbStatus('DIS'));
// rmb form

const usernameValue = document.getElementById('username').value;
const statusTypeValue = document.getElementById('statusType').value;
const rmbTypeValue = document.getElementById('rmbType').value;
