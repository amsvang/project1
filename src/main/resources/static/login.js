const baseURL = 'http://35.236.13.105:7000';
const route = 'login'
const form = document.getElementById('login-form');


form.addEventListener('submit', (e) => {
    const URL = `${baseURL}/${route}`;

    const formData = new FormData(form); // create form data object from form element/page

    fetch(URL, {
        method: 'post',
        body: formData,
        credentials: 'include',
    })
    .then(result => result.json())
    .then(data => {
        localStorage.setItem('userObj', JSON.stringify(data)); // this stores our user object-
        //in our local storage so we can get user reimbursement info for specific user id/status in employee.js
        console.log("data", data);
    });

	e.preventDefault();
});
//----------------------------------------------------------------------------

// let admin = 'admin';
// let pw = 'password';
// //get all users
// //check username and password

// if(username.value === admin && pw === password){
//     window.location.href = 'admin.html';

// } else{
//     window.location.href ='employee.html';
// }