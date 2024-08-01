const button = document.getElementById("btn-add");
const formElement = document.getElementById("item_form");

button.addEventListener('click', () => {
    formElement.classList.toggle('show');
});

//handling the form that deals with ticking the fields
document.addEventListener('DOMContentLoaded', (event) => {
    const checkbox = document.getElementById('statusCheckbox');
    const form = document.getElementById('statusForm');

    checkbox.addEventListener('change', function() {
        form.submit();
    });
});