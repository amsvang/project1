const baseURL = 'http://localhost:7000';
const route = 'login'
const logoutBtn = document.getElementById('logout');

logoutBtn.addEventListener('submit', (e) => {
	e.preventDefault(); // stops form form submitting
    const URL = `${baseURL}/${route}`;

    fetch(URL, {
        method: ,
        body: 
        credentials: 'include', // stop cors related errors
    }) 


});