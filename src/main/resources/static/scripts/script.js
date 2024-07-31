const button = document.getElementById("btn-add");
const formElement = document.getElementById("item_form");

button.addEventListener('click', () => {
    formElement.classList.toggle('show');
});