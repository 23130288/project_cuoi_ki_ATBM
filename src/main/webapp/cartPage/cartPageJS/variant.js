function updateVariant(pid, oldPvid, newVariant, popup) {
    fetch("update-variant-cart", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
        },
        body:
            "pid=" + pid +
            "&newPvid=" + newVariant.pvid
    })
        .then(res => res.json())
        .then(data => {
            if (!data.success) {
                alert("Không thể đổi phân loại");
                return;
            }

            const productItem = popup.closest(".product-item");
            const variantBox = productItem.querySelector(".product-item-variant");

            variantBox.querySelector(".variant-color").className = "variant-color " + newVariant.color;
            variantBox.querySelector(".variant-size").textContent = newVariant.size;

            productItem.querySelector(".price").textContent = data.price + " đ";
            productItem.querySelector(".total").textContent = data.itemTotalPrice + " đ";
            document.getElementById("cart-total-price").textContent = data.cartTotalPrice + " đ";
            document.getElementById("cart-final-price").textContent = data.cartTotalPrice + " đ";

            variantBox.dataset.oldPvid = newVariant.pvid;
            popup.classList.add("hidden");
        });
}




let selectedColor = null;
let selectedSize = null;
let variant = null;

// ====================================== OPEN ======================================
document.querySelectorAll(".btn-edit-variant").forEach(btn => {
    btn.addEventListener("click", () => {
        const popup = btn.closest(".product-item").querySelector(".popup");
        popup.classList.remove("hidden");
    });
});
document.querySelectorAll(".popup").forEach(popup => {
    const variants = JSON.parse(popup.dataset.variants);
    const colorButtons = popup.querySelectorAll('.color-btn');
    const sizeButtons = popup.querySelectorAll('.size-btn');
    const confirmBtn = popup.querySelector(".confirmVariant");
    const closeBtn = popup.querySelector(".closePopup");

    selectedColor = null;
    selectedSize = null;
    variant = null;

    // ================= COLOR =================
    colorButtons.forEach(btn => {
        btn.addEventListener('click', () => {

            colorButtons.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');

            selectedColor = btn.dataset.color;
            selectedSize = null;

            sizeButtons.forEach(b => {
                b.classList.remove('active', 'disabled');
            });
            const validSizes = variants.filter(v => v.color === selectedColor).map(v => v.size);

            sizeButtons.forEach(b => {
                if (!validSizes.includes(b.dataset.size)) {
                    b.classList.add('disabled');
                }
            });
        });
    });

    // ================= SIZE =================
    sizeButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            if (btn.classList.contains('disabled')) return;
            sizeButtons.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
            selectedSize = btn.dataset.size;

            if (!selectedColor || !selectedSize) return;
            variant = variants.find(v =>
                v.color === selectedColor && v.size === selectedSize
            );
        });
    });

    // ================= CONFIRM =================
    confirmBtn.addEventListener("click", () => {
        if (!variant) {
            alert("Vui lòng chọn đầy đủ màu và size");
            return;
        }
        const productItem = popup.closest(".product-item");
        const variantBox = productItem.querySelector(".product-item-variant");

        const pid = variantBox.dataset.pid;
        const oldPvid = variantBox.dataset.pvid;

        updateVariant(pid, oldPvid, variant, popup);
    });

    // ================= CLOSE =================
    closeBtn.addEventListener("click", () => {
        popup.classList.add("hidden");
    });
});