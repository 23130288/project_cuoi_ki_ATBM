document.querySelectorAll(".slider-container").forEach(container => {
    const grid = container.querySelector(".product-grid");
    const cards = grid.querySelectorAll(".product-card");
    const navContainer = container.querySelector(".slider-nav");
    let index = 0;
    const showCount = 6;

    const totalPages = Math.ceil(cards.length / showCount);


    function createNavButtons() {
        if (!navContainer || totalPages <= 1) return;

        navContainer.innerHTML = '';

        for (let i = 0; i < totalPages; i++) {
            const pageButton = document.createElement("button");
            pageButton.textContent = i + 1;
            pageButton.dataset.pageIndex = i;

            pageButton.addEventListener("click", () => {
                // Đặt index sản phẩm đầu tiên của trang mới
                index = i * showCount;
                update();
            });
            navContainer.appendChild(pageButton);
        }
    }


    function update() {
        cards.forEach((card, i) => {
            // Kiểm tra xem card có nằm trong khoảng [index, index + showCount) không
            const isVisible = (i >= index && i < index + showCount);
            // Ẩn/hiện thẻ sản phẩm
            card.style.display = isVisible ? "block" : "none";
        });

        updateNavButtons();
    }

    function updateNavButtons() {
        if (!navContainer || totalPages <= 1) return;

        const currentPageIndex = Math.floor(index / showCount);

        navContainer.querySelectorAll("button").forEach(button => {
            if (parseInt(button.dataset.pageIndex) === currentPageIndex) {
                button.classList.add("active");
            } else {
                button.classList.remove("active");
            }
        });
    }

    createNavButtons();
    update();
});

/* */

const filterToggle = document.getElementById("filterToggle");
const filterPanel = document.getElementById("filterPanel");
const tags = document.querySelectorAll(".tag");

filterToggle.addEventListener("click", () => {
    // Toggle panel
    const isOpen = filterPanel.style.display === "block";
    filterPanel.style.display = isOpen ? "none" : "block";

    // Toggle icon
    const icon = filterToggle.querySelector("i");
    icon.classList.toggle("fa-plus", isOpen);
    icon.classList.toggle("fa-minus", !isOpen);
});

// Toggle tag selection
tags.forEach(tag => {
    tag.addEventListener("click", () => {
        tag.classList.toggle("selected");
    });
});










