function updateQuantity(pid, delta, quantity) {
    if (!pid) {
        console.error("pid is undefined");
        return;
    }

    fetch("update-quantity-cart", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
        },
        body:
            "pid=" + pid +
            "&delta=" + delta
    })
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                // quantity
                quantity.textContent = data.quantity;

                // total cost of an item
                const item = quantity.closest(".product-item");
                item.querySelector(".total").textContent = formatPrice(data.itemTotalPrice);

                // total price of the cart
                const totalPrice = document.getElementById("cart-total-price")
                const finalPrice = document.getElementById("cart-final-price");
                totalPrice.textContent = formatPrice(data.cartTotalPrice);
                totalPrice.dataset.value = data.cartTotalPrice;
                finalPrice.textContent = formatPrice(data.cartFinalPrice);
            }
        });
}

function formatPrice(value) {
    return Number(value).toLocaleString('vi-VN') + " đ";
}

document.querySelectorAll('.product-item-quantity').forEach(control => {
    const pid = control.dataset.pid;
    const quantity = control.querySelector('.quantity');

    control.querySelector('.plus-quantity-button').addEventListener('click', () => {
        updateQuantity(pid, 1, quantity);
    });
    control.querySelector('.minus-quantity-button').addEventListener('click', () => {
        updateQuantity(pid, -1, quantity);
    });
});