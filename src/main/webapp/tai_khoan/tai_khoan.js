document.addEventListener("DOMContentLoaded", function () {
    const items = document.querySelectorAll(".menu_item .item");
    const infoBox = document.querySelector(".infomation");

    // Dữ liệu hiển thị cho từng mục
    const contents = {
        "Thông tin tài khoản": `
       <h2>Thông tin tài khoản</h2>

       <div class="in4">
        <div class="in4_row">
            <label class="in4_label" for="email">Tên tài khoản</label>
            <input class="in4_input" type="text" id="name" name="name" value="${user.name}" readonly>
        </div>


        <div class="in4_row">
            <label class="in4_label" for="email">Thông tin đăng nhập</label>
            <input class="in4_input" type="text" id="email" name="email" value="${user.email}" readonly>
        </div>

        <div class="in4_row">
            <label class="in4_label" for="email">Số điện thoại *</label>
            <input class="in4_input" ="text" id="sdt" name="sdt" placeholder="Bạn chưa nhập sdt" value="${user.phone}" readonly>
        </div>

        <div class="in4_row">
            <label class="in4_label" for="email">Địa chỉ *</label>
            <input class="in4_input" type="text" id="address" name="address" placeholder="Bạn chưa nhập địa chỉ"value="${user.address}" readonly>
        </div>
    </div>
    `,
        "Thông báo": `
      <h2>Thông báo</h2>
      
      <a href="../ct_Order/ct_Order.jsp" class="notification_item">
        <div class="avatar">
          <img src="image/balo_cam_trai.png" alt="Ảnh đại diện">
        </div>
        <div class="info">
          <h4>Đơn hàng giao thành công</h4>
          <p>Mã đơn hàng: hvshiodvosidjvpodjspc</p>
          <span>10/04/2025</span>
        </div>
      </a>
      
      <a href="../ct_Order/ct_Order.jsp" class="notification_item">
        <div class="avatar">
          <img src="image/balo_giu_nhiet.png" alt="Ảnh đại diện">
        </div>
        <div class="info">
          <h4>Đơn hàng giao thành công</h4>
          <p>Mã đơn hàng: hvshiodvosidjvpodjspc</p>
          <span>10/04/2025</span>
        </div>
      </a>
      
      <a href="../ct_Order/ct_Order.jsp" class="notification_item">
        <div class="avatar">
          <img src="image/balo_1_quai_đeo.png" alt="Ảnh đại diện">
        </div>
        <div class="info">
          <h4>Đơn hàng giao thành công</h4>
          <p>Mã đơn hàng: hvshiodvosidjvpodjspc</p>
          <span>10/04/2025</span>
        </div>
      </a>
    `,

        "Voucher": `
        <main>
            <div class="container" id="Voucher">
                <div class="tab-nav-menu">
                    <a href="#" class="tab-menu active" data-tab="all">Tất cả</a>
                    <a href="#" class="tab-menu" data-tab="free">Miễn phí vận chuyển</a>
                    <a href="#" class="tab-menu" data-tab="percent">Giảm theo %</a>
                    <a href="#" class="tab-menu" data-tab="value">Giảm theo giá trị</a>
                    <a href="#" class="tab-menu" data-tab="cancelled">Hủy</a>
                </div>
        
                <div class="container-contents">
        
                    <!-- Tất cả -->
                    <div class="product-content all active" id="all">
                        <div class="product-header">
                            <div class="product-header-title">Voucher</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">Hạn sử dụng</div>
                        </div>
        
                        <div class="product">
                            <div class="product-title">
                                <img src="image/vpucher_free_ship.png" alt="">
                            </div>
                            <div class="product-price">Miễn phí vận chuyển</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
        
                        <div class="product">
                            <div class="product-title">
                                <img src="image/voucer_percent.png" alt="">
                            </div>
                            <div class="product-price">Giảm 5% cho đơn trên 100k</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                        
                         <div class="product">
                            <div class="product-title">
                                <img src="image/voucher_50k.png" alt="">
                            </div>
                            <div class="product-price">Giảm 20.000đ cho đơn từ 150k</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                    </div>
        
                    <!-- Miễn phí vận chuyển -->
                    <div class="product-content free" id="free">
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">HSD</div>
                        </div>
        
                        <div class="product">
                            <div class="product-title">
                                <img src="image/vpucher_free_ship.png" alt="">
                            </div>
                            <div class="product-price">Miễn phí vận chuyển đơn từ 0đ</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                    </div>
        
                    <!-- Giảm theo % -->
                    <div class="product-content percent" id="percent">
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">HSD</div>
                        </div>
        
                        <div class="product">
                            <div class="product-title">
                                <img src="image/voucer_percent.png" alt="">
                            </div>
                            <div class="product-price">Giảm 5% cho đơn trên 100k</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                    </div>
        
                    <!-- Giảm theo giá trị -->
                    <div class="product-content value" id="value">
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Nội dung</div>
                            <div class="product-header-date">HSD</div>
                        </div>
        
                        <div class="product">
                            <div class="product-title">
                                <img src="image/voucher_50k.png" alt="">
                            </div>
                            <div class="product-price">Giảm 20.000đ cho đơn từ 150k</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                    </div>
        
                    <!-- Hủy -->
                    <div class="product-content cancelled" id="cancelled">
                        <img src="../images/nothingHere.jpg" alt="">
                    </div>
        
                </div>
            </div>
        </main>
        `,

        "Đơn hàng": `
        <main>
            <div class="container" id="Order">
                <div class="tab-nav-menu">
                    <a href="#" class="tab-menu active" data-tab="all">Tất cả</a>
                    <a href="#" class="tab-menu" data-tab="delivered">Đã giao</a>
                    <a href="#" class="tab-menu" data-tab="shipping">Đang giao</a>
                    <a href="#" class="tab-menu" data-tab="cancelled">Hủy</a>
                </div>
                <div class="container-contents">
                    <div class="product-content all active" id="all">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Sản phẩm</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-status">Tình trạng</div>
                            <div class="product-header-date">Ngày tạo</div>
                        </div>
                        <!--content-->
                        <div class="product">
                            <div class="product-title">
                                <img src="image/balo_1_quai_đeo.png" alt="">
                                <div class="product-info">
                                    <label>Name placeholder</label>
                                </div>
                            </div>
                            <div class="product-price">345.000 đ</div>
                            <div class="product-status-delivered">Đã giao</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                        <div class="product">
                            <div class="product-title">
                                <img src="image/balo_cam_trai.png" alt="">
                                <div class="wishlist-item-info">
                                    <label>Name placeholder</label>
                                </div>
                            </div>
                            <div class="product-price">345.000 đ</div>
                            <div class="product-status-delivered">Đã giao</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                        <div class="product">
                            <div class="product-title">
                                <img src="image/balo_du_lich_gon_nhe.png" alt="">
                                <div class="wishlist-item-info">
                                    <label>Name placeholder</label>
                                </div>
                            </div>
                            <div class="product-price">345.000 đ</div>
                            <div class="product-status-delivered">Đã giao</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                        <div class="product">
                            <div class="product-title">
                                <img src="image/balo_giu_nhiet.png" alt="">
                                <div class="wishlist-item-info">
                                    <label>Name placeholder</label>
                                </div>
                            </div>
                            <div class="product-price">345.000 đ</div>
                            <div class="product-status-shipping">Đang giao</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                    </div>
                    <!-- Delivered -->
                    <div class="product-content delivered" id="delivered">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-date">Ngày tạo</div>
                        </div>
                        <!--content-->
                        <div class="product">
                            <div class="product-title">
                                <img src="image/balo_giu_nhiet.png" alt="">
                                <div class="wishlist-item-info">
                                    <label>Name placeholder</label>
                                </div>
                            </div>
                            <div class="product-price">345.000 đ</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                        <div class="product">
                            <div class="product-title">
                                <img src="image/balo_du_lich_gon_nhe.png" alt="">
                                <div class="wishlist-item-info">
                                    <label>Name placeholder</label>
                                </div>
                            </div>
                            <div class="product-price">345.000 đ</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                    </div>
                    <!-- Shipping -->
                    <div class="product-content shipping" id="shipping">
                        <!--header-->
                        <div class="product-header">
                            <div class="product-header-title">Tiêu đề</div>
                            <div class="product-header-price">Đơn giá</div>
                            <div class="product-header-date">Ngày tạo</div>
                        </div>
                        <!--content-->
                        <div class="product">
                            <div class="product-title">
                                <img src="image/balo_chong_nuoc.png" alt="">
                                <div class="wishlist-item-info">
                                    <label>Name placeholder</label>
                                </div>
                            </div>
                            <div class="product-price">345.000 đ</div>
                            <div class="product-date">01/12/2025</div>
                        </div>
                    </div>
                    <div class="product-content cancelled" id="cancelled">
                        <img src="../images/nothingHere.jpg" alt="">
                    </div>
                </div>
            </div>
        </main>
    `,
        "Đổi mật khẩu": `
      <h2>Đổi mật khẩu</h2>
      <div class="in4">
        <div class="in4_row">
          <label class="in4_label" for="old_password">Mật khẩu cũ</label>
          <div class="password-wrapper">
            <input class="in4_input" id="old_password" type="password" name="old_password">
            <button type="button" id="toggleOldPassword">👁</button>
          </div>
        </div>
    
        <div class="in4_row">
          <label class="in4_label" for="password">Mật khẩu mới</label>
          <div class="password-wrapper">
            <input class="in4_input" id="password" type="password" name="password">
            <button type="button" id="togglePassword">👁</button>
          </div>
        </div>
    
        <div class="in4_row">
          <label class="in4_label" for="confirm_password">Xác nhận mật khẩu mới</label>
          <div class="password-wrapper">
            <input class="in4_input" id="confirm_password" type="password" name="confirm_password">
            <button type="button" id="toggleConfirmPassword">👁</button>
          </div>
        </div>
        <div class="in4_row">
            <button class="bt_xac_nhan" id="btn-doi-mk">Xác nhận</button>
        </div>
      </div>
            `,
        "Đăng xuất": `
            <h2>Đăng xuất</h2>
            <p>Bạn có chắc muốn đăng xuất không?</p>
            <button class="bt_xac_nhan" id="btn-dang-xuat">Đăng xuất</button>
     `
    };
    // Gán mặc định hiển thị "Thông tin tài khoản" khi load
    infoBox.innerHTML = contents["Thông tin tài khoản"];

    items.forEach(item => {
        item.addEventListener("click", (e) => {
            e.preventDefault();
            const text = item.textContent.trim();
            infoBox.innerHTML = contents[text] || "<p>Chưa có nội dung</p>";

            // Nếu người dùng bấm "Đổi mật khẩu" thì kích hoạt toggle
            if (text === "Đổi mật khẩu") {
                const password = document.getElementById('password');
                const confirmPassword = document.getElementById('confirm_password');
                const old_password = document.getElementById('old_password');

                const togglePassword = document.getElementById('togglePassword');
                const toggleConfirm = document.getElementById('toggleConfirmPassword');
                const toggleOld = document.getElementById('toggleOldPassword');

                const setToggle = (btn, input) => {
                    btn.addEventListener('mouseover', () => input.type = 'text');
                    btn.addEventListener('mouseout', () => input.type = 'password');
                };
                setToggle(togglePassword, password);
                setToggle(toggleConfirm, confirmPassword);
                setToggle(toggleOld, old_password);
            }

            if (text === "Đổi mật khẩu") {
                document.getElementById("btn-doi-mk").addEventListener("click", () => {
                    alert("Đổi mật khẩu thành công!");
                });
            }

            if (text === "Đơn hàng" || text === "Voucher") {
                const tabMenus = document.querySelectorAll('.tab-menu');
                const contents = document.querySelectorAll('.product-content');

                tabMenus.forEach(tab => {
                    tab.addEventListener('click', (e) => {
                        e.preventDefault();

                        tabMenus.forEach(t => t.classList.remove('active'));
                        contents.forEach(c => c.classList.remove('active'));

                        tab.classList.add('active');
                        document.querySelector(`.${tab.dataset.tab}`).classList.add('active');
                    });
                });
            }

            if (text === "Đăng xuất") {
                document.getElementById("btn-dang-xuat").addEventListener("click", () => {
                    localStorage.removeItem("user");
                    alert("Đăng xuất thành công!");
                    window.location.href = "../dang_nhap/dang_nhap.html";
                });
            }
        });
    });
});

/* ======= button popup======= */

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



