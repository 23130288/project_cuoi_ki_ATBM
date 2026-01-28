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

document.querySelectorAll(".inner-circle1").forEach(btn => {
    btn.addEventListener("click", function () {
        const pid = this.dataset.pid;
        const pvid = this.dataset.pvid;
        const mainImg = this.dataset.mainImg;

        let url = `add-cart?pid=${pid}&pvid=${pvid}&mainImg=${mainImg}&q=1`;
        fetch(url)
            .then(res => {
                if (res.status === 401) {
                    window.location.href = "dang_nhap";
                    return;
                }
                return res.json();
            })
            .then(data => {
                if (data?.success) {
                    this.disabled = true;
                    this.innerHTML = '<i class="fa-solid fa-check"></i>';
                }
            })
            .catch(err => console.error(err));
    });
});