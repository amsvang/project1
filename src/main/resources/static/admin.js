const url = 'http://localhost:7000/';
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
// div buttons
const userToggleBtn = document.getElementById('user-toggle');
const rmbToggleBtn = document.getElementById('rmb-toggle');
const userItems = [];
const rmbItems = [];

//USER-----------------------------------------------------------------

// click button to grab user data
userBtn.addEventListener('click', () => {
	console.log('user button clicked');
	userDataTable.classList.remove('hide');
	userTbody.innerHTML = '';
	fetch(`${url}admin/users`)
		.then((res) => res.json())
		.then((data) => getUserData(data));
});
// add db data to user table---------------
getUserData = (data) => {
	for (user of data) {
		let tr = document.createElement('tr');
		userItems.push(tr);

		tr.innerHTML = `
		<td>
			<img src="https://randomuser.me/portraits/men/${user.userId}.jpg">
		</td>
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

//REIMBURSEMENTS-------------------------------------------------------

//click to rmb button to grab rmb data
rmbBtn.addEventListener('click', () => {
	rmbActions.classList.remove('hide');
	rmbDataTable.classList.remove('hide');
	console.log('clicked');
	rmbTbody.innerHTML = '';
	fetch(`${url}admin/reimbursement`)
		.then((res) => res.json())
		.then((data) => getRmbData(data));
});
//add rmb data to rmb table
getRmbData = (data) => {
	for (user of data) {
		let tr = document.createElement('tr');
		rmbItems.push(tr);

		tr.innerHTML = `
	<td><img src="https://randomuser.me/portraits/men/${user.userId}.jpg"></td>
		<td>
		100${user.userId}u
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
	}
};

//rmb data search filter
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

const rmbStatusFilter = document.getElementById('status');

//update rmb

updateRmbBtn.addEventListener('click', () => {
	updateRmbFormDiv.classList.remove('hide');
});

//Button function-------------------------------------------
userToggleBtn.addEventListener('click', () => {
	userTable.classList.toggle('hide');
});
rmbToggleBtn.addEventListener('click', () => {
	rmbTable.classList.toggle('hide');
});
