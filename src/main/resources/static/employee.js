const baseURL = 'http://35.236.13.105:7000'';
const route = 'employee';


let dataTbody = document.getElementById('data-tbody');
let viewAccountInfoBtn = document.getElementById('view-account-info');
let viewReimbursementsBtn = document.getElementById('view-reimbursements');
let addReimbursementsBtn = document.getElementById('add-reimbursement');
let viewPendingReimbursementsBtn = document.getElementById('view-pending-reimbursements');
let viewApprovedReimbursementsBtn = document.getElementById('view-approved-reimbursements');
let viewDisapprovedReimbursementsBtn = document.getElementById('view-disapproved-reimbursements');
let reimbursementBtnContainer = document.getElementById('reimbursement-btn-container');
let reimbursementTableContainer = document.getElementById('reimbursement-table-container');
const form = document.getElementById('reimbursement-form');
let submitBtn = document.getElementById('reimbursement-submit-btn');


// Gets reimbursement data from server and display on web page in a table ----------------------------------------------
//Shifted to top of page because view pending and approved reimbursements could not see it.

getReimbursementData = (data) => {
    console.log("DATA", data);
	dataTbody.innerHTML = ""; //clear data
	reimbursementTableContainer.classList.remove("hidden");
	form.classList.add("hidden");

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

    viewReimbursementsBtn.addEventListener('click', () => {
    reimbursementBtnContainer.classList.remove("hidden");
});

/-----------------------------------------------------------------------------------------------------------------

form.addEventListener('submit', (e) => {
    const URL = `${baseURL}/${route}/reimbursement`;
    const formData = new FormData(form); // create form data object from form element/page
    let postData = {};
    console.log("SUBMIT", formData);
    console.log(form);

    // Convert formData object to JSON object that back end will accept
    formData.forEach((value, key) => postData[key] = value);
     let userObj = JSON.parse(localStorage.getItem('userObj'));
    postData = {
        ...postData, //copying existing post data key values pairs ex. type and amount
        userId: userObj.userId,
        reimbursementStatus: "PENDING",
        isReimbursementSubmitted: true,
        isReimbursementResolved: false,
        description: "Meal/Lunch",
        reimbursementReceipt: true
    }

    console.log(postData);

    fetch(URL, {
        method: 'post',
        body: JSON.stringify(postData),
        credentials: 'include',
    })
    .then(result => console.log(result.status)) //result.status tells you 201 successful or 400 error

	e.preventDefault();

});


// View pending reimbursements ----------------------------------------------------------------------------------------

viewPendingReimbursementsBtn.addEventListener('click', () => {
    let apiUrl = `${baseURL}/employee/reimbursement/status`;
    let userObj = JSON.parse(localStorage.getItem('userObj'));
    console.log(userObj);
    apiUrl = `${apiUrl}/${userObj.userId}?status=PENDING`;

    fetch(apiUrl)
        .then((res) => res.json())
        .then((data) => getReimbursementData(data));
});

// View approved reimbursements ---------------------------------------------------------------------------------------

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

addReimbursementsBtn.addEventListener('click', () => {
    form.classList.remove('hidden');

});



//let viewReimbursement = document.getElementById('view-reimbursement');

/*let dataContainer = document.getElementById('data-container');

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
*/