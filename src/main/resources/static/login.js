const baseURL = 'http://localhost:8080';
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
        localStorage.setItem('userObj', JSON.stringify(data));
        console.log("data", data);
    });

	e.preventDefault();
});
