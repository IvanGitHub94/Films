$(document).ready(function() {
document.querySelectorAll(".add-director").forEach(button => {
    button.addEventListener("click", event => {
        let form = event.target.nextElementSibling;
        form.removeAttribute("hidden");
        button.remove();
    });
});
});