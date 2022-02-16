const url = 'http://localhost:8080/';


let dataContainer = document.getElementById('data-tbody');

let viewPendingReimbursementsBtn = document.getElementById('view-pending-reimbursements');

viewPendingReimbursementsBtn.addEventListener('click', () => {
	let apiUrl = `${url}employee/reimbursement/status`;
	let userObj = JSON.parse(localStorage.getItem('userObj'));
	console.log(userObj);
	apiUrl = `${apiUrl}/${userObj.userId}?status=PENDING`;

    fetch(apiUrl)
		.then((res) => res.json())
		.then((data) => getReimbursementData(data));
});

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






