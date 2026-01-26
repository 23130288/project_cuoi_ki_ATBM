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
            price.innerText = variant.price.toLocaleString('vi-VN') + " đ";
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
        .then(res => {
            if (res.status === 401) {
                window.location.href = 'dang_nhap';
                return;
            }
            return res.json();
        })
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
const buyBtn = document.getElementById('buyBtn'); // get button to cart page
buyBtn.addEventListener('click', () => {
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
        .then(res => {
            if (res.status === 401) {
                window.location.href = 'dang_nhap';
                return;
            }
            return res.json();
        })
        .then(data => {
            if (data.success) {
                window.location.href = 'cart';
            }
        })
        .catch(() => {
            showMessage('Lỗi hệ thống!', 'red');
        });
});