document.querySelectorAll("add-director").forEach(button => {
    button.addEventListener("click", event => {
        let form = event.target.nextSibling;
        form.setStyle("visibility: visible");
    });
})