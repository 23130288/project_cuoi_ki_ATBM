const voucherSelect = document.getElementById("voucherSelect");

voucherSelect.addEventListener("change", () => {
    const uvid = voucherSelect.value;
    applyVoucher(uvid);
});

function applyVoucher(uvid) {
    fetch("apply-voucher-cart", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
        },
        body: "uvid=" + uvid
    })
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                document.getElementById("discount-ammount").textContent = formatPrice(data.discount);
                document.getElementById("cart-final-price").textContent = formatPrice(data.cartFinalPrice);
            } else {
                alert(data.message);
                voucherSelect.value = "";
                document.getElementById("discount-ammount").textContent = "0 đ";
                document.getElementById("cart-final-price").textContent = formatPrice(data.cartTotalPrice);
            }
        });
}
function formatPrice(value) {
    return Number(value).toLocaleString('vi-VN') + " đ";
}