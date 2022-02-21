const baseURL = 'http://35.236.13.105:7000';
const route = 'login'
const form = document.getElementById('login-form');


let userObj = JSON.parse(localStorage.getItem('userObj'));
if(userObj && userObj.role == "EMPLOYEE") {
    location.replace(`${baseURL}/employee.html`);
} else if (userObj && userObj.role == "ADMIN") {
    location.replace(`${baseURL}/admin.html`)
}

form.addEventListener('submit', (e) => {
	e.preventDefault(); // stops form form submitting

    const URL = `${baseURL}/${route}`;
    const formData = new FormData(form); // create form data object from form element/page

    try {
        fetch(URL, {
            method: 'post',
            body: formData,
            credentials: 'include', // stop cors related errors
        })
        .then(result => {
            if (result.status === 401) {
                return Promise.resolve({}); // return nothing if 401
            } else {
                return result.json() // return the result object
            }
        })
        .then(data => {
            localStorage.setItem('userObj', JSON.stringify(data)); // this stores our user object-
            //in our local storage so we can get user reimbursement info for specific user id/status in employee.js

            if(data?.role == "EMPLOYEE") {
                location.replace("http://35.236.13.105:7000/employee.html");
            } else if (data?.role == "ADMIN") {
                location.replace("http://35.236.13.105/admin.html");
            } else {
                alert("You are not authorized");
            }
        });
    } catch (e) {
        console.log("ERR", e);
    }



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