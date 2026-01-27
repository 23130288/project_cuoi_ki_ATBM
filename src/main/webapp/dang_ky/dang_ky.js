//phần làm nút hiện mật khẩu
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirm_password');
const togglePassword = document.getElementById('togglePassword');
const toggleConfirm = document.getElementById('toggleConfirmPassword');

// Hover để xem mật khẩu
togglePassword.addEventListener('mouseover', () => {
    password.type = 'text';
});
togglePassword.addEventListener('mouseout', () => {
    password.type = 'password';
});

toggleConfirm.addEventListener('mouseover', () => {
    confirmPassword.type = 'text';
});
toggleConfirm.addEventListener('mouseout', () => {
    confirmPassword.type = 'password';
});


const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z0-9]).{8,}$/;
const passwordError = document.getElementById("passwordError");
const confirmError = document.getElementById("confirmError");
const submit = document.getElementById("log");

// Kiểm tra mật khẩu (có ít nhất 8 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt)
password.addEventListener("input", () => {
    const pass = password.value;
    const passwordValid = passwordRegex.test(pass);
    passwordError.textContent = passwordValid ? "" : "Mật khẩu phải có ít nhất 8 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt.";
    submit.disabled = !passwordValid;
});
confirmPassword.addEventListener("input", () => {
    const confirm = confirmPassword.value;
    const confirmValid = password.value === confirm && confirm.length > 0;
    confirmError.textContent = confirmValid ? "" : "Mật khẩu nhập lại không khớp.";
    submit.disabled = !confirmValid;
});



// ============================================ EMAIL ============================================
const email = document.getElementById("email");
const emailError = document.getElementById("emailError");
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;

email.addEventListener("input", () => {
    const em = email.value;
    const emValid = emailRegex.test(em);
    emailError.textContent = emValid ? "" : "Sai định dạng email";
    submit.disabled = !emValid;
});
