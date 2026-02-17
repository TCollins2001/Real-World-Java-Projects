document.addEventListener("DOMContentLoaded", () => {

  const form = document.querySelector(".contact-form");
  const modal = document.getElementById("transmissionModal");
  const okBtn = document.getElementById("okTransmissionBtn");

  if (!form || !modal) return;

  form.addEventListener("submit", (e) => {
    e.preventDefault();

    modal.classList.remove("hidden");

    form.reset();
  });

  if (okBtn) {
    okBtn.addEventListener("click", () => {
      modal.classList.add("hidden");
    });
  }

  modal.addEventListener("click", (e) => {
    if (e.target === modal) {
      modal.classList.add("hidden");
    }
  });

});
