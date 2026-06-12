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

document.getElementById("signBtn").addEventListener("click", () => {
    const form = document.getElementById("form-input-info");
    const formData = new FormData(form);
    fetch("checkout", {
        method: "POST",
        body: formData
    })
        .then(res => res.json())
        .then(data => {
            if (!data.success) {
                document.getElementById("error-verify").style.display = "block";
            } else {
                window.location.href = data.redirectUrl;
            }
        });
});