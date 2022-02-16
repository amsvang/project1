const url = 'http://localhost:8080/';

let dataContainer = document.getElementById('data-tbody');
let viewPendingReimbursementsBtn = document.getElementById('view-pending-reimbursements');

//----------------------------------------------------------------------------------------------------------

viewPendingReimbursementsBtn.addEventListener('click', () => {
	let apiUrl = `${url}employee/reimbursement/status`;
	let userObj = JSON.parse(localStorage.getItem('userObj'));
	console.log(userObj);
	apiUrl = `${apiUrl}/${userObj.userId}?status=PENDING`;

    fetch(apiUrl)
		.then((res) => res.json())
		.then((data) => getReimbursementData(data));
});

//----------------------------------------------------------------------------------------------------------

getReimbursementData = (data) => {
	dataContainer.innerHTML = "";
	for (let reimbursement of data) {
		let reimbursementTable = document.createElement('tr');

		reimbursementTable.innerHTML = `
        <td>${reimbursement.reimbursementAmount}</td>

        `;

		dataContainer.append(reimbursementTable);
	}
};
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