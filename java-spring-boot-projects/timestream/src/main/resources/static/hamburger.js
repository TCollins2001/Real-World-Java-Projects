const hamburgerBtn = document.getElementById("hamburgerBtn");
const navLinks = document.getElementById("navLinks");

if (hamburgerBtn && navLinks) {

    hamburgerBtn.addEventListener("click", () => {

        navLinks.classList.toggle("show");

        hamburgerBtn.classList.toggle("active");
        document.body.classList.toggle("menu-open");
    });
}