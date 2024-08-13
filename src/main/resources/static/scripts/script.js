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

document.addEventListener('DOMContentLoaded', (event) => {
    const navLinks = document.querySelectorAll('.navTask');

    navLinks.forEach(link => {
        link.classList.add('inactive-link'); // Add inactive class initially

        link.addEventListener('click', function() {
            navLinks.forEach(link => {
                link.classList.remove('active-link');
                link.classList.add('inactive-link'); // Add inactive class to all links
            });
            this.classList.add('active-link');
            this.classList.remove('inactive-link'); // Remove inactive class from the clicked link
        });
    });
});