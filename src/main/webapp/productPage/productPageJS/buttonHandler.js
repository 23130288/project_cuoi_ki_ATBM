function showMessage(msg, color = 'green') {
    const status = document.getElementById('statusMessage');
    status.textContent = msg;
    status.style.color = color;
    status.classList.add('show');
    setTimeout(() => status.classList.remove('show'), 2000);
}


// size and color buttons
const colorButtons = document.querySelectorAll('.color-btn'); // get color buttons
const sizeButtons = document.querySelectorAll('.size-btn'); // get size buttons
const price = document.getElementById("price");
let selectedColor = null;
let selectedSize = null;
colorButtons.forEach(btn => {
    btn.addEventListener('click', () => {
        colorButtons.forEach(b => b.classList.remove('active'));
        btn.classList.add('active');

        selectedColor = btn.dataset.color;
        selectedSize = null;

        document.querySelectorAll('.size-btn').forEach(b => {
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
sizeButtons.forEach(btn => {
    btn.addEventListener('click', () => {

        if (btn.classList.contains('disabled')) return;

        sizeButtons.forEach(b => b.classList.remove('active'));

        btn.classList.add('active');
        selectedSize = btn.dataset.size;

        if (!selectedColor || !selectedSize) return;
        const variant = variants.find(v =>
            v.color === selectedColor && v.size === selectedSize
        );
        if (variant) {
            price.innerText = variant.price + " Đ";
        }
    });
});


/* ============================================== button handler =============================================== */
/* ============================================================================================================= */
/* ======= add to cart button ======= */
const addToCartBtn = document.getElementById('addToCartBtn'); // get button to cart page
addToCartBtn.addEventListener('click', () => {
    if (!selectedColor || !selectedSize) {
        showMessage('Vui lòng chọn cả màu và kích cỡ trước khi thêm vào giỏ hàng!', 'red');
        return;
    }
    const pid = addToCartBtn.dataset.pid;
    const mainImg = addToCartBtn.dataset.mainImg;
    const variant = variants.find(v =>
        v.color === selectedColor && v.size === selectedSize
    );
    let url = `add-cart?pid=${pid}&pvid=${variant.pvid}&mainImg=${mainImg}&q=1`;
    fetch(url)
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                showMessage('Thêm vào giỏ hàng thành công!', 'green');
            }
        })
        .catch(() => {
            showMessage('Lỗi hệ thống!', 'red');
        });
});

/* ======= buy button ======= */
/* open and off */
const buyPopup = document.getElementById('buyPopup');
const buyBtn = document.getElementById('buyBtn'); // get button to cart page
buyBtn.addEventListener('click', () => {
    const colorSelected = document.querySelector('.color-btn.active');
    const sizeSelected = document.querySelector('.size-options button.active');

    if (!colorSelected || !sizeSelected) {
        showMessage('Vui lòng chọn cả màu và kích cỡ trước khi thêm vào giỏ hàng!', 'red');
        return;
    }
    buyPopup.style.display = 'flex';
});
const cancelBuy = document.getElementById('cancelBuy');
cancelBuy.addEventListener('click', () => {
    buyPopup.style.display = 'none';
});
/* Buy handler */
const confirmBuy = document.getElementById('confirmBuy');
confirmBuy.addEventListener('click', () => {
    const name = document.getElementById('name').value.trim();
    const phone = document.getElementById('phone').value.trim();
    const address = document.getElementById('address').value.trim();

    if (!name || !phone || !address) {
        showMessage('Vui lòng điền đầy đủ thông tin trước khi xác nhận!', 'red');
        return;
    }
    showMessage('Cảm ơn, Đơn hàng của bạn đang được xử lý.')
    buyPopup.style.display = 'none';
});