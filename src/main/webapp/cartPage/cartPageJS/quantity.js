function updateQuantity(pid, delta, qtyEl) {
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
                qtyEl.textContent = data.quantity;

                // total cost of an item
                const item = qtyEl.closest(".product-item");
                item.querySelector(".total").textContent = data.itemTotalPrice + " đ";

                // total price of the cart
                const totalPrice = document.getElementById("cart-total-price")
                const finalPrice = document.getElementById("cart-final-price");
                totalPrice.textContent = data.cartTotalPrice + " đ";
                totalPrice.dataset.value = data.cartTotalPrice;
                finalPrice.textContent = data.cartFinalPrice + " đ";
            }
        });
}

document.querySelectorAll('.product-item-quantity').forEach(control => {
    const pid = control.dataset.pid;
    const qtyEl = control.querySelector('.quantity');

    control.querySelector('.plus-quantity-button').addEventListener('click', () => {
        updateQuantity(pid, 1, qtyEl);
    });
    control.querySelector('.minus-quantity-button').addEventListener('click', () => {
        updateQuantity(pid, -1, qtyEl);
    });
});