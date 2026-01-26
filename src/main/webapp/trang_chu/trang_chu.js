document.querySelectorAll(".slider-container").forEach(container => {
    const grid = container.querySelector(".product-grid");
    const cards = grid.querySelectorAll(".product-card");
    let index = 0;
    const showCount = 3;

    function update() {
        cards.forEach((card, i) => {
            card.style.display = (i >= index && i < index + showCount) ? "block" : "none";
        });
    }

    container.querySelector(".arrow.left").addEventListener("click", () => {
        index = (index - showCount + cards.length) % cards.length;
        update();
    });

    container.querySelector(".arrow.right").addEventListener("click", () => {
        index = (index + showCount) % cards.length;
        update();
    });

    update();
});

const buttons = document.querySelectorAll(".getBtn");

buttons.forEach(btn => {
    btn.addEventListener("click", () => {
        alert("Bạn đã nhận được voucher!");
    });
});




