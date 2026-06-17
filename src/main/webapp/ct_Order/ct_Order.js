const reportMissingBtn = document.getElementById("report-missing-btn");
const reportMissing = document.getElementById("report-missing");
const cancelReportMissing = document.getElementById("cancel-report-missing");

if (reportMissingBtn && reportMissing && cancelReportMissing) {
    reportMissingBtn.addEventListener("click", () => {
        reportMissing.style.display = "flex";
    });
    cancelReportMissing.addEventListener("click", () => {
        reportMissing.style.display = "none";
    });
}
const supportForm = document.getElementById("supportForm");
if (supportForm) {
    supportForm.addEventListener("submit", function (e) {
        e.preventDefault();
        const formData = new FormData(this);
        fetch("support-order-changed", {
            method: "POST",
            body: formData
        })
            .then(res => res.json())
            .then(data => {
                if (data.success) {
                    reportMissing.style.display = "none";
                    document.getElementById("waitingMessage").style.display = "block";
                }
            })
    });
}

// signature
const confirmBtn = document.getElementById("confirmBtn");
const digitalSignModel = document.getElementById("digitalSignModel");
const cancelBtn = document.getElementById("cancelBtn");

if (confirmBtn && digitalSignModel && cancelBtn) {
    confirmBtn.addEventListener("click", () => {
        digitalSignModel.style.display = "flex";
    });
    cancelBtn.addEventListener("click", () => {
        digitalSignModel.style.display = "none";
    });
}

const signBtn = document.getElementById("signBtn");
if (signBtn) {
    signBtn.addEventListener("click", () => {
        const form = document.getElementById("form-verify");
        const formData = new FormData(form);
        fetch("verify-order", {
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
}