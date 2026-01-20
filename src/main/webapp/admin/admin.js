document.addEventListener("DOMContentLoaded", function () {
    const items = document.querySelectorAll(".menu_item .item");
    const infoBox = document.querySelector(".infomation");

    const contents = {
        "Quản lý sản phẩm": `
            <h2>Quản lý sản phẩm</h2>
            <div class="Menu-bar">
                <button class="bt_menu" id="btn_them_sp">+ Thêm sản phẩm</button>
                
                <div class="search-bar">
                    <input type="text" name="query" id="searchProductInput" placeholder="Tên sản phẩm..."/>
                    <button class="btn-search" onclick="handleProductSearch()"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            
            <div class="table-wrapper">
                <table class="table_data" id="productTable">
<!--                  load nội dung bằng hàm riêng-->
                </table>
            </div>         
        `,

        "Quản lý biến thể sản phẩm": `
            <h2>Quản lý biến thể sản phẩm</h2>
            <div class="Menu-bar">
                <button class="bt_menu" id="btn_them_pt_sp">+ Thêm biến thể</button>
                
                <div class="search-bar">
                     <input type="text" name="query" id="searchProductVariantInput" placeholder="Tên sản phẩm..."/>
                    <button class="btn-search" onclick="handleProductVariantSearch()"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            
            <div class="table-wrapper">
                <table class="table_data" id="productVariantsTable">
<!--                  load nội dung bằng hàm riêng-->
                </table>
            </div>
        `,

        "Quản lý người dùng": `
            <h2>Quản lý người dùng</h2>
            <div class="Menu-bar">                
                <div class="search-bar">
                    <input type="text" name="query" placeholder="Tên người dung..."/>
                    <button class="btn-search"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            
            <div class="table-wrapper">
                <table class="table_data" id="userTable">
<!--                  load nội dung bằng hàm riêng-->
                </table>
            </div>
        `,

        "Quản lý đơn hàng": `
            <h2>Quản lý đơn hàng</h2>
            <div class="Menu-bar">
                <div class="search-bar">
                    <input type="text" name="query" placeholder="Mã đơn hàng..."/>
                    <button class="btn-search"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            
            <div class="table-wrapper">
                <table class="table_data">
                    <tr><th>Mã đơn</th><th>Khách hàng</th><th>Tổng tiền</th><th>Trạng thái</th><th>Thao tác</th></tr>
                    <tr><td>DH001</td><td>Nguyễn Văn A</td><td>750.000₫</td><td>Đang giao</td>
                    <td><button class="btn-xem">Xem</button><button class="btn-huy">Hủy</button></td></tr>
                    <tr><td>DH002</td><td>Trần Thị B</td><td>1.500.000₫</td><td>Hoàn thành</td>
                    <td><button class="btn-xem">Xem</button></td></tr>
                </table>    
            </div>    
        `,

        "Quản lý voucher": `
            <h2>Quản lý voucher</h2>
            <div class="Menu-bar">
                <button class="bt_menu" id="btn-them-tb">+ Thêm voucher</button>
                
                <div class="search-bar">
                    <input type="text" name="query" placeholder="Tên voucher..."/>
                    <button class="btn-search"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
               
            <div class="table-wrapper"> 
                <table class="table_data">
                    <tr><th>Mã voucher</th><th>Đối tượng nhận</th><th>Loại voucher</th><th>Nội dung</th><th>Có hiệu lực đến</th></tr>
                    <tr><td>v001</td><td>all</td><td>giảm giá</td><td>giảm 100k cho đơn hàng trên 500k</td><td>15/11/2025</td>
                    <tr><td>v002</td><td>0001</td><td>giảm giá</td><td>giảm 5% cho đơn hàng trên 500k</td><td>15/11/2025</td>
                </table>
            </div>
        `,

        "Quản lý dịch vụ, chính sách": `
            <h2>Quản lý dịch vụ, chính sách</h2>
            <div class="Menu-bar">
                <button class="bt_menu" id="btn-them-tb">+ Thêm dịch vụ, chính sách</button>
                
                <div class="search-bar">
                    <input type="text" name="query" placeholder="Tên chính sách, dịch vụ..."/>
                    <button class="btn-search"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
               
            <div class="table-wrapper">     
                <table class="table_data">
                    <tr><th>Mã</th><th>Tiêu đề</th><th>Nội dung</th><th>Ngày tạo</th><th>Trạng thái</th><th>Thao tác</th></tr>
                    <tr><td>p001</td><td>chính sách mua hàng</td><td>chính sách ......</td><td>15/11/2025</td><td>đang áp dụng</td>
                    <td><button class="btn-sua" id="sua_cs_dv">Sửa</button>
                    <tr><td>p002</td><td>chính sách đổi trả</td><td>chính sách ......</td><td>15/11/2025</td><td>đang áp dụng</td>
                    <td><button class="btn-sua" id="sua_cs_dv">Sửa</button>
                    <tr><td>s001</td><td>dịch vụ tư vấn</td><td>dịch vụ ......</td><td>15/11/2025</td><td>đang áp dụng</td>
                    <td><button class="btn-sua" id="sua_cs_dv">Sửa</button>
                    <tr><td>s001</td><td>dịch vụ đổi cũ lấy mới</td><td>dịch vụ ......</td><td>5/12/2025</td><td>dừng áp dụng</td>
                    <td><button class="btn-sua" id="sua_cs_dv">Sửa</button>
                </table>
            </div>
        `,

        "Quản lý thông báo": `
            <h2>Quản lý đơn hàng</h2>
            <div class="Menu-bar">
                <button class="bt_menu" id="btn-them-tb">+ Thêm thông báo</button>
                
                <div class="search-bar">
                    <input type="text" name="query" placeholder="Tên sản phẩm..."/>
                    <button class="btn-search"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            <div class="table-wrapper">     
                <table class="table_data">
                    <tr><th>Mã thông báo</th><th>người nhận thông báo</th><th>loại thông báo</th><th>nội dung</th><th>ngày thông báo</th></tr>
                    <tr><td>tb001</td><td>0001</td><td>giảm giá</td><td>giảm 100k cho đơn hàng trên 500k</td><td>11/11/2025</td>
                    <tr><td>tb002</td><td>all</td><td>giảm giá</td><td>giảm 100k cho đơn hàng trên 500k</td><td>11/11/2025</td>
                </table>
            </div>
        `,

        "Trả lời câu hỏi": `
          <h2>Thông báo</h2>
         
          <div class="notification_item" id="selection_shop">
            <div class="info">
              <h4>Kiểm tra, hủy hoặc thay đổi đơn hàng</h4>
              <p>Mã câu hỏi:đueihoeoi. | người gửi: u0001.</p>
              <span>10/11/2025</span>
            </div>
          </div>
          
          <div class="notification_item">
            <div class="info">
              <h4>Đăng nhập/Đăng ký</h4>
              <p>Mã câu hỏi:đueihoeoi. | người gửi: u0002.</p>
    
              <span>10/04/2025</span>
            </div>
          </div>
          
          <div class="notification_item">
            <div class="info">
              <h4>Phương thức trả tiền</h4>
              <p>Mã câu hỏi:12345678. | người gửi: u0003.</p>
              <span>10/10/2025</span>
            </div>
          </div>
          
          <div class="notification_item" id="selection_shop">
            <div class="info">
              <h4>Câu hỏi khác</h4>
              <p>Mã đơn hàng:akwkofnv. | người gửi: u0001.</p>
              <span>10/11/2025</span>
            </div>
          </div>
        `,

        "Thống kê báo cáo": `
            <h2>Thống kê doanh thu</h2>
            <p>Doanh thu tháng này: <b>12.500.000₫</b></p>
            <p>Tổng đơn hàng: <b>58</b></p>
            <p>Sản phẩm bán chạy nhất: <b>Vali cao cấp</b></p>
        `,

        "Đăng xuất": `
            <h2>Đăng xuất</h2>
            <p>Bạn có chắc muốn đăng xuất khỏi trang quản trị?</p>
            <button class="bt_xac_nhan" id="btn_dang_xuat">Đăng xuất</button>
     `
    };

    // Gắn sự kiện cho nút Xóa, Hủy, Sửa, Xem
    function attachEvents() {
        document.querySelectorAll(".btn-xoa, .btn-huy").forEach(btn => {
            btn.addEventListener("click", () => {
                if (confirm("Bạn có chắc muốn xóa mục này không?")) {
                    alert("Đã xóa thành công!");
                }
            });
        });

        document.querySelectorAll("#them_sp").forEach(btn => {
            btn.addEventListener("click", () => {
                openAdminPopup(
                    "Thêm biến thể",
                    `
            <div class="popup_item">
                <label>Tên sản phẩm:</label>
                <input type="text" id="sp-name" value="Balo du lịch">
            </div>
            
            <div class="popup_item">
                <label>Màu:</label>
                <select id="sp-color">
                    <option value="red">đỏ</option>
                    <option value="yellow">vàng</option>
                </select>            
            </div>
            
            <div class="popup_item">
                <label>Size:</label>
                <select id="sp-size">
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                </select> 
            </div>
            
            <div class="popup_item">
                <label>Giá:</label>
                <input type="number" id="sp-price" value="500000">
            </div>
            
            <div class="popup_item">
                <label>Số lượng:</label>
                <input type="number" id="sp-quantity" value="5">
            </div>
            
            <div class="popup_item">
                <label>Trạng thái:</label>
                <select id="sp-Status">
                    <option value="đang bán">Đang bán</option>
                    <option value="bán chạy">Bán chạy</option>
                    <option value="dừng bán">Dừng bán</option>
                </select>
            </div>

            
            <div class="popup_item">
                <label>Ảnh sản phẩm:</label>
                <div class="img-upload-box" id="drop-zone">
                    <span>+</span>
                    <p>Kéo hoặc click để thêm ảnh</p>
                </div>
                
                <div class="preview-list" id="preview-list">
                    <div class="preview-item">
                        <img src="image/balo1.jpg" alt="Balo 1">
                    </div>
                    <div class="preview-item">
                        <img src="image/balo2.jpg" alt="Balo 2">
                    </div>
                </div>
            </div>
            `,
                    () => {
                        alert("✔ Sửa sản phẩm xong!");
                    }
                );
            });
        });

        document.querySelectorAll("#sua_sp").forEach(btn => {
            btn.addEventListener("click", () => {
                openAdminPopup(
                    "Sửa sản phẩm",
                    `
            <div class="popup_item">
                <label>Tên sản phẩm:</label>
                <input type="text" id="sp-name" value="Balo du lịch">
            </div>
            
            <div class="popup_item">
                <label>Loại:</label>
                <select id="sp-Tyoe">
                    <option value="Balo">Balo</option>
                    <option value="Vali">Vali</option>
                </select>            
            </div>
                
            <div class="popup_item">
                <label>Kiểu dáng:</label>
                <select id="sp-size">
                    <option value="túi đeo chéo">túi đeo chéo</option>
                    <option value="túi đeo bụng">túi đeo bụng</option>
                    <option value="túi sách">túi sách</option>
                </select> 
            </div>
          
            <div class="popup_item">
                <label>Chất liệu:</label>
                <select id="sp-Tyoe">
                    <option value="Balo">hợp kim</option>
                    <option value="Vali">coston</option>
                </select>            
            </div>
                
            <div class="popup_item">
                <label>Tên nhà cung cấp:</label>
                <input type="text" id="sp-name" placeholder="Nhập nhà cung cấp">
            </div>
                
            <div class="popup_item">
                <label>Trạng thái:</label>
                <select id="sp-Status">
                    <option value="đang bán">Đang bán</option>
                    <option value="bán chạy">Bán chạy</option>
                    <option value="dừng bán">Dừng bán</option>
                </select>
            </div>

            
            <div class="popup_item">
                <label>Ảnh sản phẩm:</label>
                <div class="img-upload-box" id="drop-zone">
                    <span>+</span>
                    <p>Kéo hoặc click để thêm ảnh</p>
                </div>
                
                <div class="preview-list" id="preview-list">
                    <div class="preview-item">
                        <img src="image/balo1.jpg" alt="Balo 1">
                    </div>
                    <div class="preview-item">
                        <img src="image/balo2.jpg" alt="Balo 2">
                    </div>
                </div>
            </div>
            `,
                    () => {
                        alert("✔ Sửa sản phẩm xong!");
                    }
                );
            });
        });

        document.querySelectorAll("#sua_bt").forEach(btn => {
            btn.addEventListener("click", () => {
                openAdminPopup(
                    "Sửa biến thể sản phẩm",
                    `
            <div class="popup_item">
                <label>Màu:</label>
                <select id="sp-color">
                    <option value="red">đỏ</option>
                    <option value="yellow">vàng</option>
                </select>            
            </div>
            
            <div class="popup_item">
                <label>Size:</label>
                <select id="sp-size">
                    <option value="M">M</option>
                    <option value="L">L</option>
                    <option value="XL">XL</option>
                </select> 
            </div>
            
            <div class="popup_item">
                <label>Giá:</label>
                <input type="number" id="sp-price" value="500000">
            </div>
            
            <div class="popup_item">
                <label>Số lượng:</label>
                <input type="number" id="sp-quantity" value="5">
            </div>
            
            <div class="popup_item">
                <label>Trạng thái:</label>
                <select id="sp-Status">
                    <option value="đang bán">Đang bán</option>
                    <option value="bán chạy">Bán chạy</option>
                    <option value="dừng bán">Dừng bán</option>
                </select>
            </div>
            `,
                    () => {
                        alert("✔ Sửa sản phẩm xong!");
                    }
                );
            });
        });

        document.querySelectorAll("#sua_cs_dv").forEach(btn => {
            btn.addEventListener("click", () => {
                openAdminPopup(
                    "Sửa chính sách, dịch vụ",
                    `
            <div class="popup_item">
                <label>Tên chính sách, dịch vụ:</label>
                <input type="text" id="cs_dv-name" value="chính sách mua hàng" readonly>
            </div>
            
            <div class="popup_item">
                <label>Loại:</label>
                <input type="text" id="cs_dv-type" value="chính sách" readonly>
            </div>
            
            <div class="popup_item">
                <label>Trạng thái:</label>
                <select id="sp-Status">
                    <option value="đang bán">Đang áp dụng</option>
                    <option value="dừng bán">Dừng áp dụng</option>
                </select>
            </div>
            
             <div class="popup_item">
                <label>Nội dung:</label>
                <textarea id="tb-content">đây là nội dung chính sách</textarea>
            </div>
            `,
                    () => {
                        alert("✔ Sửa sản phẩm xong!");
                    }
                );
            });
        });

        document.querySelectorAll(".btn-xem").forEach(btn => {
            btn.addEventListener("click", () => {
                openAdminPopup(
                    "Chi tiết đơn hàng",
                    `
            <div class="popup_item">
                <label>Mã đơn:</label>
                <input type="text" value="DH001" readonly>
            </div>
            
            <div class="popup_item">
                <label>Khách hàng:</label>
                <input type="text" value="Nguyễn Văn A" readonly>
            </div>
            
            <div class="popup_item">
                <label>Tổng tiền:</label>
                <input type="text" value="750.000₫" readonly>
            </div>
            
            <div class="popup_item">
                <label>Trạng thái:</label>
                <input type="text" value="Đang giao" readonly>
            </div>
            
            <div class="popup_item">
                <label>Sản phẩm trong đơn:</label>
                <textarea readonly>Balo du lịch x2 - 500.000₫
Vali cao cấp x1 - 1.200.000₫</textarea>
            </div>
            
            <div class="popup_item">
                <label>Ghi chú:</label>
                <input type="text" value="Không có" readonly>
            </div>
            `,
                    () => {
                        alert("✔ Đóng popup chi tiết đơn hàng");
                    }
                );
            });
        });

    }

    // Hiển thị mặc định
    infoBox.innerHTML = contents["Quản lý sản phẩm"];
    handleProductSearch();
    attachEvents();

    // Xử lý menu click
    items.forEach(item => {
        item.addEventListener("click", () => {
            const text = item.textContent.trim();
            infoBox.innerHTML = contents[text] || "<p>Chưa có nội dung</p>";

            if (text === "Đăng xuất") {
                document.getElementById("btn_dang_xuat").addEventListener("click", () => {
                    localStorage.removeItem("user");
                    alert("Đăng xuất thành công!");
                    window.location.href = "../dang_nhap/dang_nhap.jsp";
                });
            }

            if (text === "Quản lý sản phẩm") {
                handleProductSearch();

                const btnThem = document.getElementById("btn_them_sp");
                if (btnThem) btnThem.addEventListener("click", () => {
                    addProduct()
                });
            }

            if (text === "Quản lý biến thể sản phẩm") {
                handleProductVariantSearch();

                const btnThem = document.getElementById("btn_them_pt_sp");
                if (btnThem) btnThem.addEventListener("click", () => {
                    addProductVariantExcel()
                });
            }

            if (text === "Quản lý người dùng") {
                loadUserList();
            }

            if (text === "Quản lý voucher") {
                const btnThemTB = document.getElementById("btn-them-tb");
                if (btnThemTB) btnThemTB.addEventListener("click", () => {
                    openAdminPopup(
                        "Tạo voucher mới",
                        `
                <label>Đối tượng nhận voucher:</label>
                <select id="tb-target-type">
                    <option value="all">Tất cả</option>
                    <option value="specific">Cụ thể</option>
                </select>
                
                <!-- Ô nhập UID chỉ hiện khi chọn "specific" -->
                <div id="uid-box" style="display:none; margin-top: 5px;">
                    <label>Nhập UID (hoặc danh sách UID, cách nhau bằng dấu phẩy):</label>
                    <input type="text" id="tb-uid" placeholder="VD: 0001, 0002, 0003">
                </div>

                <label>Loại voucher:</label>
                <select id="tb-type">
                    <option value="giam_gia">Giảm giá (VNĐ)</option>
                    <option value="phan_tram">Giảm theo %</option>
                    <option value="mien_phi_van_chuyen">Miễn phí vận chuyển</option>
                </select>
                
                <label>Nội dung:</label>
                <div id="content-area">
                    <!-- Nội dung sẽ tự thay đổi bằng JS -->
                </div>
                
                <label>Ngày hết hạn:</label>
                <input type="date" id="tb-expired">

            `,
                        () => {
                            alert(`✔ Đã tạo voucher.`);
                        }
                    );
                });
            }

            if (text === "Quản lý thông báo") {
                const btnThemTB = document.getElementById("btn-them-tb");
                if (btnThemTB) btnThemTB.addEventListener("click", () => {
                    openAdminPopup(
                        "Tạo thông báo mới",
                        `
                <label>Đối tượng nhận thông báo:</label>
                <select id="tb-target-type">
                    <option value="all">Tất cả</option>
                    <option value="specific">Cụ thể</option>
                </select>
                
                <!-- Ô nhập UID chỉ hiện khi chọn "specific" -->
                <div id="uid-box" style="display:none; margin-top: 5px;">
                    <label>Nhập UID (hoặc danh sách UID, cách nhau bằng dấu phẩy):</label>
                    <input type="text" id="tb-uid" placeholder="VD: 0001, 0002, 0003">
                </div>

                <label>Loại thông báo:</label>
                <select id="tb-type">
                    <option value="item">Có sản phẩm mới</option>
                    <option value="voucher">Có voucher mới</option>
                    <option value="policy">Có chính sách hoặc dich vụ mới</option>
                    <option value="Order">Yêu cầu hủy đơn hàng</option>
                </select>

                <label>Nội dung:</label>
                <textarea id="tb-content" placeholder="Nhập nội dung thông báo"></textarea>
            `,
                        () => {
                            alert(`✔ Đã tạo thông báo cho UID: 12345`);
                        }
                    );
                });
            }

            if (text === "Quản lý dịch vụ, chính sách") {
                const btnThemTB = document.getElementById("btn-them-tb");
                if (btnThemTB) btnThemTB.addEventListener("click", () => {
                    openAdminPopup(
                        "Tạo thông báo mới",
                        `
                <label>Tên chính sách, dịch vụ:</label>
                <input type="text" id="cs_dv-name" placeholder="Nhập tiêu đề">
            
                <label>Loại:</label>
                <select id="tb-type">
                    <option value="chính sách">Chính sách</option>
                    <option value="dich_vu">Dịch vụ</option>
                </select>
            
                <label>Trạng thái:</label>
                <select id="sp-Status">
                    <option value="đang bán">Đang áp dụng</option>
                    <option value="dừng bán">Dừng áp dụng</option>
                </select>
            
                <label>Nội dung:</label>
                <textarea id="tb-content" placeholder="Nhập nội dung"></textarea>
            `,
                        () => {
                            alert(`✔ Đã Thêm chính sách, dịch vụ.`);
                        }
                    );
                });
            }

            if (text === "Trả lời câu hỏi") {
                const notiItems = document.querySelectorAll(".notification_item");
                notiItems.forEach(item => {
                    item.addEventListener("click", () => {
                        window.location.href = "../helpPage_admin/helpPage.html";
                    });
                });
            }

            attachEvents(); // gắn lại cho nút trong nội dung mới
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
        if (onConfirm) onConfirm(() => {
            popup.style.display = "none";
        });
    };


    // ======= XỬ LÝ VOUCHER =========
    const targetSelect = document.getElementById("tb-target-type");
    const uidBox = document.getElementById("uid-box");
    const typeSelect = document.getElementById("tb-type");
    const contentArea = document.getElementById("content-area");

    if (targetSelect && uidBox) {
        targetSelect.addEventListener("change", () => {
            uidBox.style.display = targetSelect.value === "specific" ? "block" : "none";
        });
    }

    function updateContentInput() {
        if (!typeSelect || !contentArea) return;

        const type = typeSelect.value;

        if (type === "giam_gia") {
            contentArea.innerHTML = `
                <input type="number" id="amount" placeholder="Số tiền giảm (VD: 50000)">
                <input type="number" id="min" placeholder="Áp dụng cho đơn hàng từ (VD: 200000)">
            `;
        } else if (type === "phan_tram") {
            contentArea.innerHTML = `
                <input type="number" id="percent" placeholder="Giảm (%) (VD: 10)">
                <input type="number" id="min" placeholder="Áp dụng cho đơn hàng từ (VD: 200000)">
            `;
        } else if (type === "mien_phi_van_chuyen") {
            contentArea.innerHTML = `
                <input type="number" id="min" placeholder="Miễn phí vận chuyển cho đơn từ (VD: 300000)">
            `;
        }
    }

    if (typeSelect) {
        typeSelect.addEventListener("change", updateContentInput);
        updateContentInput(); // gọi 1 lần khi mở popup
    }
}

document.addEventListener("DOMContentLoaded", function () {
    const btnDoiThongTin = document.getElementById("btn-doi-thong-tin");
    if (btnDoiThongTin) {
        btnDoiThongTin.addEventListener("click", () => {
            // language=HTML format=false
            openAdminPopup(
                "Thay đổi thông tin",
                `
                <div class="popup_item">
                    <label>Tên tài khoản</label>
                    <input class="in4_input" type="text" id="name" value="Nguyễn Văn A" readonly>
                </div>
                 
                <div class="popup_item"> 
                    <label>Avata</label>                  
                    <div class="popup_avata">
                        <div class="preview-item">
                            <img src="admin/image/avatar.jpg">
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

//hàm hỗ trợ ajax-json
//load user
function loadUserList() {
    fetch('/projectWeb_war/admin/users')
        .then(res => res.json())
        .then(users => {
            const table = document.getElementById("userTable");

            // XÓA DỮ LIỆU CŨ
            table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Email</th>
                <th>Vai trò</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
            `;
            users.forEach(u => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${u.uid}</td>
                    <td>${u.name}</td>
                    <td>${u.email}</td>
                    <td>${u.role}</td>
                    <td>${u.status ? "Hoạt động" : "Bị khóa"}</td>
                    <td>
                        <button onclick="toggleUserStatus(${u.uid})">
                            ${u.status ? "Khóa" : "Mở"}
                        </button>
                    </td>
                `;
                table.appendChild(row);
            });
        });
}

function toggleUserStatus(uid) {
    if (!confirm("Bạn có chắc muốn thay đổi trạng thái user này?")) {
        return;
    }

    fetch(`/projectWeb_war/admin/user/toggle_user_status?uid=${uid}`, {
        method: "POST"
    })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            loadUserList(); // load lại bảng
        })
        .catch(err => {
            console.error(err);
            alert("Có lỗi xảy ra");
        });
}

//================================= các phương thước phần product =================================
function addProduct() {
    openAdminPopup(
        "Thêm sản phẩm mới",
        `
                <div class="popup_item">
                    <label>Chế độ nhập:</label>
                    <div style="display:flex; gap:20px;">
                        <label>
                            <input type="radio" name="import_mode" value="manual" checked>
                            Nhập thủ công
                        </label>
                        <label>
                            <input type="radio" name="import_mode" value="excel">
                            Nhập từ Excel
                        </label>
                    </div>
                </div>
                
                <div id="manual_mode">
                    <div class="popup_item">
                        <label>Tên sản phẩm:</label>
                        <input type="text" id="p_name" placeholder="Nhập tên sản phẩm">
                    </div>
                    
                    <div class="popup_item">
                        <label>Loại:</label>
                        <select id="p_type">
                            <option value="Balo">Balo</option>
                            <option value="Vali">Vali</option>
                        </select>            
                    </div>
                    
                    <div class="popup_item">
                        <label>Kiểu dáng:</label>
                        <select id="p_style">
                            <option value="túi đeo chéo">túi đeo chéo</option>
                            <option value="túi đeo bụng">túi đeo bụng</option>
                            <option value="túi sách">túi sách</option>
                        </select> 
                    </div>
              
                    <div class="popup_item">
                        <label>Chất liệu:</label>
                        <select id="p_material">
                            <option value="Balo">hợp kim</option>
                            <option value="Vali">coston</option>
                        </select>            
                    </div>
                    
                    <div class="popup_item">
                        <label>Tên nhà cung cấp:</label>
                        <input type="text" id="p_producer" placeholder="Nhập nhà cung cấp">
                    </div>
                    
                    <div class="popup_item">
                        <label>Trạng thái:</label>
                        <select id="p_status">
                            <option value="đang bán">Đang bán</option>
                            <option value="bán chạy">Bán chạy</option>
                            <option value="dừng bán">Dừng bán</option>
                        </select>
                    </div>
                
                    <div class="popup_item">
                        <label>Mô tả:</label>
                        <textarea id="p_description"></textarea>
                    </div>
                
                    <div class="popup_item">
                        <label>Ảnh sản phẩm:</label>
                    
                        <!-- Khu vực kéo/thả -->
                        <div class="img-upload-box" id="drop-zone">
                            <span>+</span>
                            <p>Kéo hoặc click để thêm ảnh</p>
                            <input type="file" id="sp-img" accept="image/*" multiple>
                        </div>
                    
                        <!-- Khu vực preview nhiều ảnh -->
                        <div class="preview-list" id="preview-list">
                            <!-- ảnh được nhập sẽ hiện ở đây -->
                        </div>
                    </div>
                </div>
                
                <div id="excel_mode" style="display:none;">
                    <div class="popup_item">
                        <label>Nhập sản phẩm từ Excel:</label>
                        <div class="img-upload-box" id="excel-drop-zone">
                            <span>+</span>
                            <p>Kéo hoặc click để tải file Excel (.xlsx)</p>
                            <input type="file" id="excel_file" accept=".xlsx,.xls" multiple>
                        </div>
                        <p style="font-size:13px; color:#666;">
                            File Excel phải có các cột: name, type, style, material, producer, status, description
                        </p>
                        
                        <!-- Khu vực preview -->
                        <div class="excel-preview" id="excel_preview_list">
                            <!-- file excel sẽ hiện ở đây -->
                        </div>
                    </div>
                </div>

            `, function () {
            const mode = document.querySelector("input[name='import_mode']:checked").value;

            if (mode === "manual") {
                // ===== MODE NHẬP TAY =====
                const name = document.getElementById("p_name").value;
                const type = document.getElementById("p_type").value;
                const style = document.getElementById("p_style").value;
                const material = document.getElementById("p_material").value;
                const producer = document.getElementById("p_producer").value;
                const status = document.getElementById("p_status").value;
                const description = document.getElementById("p_description").value;
                const imgInput = document.getElementById("sp-img");
                const files = imgInput.files;
                //check dữ liệu nhập
                if ("" === name) {
                    alert("Vui lòng nhập tên Sản phẩm.");
                    return;
                }
                if ("" === producer) {
                    alert("Vui lòng nhập tên nhà cung cấp.");
                    return;
                }
                if (!files || files.length === 0) {
                    alert("Vui lòng chọn ít nhất 1 ảnh");
                    return;
                }
                const formData = new FormData();

                // dữ liệu sản phẩm
                formData.append("name", name);
                formData.append("type", type);
                formData.append("style", style);
                formData.append("material", material);
                formData.append("producer", producer);
                formData.append("status", status);
                formData.append("description", description);

                // ảnh
                Array.from(files).forEach(file => {
                    formData.append("images", file);
                });

                //gọi ajax
                fetch("/projectWeb_war/admin/product_add", {
                    method: "POST",
                    body: formData
                })
                    .then(res => res.json())
                    .then(data => {
                        if (data.success) {
                            alert("Thêm sản phẩm thành công");
                            handleProductSearch();
                        } else {
                            alert(data.message || "Thêm sản phẩm thất bại");
                        }
                    })
                    .catch(err => {
                        console.error(err);
                        alert("Không kết nối được server");
                    });

            } else {
                // ===== MODE EXCEL =====
                const fileInput = document.getElementById("excel_file");
                const file = fileInput.files[0];

                if (!file) {
                    alert("Vui lòng chọn file Excel");
                    return;
                }

                const formData = new FormData();
                formData.append("excelFile", file);

                fetch("/projectWeb_war/admin/product_excel_add", {
                    method: "POST",
                    body: formData
                })
                    .then(res => res.json())
                    .then(data => {
                        if (data.success) {
                            alert("Nhập sản phẩm từ Excel thành công");
                            handleProductSearch();
                        } else {
                            alert(data.message || "Nhập Excel thất bại");
                        }
                    })
                    .catch(err => {
                        console.error(err);
                        alert("Không kết nối được server");
                    });
            }
        });
    setTimeout(() => {
        const radios = document.querySelectorAll("input[name='import_mode']");
        const manualBox = document.getElementById("manual_mode");
        const excelBox = document.getElementById("excel_mode");

        radios.forEach(radio => {
            radio.addEventListener("change", () => {
                if (radio.value === "manual") {
                    manualBox.style.display = "block";
                    excelBox.style.display = "none";
                } else {
                    manualBox.style.display = "none";
                    excelBox.style.display = "block";
                }
            });
        });
    }, 0);
    setTimeout(() => {
        const excelInput = document.getElementById("excel_file");
        const previewBox = document.getElementById("excel_preview_list");

        if (!excelInput || !previewBox) return;

        excelInput.addEventListener("change", () => {
            previewBox.innerHTML = "";

            Array.from(excelInput.files).forEach(file => {
                const div = document.createElement("div");
                div.className = "preview-item excel-preview";

                div.innerHTML = `
                <span style="font-weight:600;">XLS</span>
                <span class="file-name">${file.name}</span>
            `;

                previewBox.appendChild(div);
            });
        });
    }, 0);

    setTimeout(() => {
        const imgInput = document.getElementById("sp-img");
        const previewImgBox = document.getElementById("preview-list");

        if (!imgInput || !previewImgBox) return;

        imgInput.addEventListener("change", () => {
            previewImgBox.innerHTML = "";

            Array.from(imgInput.files).forEach(file => {
                if (!file.type.startsWith("image/")) return;

                const div = document.createElement("div");
                div.className = "preview-item";

                div.innerHTML = `
                <img src="${URL.createObjectURL(file)}">
            `;

                previewImgBox.appendChild(div);
            });
        });
    }, 0);
}

function addProductVariantExcel() {
    openAdminPopup(
        "Thêm danh sách biến thể mới",
        `
            <div class="popup_item">
                <label>Nhập nhập từ Excel:</label>
                <div class="img-upload-box" id="excel-drop-zone">
                    <span>+</span>
                    <p>Kéo hoặc click để tải file Excel (.xlsx)</p>
                    <input type="file" id="pv_excel_file" accept=".xlsx,.xls" multiple>
                </div>
                <p style="font-size:13px; color:#666;">
                File Excel phải có các cột: pid, size, color, price, quantity
                </p>
                                
                 <!-- Khu vực preview -->
                <div class="excel-preview" id="pv_excel_preview_list">
                    <!-- file excel sẽ hiện ở đây -->
                </div>
            </div>

            `, function () {
            // ===== MODE EXCEL =====
            const fileInput = document.getElementById("pv_excel_file");
            const file = fileInput.files[0];

            if (!file) {
                alert("Vui lòng chọn file Excel");
                return;
            }

            const formData = new FormData();
            formData.append("excelFile", file);

            fetch("/projectWeb_war/admin/product_Variant_excel_add", {
                method: "POST",
                body: formData
            })
                .then(res => res.json())
                .then(data => {
                    if (data.success) {
                        alert("Nhập sản phẩm từ Excel thành công");
                        handleProductVariantSearch();
                    } else {
                        alert(data.message || "Nhập Excel thất bại");
                    }
                })
                .catch(err => {
                    console.error(err);
                    alert("Không kết nối được server");
                });

        });
    setTimeout(() => {
        const excelInput = document.getElementById("pv_excel_file");
        const previewBox = document.getElementById("pv_excel_preview_list");

        if (!excelInput || !previewBox) return;

        excelInput.addEventListener("change", () => {
            previewBox.innerHTML = "";

            Array.from(excelInput.files).forEach(file => {
                const div = document.createElement("div");
                div.className = "preview-item excel-preview";

                div.innerHTML = `
                <span style="font-weight:600;">XLS</span>
                <span class="file-name">${file.name}</span>
            `;

                previewBox.appendChild(div);
            });
        });
    }, 0);
}

function addProductVariant(pid, name, type) {
    openAdminPopup(
        "Thêm biến thể cho " + name,
        `
                <div class="popup_item">
                    <label>Mã sản phẩm:</label>
                    <input type="text" id="p_pid" value="${pid}" readonly>
                </div>
                
                <div class="popup_item">
                    <label>Tên sản phẩm:</label>
                    <input type="text" id="p_name" value="${name}" readonly>
                </div>
                
                 <div class="popup_item">
                    <label>Kích thước:</label>
                    <select id="p_size"></select>
                </div>
                
                <div class="popup_item">
                    <label>Màu sắc:</label>
                    <select id="p_color">
                        <option value="black">Đen</option>
                        <option value="white">Trắng</option>
                        <option value="gray">Xám</option>
                        <option value="navy">Xanh navy</option>
                        <option value="blue">Xanh dương</option>
                        <option value="green">Xanh lá</option>
                        <option value="brown">Nâu</option>
                        <option value="beige">Be</option>
                        <option value="red">Đỏ</option>
                        <option value="yellow">Vàng</option>
                    </select> 
                </div>
          
                <div class="popup_item">
                    <label>Giá:</label>
                    <input type="number" id="p_price" placeholder="VD: 850000" min="0" step="1000">
                </div>
                
                <div class="popup_item">
                    <label>Số lượng:</label>
                    <input type="number" id="p_quantity" placeholder="VD: 10" min="0" step="1">
                </div>
                                
            `, function () {
            // 1. LẤY DỮ LIỆU NGƯỜI DÙNG
            const pid = document.getElementById("p_pid").value;
            const size = document.getElementById("p_size").value;
            const color = document.getElementById("p_color").value;
            const price = document.getElementById("p_price").value;
            const quantity = document.getElementById("p_quantity").value;

            // 2. GỬI AJAX POST
            fetch("/projectWeb_war/admin/productVariant_add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
                },
                body:
                    "pid=" + encodeURIComponent(pid) +
                    "&size=" + encodeURIComponent(size) +
                    "&color=" + encodeURIComponent(color) +
                    "&price=" + encodeURIComponent(price) +
                    "&quantity=" + encodeURIComponent(quantity)
            }).then(res => res.json())
                .then(data => {
                    if (data.success) {
                        alert("Thêm biến thể thành công");
                        handleProductVariantSearch();
                    } else {
                        alert(data.message || "Thêm biến thể thất bại");
                    }
                })
                .catch(err => {
                    console.error(err);
                    alert("Lỗi đã sảy ra.");
                });
        });
    loadSizeByType(type);
}

function loadSizeByType(type) {
    const sizeSelect = document.getElementById("p_size");

    if (type === "balo") {
        sizeSelect.innerHTML = `
            <option value="S">S (Nhỏ)</option>
            <option value="M">M (Trung)</option>
            <option value="L">L (Lớn)</option>
        `;
    } else if (type === "vali") {
        sizeSelect.innerHTML = `
            <option value="20">20 inch (Xách tay)</option>
            <option value="24">24 inch (Trung)</option>
            <option value="28">28 inch (Lớn)</option>
        `;
    }
}

function handleProductSearch() {
    const keyword = document.getElementById("searchProductInput").value.trim();
    loadProducts(keyword);
}

function handleProductVariantSearch() {
    const keyword = document.getElementById("searchProductVariantInput").value.trim();
    loadProductsVariant(keyword);
}

function loadProducts(keyword = "") {
    let url = '/projectWeb_war/admin/product_load';

    if (keyword !== "") {
        url = `/projectWeb_war/admin/product_Sreach?keyword=${encodeURIComponent(keyword)}`;
    }

    fetch(url)
        .then(res => res.json())
        .then(products => {
            //check rỗng
            if (!products || products.length === 0) {
                alert("Không tìm sản phẩm nào.");
                loadProducts("");
                return;
            }
            printProductTable(products);
        })
        .catch(err => console.error(err));
}

function loadProductsVariant(keyword = "") {
    let url = '/projectWeb_war/admin/Product_Variant_load';

    if (keyword !== "") {
        url = `/projectWeb_war/admin/Product_Variant_Sreach?keyword=${encodeURIComponent(keyword)}`;
    }

    fetch(url)
        .then(res => res.json())
        .then(productVariants => {
            //check rỗng
            if (!productVariants || productVariants.length === 0) {
                alert("Không tìm thấy biến thể nào.");
                loadProductsVariant("");
                return;
            }
            printproductVariantsTable(productVariants);
        })
        .catch(err => console.error(err));
}

function printProductTable(products) {
    const table = document.getElementById("productTable");

    //xóa bảng cũ
    table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Loại</th>
            <th>Kiểu dáng</th>
            <th>Chất liệu</th>
            <th>Còn lại</th>
            <th>NCC</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
    `;

    products.forEach(p => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${p.pid}</td>
            <td>${p.name}</td>
            <td>${p.type}</td>
            <td>${p.style}</td>
            <td>${p.material}</td>
            <td>0</td>
            <td>${p.producer}</td>
            <td>${p.status}</td>
            <td>
                <button onclick="addProductVariant(${p.pid}, '${p.name}', '${p.type}')">+</button>
                <button>Sửa</button>
                <button onclick="toggleProductStatus(${p.pid})">${p.status}</button>
            </td>
        `;
        table.appendChild(row);
    });
}

function printproductVariantsTable(productVariants) {
    const table = document.getElementById("productVariantsTable");

    // XÓA DỮ LIỆU CŨ
    table.innerHTML = `
            <tr>
                <th>PVID</th>
                <th>PID</th>
                <th>Tên</th>
                <th>Kích thước</th>
                <th>Màu sắc</th>
                <th>Giá</th>
                <th>Còn lại</th>
                <th>Thao tác</th>
            </tr>
            `;

    productVariants.forEach(pv => {
        const row = document.createElement("tr");
        // language=HTML
        row.innerHTML = `
            <td>${pv.pvid}</td>
            <td>${pv.pid}</td>
            <td>${pv.productName}</td>
            <td>${pv.size}</td>
            <td>${pv.color}</td>
            <td>${pv.price}</td>
            <td>${pv.quantity}</td>
            <td>
                <button onclick="">Sửa</button>
            </td>
        `;
        table.appendChild(row);
    });
}

function toggleProductStatus(pid) {
    if (!confirm("Bạn có chắc muốn thay đổi trạng thái product này?")) {
        return;
    }

    fetch(`/projectWeb_war/admin/product/toggle_product_status?pid=${pid}`, {
        method: "POST"
    })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            handleProductSearch();
        })
        .catch(err => {
            console.error(err);
            alert("Có lỗi xảy ra");
        });
}
