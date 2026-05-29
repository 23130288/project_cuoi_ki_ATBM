const confirmBtn = document.getElementById("confirmBtn");
const digitalSignModel = document.getElementById("digitalSignModel");
const cancelBtn = document.getElementById("cancelBtn");

confirmBtn.addEventListener("click", () => {
    digitalSignModel.style.display = "flex";
    document.getElementById("currentTime").textContent = new Date().toLocaleString();
});
cancelBtn.addEventListener("click", () => {
    digitalSignModel.style.display = "none";
});