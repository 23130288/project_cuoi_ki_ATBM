const voucherBox = document.querySelector('.voucher-box');
const toggle = document.getElementById('voucher-toggle');
const selectedText = document.getElementById('voucher-selected');

toggle.addEventListener('click', () => {
    voucherBox.classList.toggle('open');
});

// khi chọn voucher
document.querySelectorAll('input[name="voucher"]').forEach(radio => {
    radio.addEventListener('change', () => {
        const label = radio.closest('label').querySelector('.voucher-name').textContent;

        selectedText.textContent = label;
        voucherBox.classList.remove('open');
    });
});