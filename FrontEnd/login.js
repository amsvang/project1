//validations------------------

const form = document.getElementById('login-form');
const username = document.getElementById('username').value;
const password = document.getElementById('password').value;

form.addEventListener('submit', (e) => {
	e.preventDefault();





let admin = 'admin';
let pw = 'password';


//get all users
//check username and password


if(username.value === admin && pw === password){
    window.location.href = 'admin.html';

} else{
    window.location.href ='employee.html';
}