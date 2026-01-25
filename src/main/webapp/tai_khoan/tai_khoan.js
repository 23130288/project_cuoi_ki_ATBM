document.addEventListener("DOMContentLoaded", () => {
    const items = document.querySelectorAll(".menu_item .item");
    const infoBox = document.querySelectorAll(".information-container");

    function hideAllContainers() {
        infoBox.forEach(container => {
            container.style.display = "none";
        });
    }

    items.forEach(item => {
        item.addEventListener("click", (e) => {
            e.preventDefault();

            const targetId = item.dataset.target;
            if (!targetId) return;

            // active menu
            items.forEach(item => {
                item.classList.remove("active");
            });
            item.classList.add("active");

            // show content
            hideAllContainers();
            const targetContainer = document.getElementById(targetId);
            if (targetContainer) {
                targetContainer.style.display = "block";
            }
        });
    });

    // default: Thông tin tài khoản
    hideAllContainers();
    const defaultItem = document.querySelector('.menu_item .item[data-target="user-info"]');
    if (defaultItem) {
        defaultItem.click();
    }
});
document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".container").forEach(container => {

        const tabMenus = container.querySelectorAll(".tab-menu");
        const contents = container.querySelectorAll(".product-content");

        tabMenus.forEach(tab => {
            tab.addEventListener("click", e => {
                e.preventDefault();
                const target = tab.dataset.tab;
                tabMenus.forEach(t => t.classList.remove("active"));
                contents.forEach(c => c.classList.remove("active"));

                tab.classList.add("active");

                const content = container.querySelector(`.product-content.${target}`);
                if (content) {
                    content.classList.add("active");
                }
            });
        });
    });
});


/* open and off */
function openAdminPopup(title, bodyHTML, onConfirm) {
    const popup = document.getElementById("adminPopup");
    const popupTitle = document.getElementById("popup-title");
    const popupBody = document.getElementById("popup-body");
    const btnConfirm = document.getElementById("popup-confirm");
    const btnCancel = document.getElementById("popup-cancel");

    popupTitle.textContent = title;
    popupBody.innerHTML = bodyHTML;
    popup.style.display = "flex";

    btnCancel.onclick = () => popup.style.display = "none";
    btnConfirm.onclick = () => {
        popup.style.display = "none";
        if (onConfirm) onConfirm();
    };
}

document.addEventListener("DOMContentLoaded", function () {
    const btnDoiThongTin = document.getElementById("btn-doi-thong-tin");
    if (btnDoiThongTin) {
        btnDoiThongTin.addEventListener("click", () => {
            openAdminPopup(
                "Thay đổi thông tin",
                `
                <div class="popup_item">
                    <label>Tên tài khoản</label>
                    <input class="in4_input" type="text" id="name" value="${user.email}">
                </div>
                                
                <div class="popup_item">
                    <label>Số điện thoại*</label>
                    <input type="text" id="sdt" placeholder="Nhập số điện thoại" value="${user.phone}">
                </div>
                
                <div class="popup_item">
                    <label>Đia chỉ*</label>
                    <input type="text" id="dia_chi" placeholder="Nhập địa chỉ" value="${user.address}">
                </div>  
                 
                <div class="popup_item"> 
                    <label>Avata</label>                  
                    <div class="popup_avata">
                        <div class="preview-item">
                            <img src="image/avatar.jpg">
                        </div>    
                              
                        <div class="img-upload-box" id="drop-zone">
                            <span>+</span>
                            <p>Kéo hoặc click để thêm ảnh</p>
                        </div>
                    </div>
                </div>
                `,
                () => {
                    alert("Đã thay đổi thông tin thành công!");
                }
            );
        });
    }
});

document.querySelectorAll('.notification_item').forEach(item => {
    item.addEventListener('click', () => {
        window.location.href = 'ct_Order.jsp';
    });
});



