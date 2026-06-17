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
                    <input type="text" name="query" id="searchUserInput" placeholder="Tên người dung..."/>
                    <button class="btn-search" onclick="handleUserSearch()"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            
            <div class="table-wrapper">
                <table class="table_data" id="userTable">
<!--                  load nội dung bằng hàm riêng-->
                </table>
            </div>
        `,

        "Quản lý Khóa": `
            <h2>Quản lý và tạo khóa</h2>
                <div class="Key-filter">
                    <label>
                        <input type="radio" name="keyFilter" checked onchange="loadkeyList('all')">Tất cả
                    </label>
                    
                    <label style="margin-left:20px">
                        <input type="radio" name="keyFilter" onchange="loadkeyList('processing')">Chưa trả lời
                    </label>
                </div>

                <div id="keyList">
                      <!-- notification_item sẽ được JS đổ vào đây -->
                </div>
        `,

        "Quản lý đơn hàng": `
            <h2>Quản lý đơn hàng</h2>
            <div class="Menu-bar">
                <div class="search-bar">
                    <input type="text" name="query" id="searchOrderInput" placeholder="Mã đơn hàng..."/>
                    <button class="btn-search" onclick="handleOrderSearch()"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            
            <div class="table-wrapper">
                <table class="table_data" id="OrderTable">
<!--                  load nội dung bằng hàm riêng-->
                </table>
            </div>     
        `,

        "Quản lý voucher": `
            <h2>Quản lý voucher</h2>
            <div class="Menu-bar">
                <button class="bt_menu" id="btn_them_v">+ Thêm voucher</button>
            </div>
            
             <div class="table-wrapper">
                <table class="table_data" id="voucherTable">
<!--                  load nội dung bằng hàm riêng-->
                </table>
            </div>   
        `,

        "Quản lý dịch vụ, chính sách": `
            <h2>Quản lý dịch vụ, chính sách</h2>
            <div class="Menu-bar">
                <button class="bt_menu" id="btn_them_sp">+ Thêm dịch vụ, chính sách</button>
            </div>
               
            <div class="table-wrapper">
                <table class="table_data" id="Service_PolicyTable">
<!--                  load nội dung bằng hàm riêng-->
                </table>
            </div>  
        `,

        "Quản lý thông báo": `
            <h2>Quản lý đơn hàng</h2>
            <div class="Menu-bar">                
                <div class="search-bar">
                    <input type="text" name="query" id="searchNotificationInput"  placeholder="Tên sản phẩm..."/>
                    <button class="btn-search" onclick="handleNotificationSearch()"><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            <div class="table-wrapper">
                <table class="table_data" id="NotificationTable">
<!--                  load nội dung bằng hàm riêng-->
                </table>
            </div> 
        `,

        "Trả lời câu hỏi": `
            <h2>Các câu hỏi</h2>
                <div class="support-filter">
                    <label>
                        <input type="radio" name="supportFilter" checked onchange="loadSupportList('all')">Tất cả
                    </label>
                    
                    <label style="margin-left:20px">
                        <input type="radio" name="supportFilter" onchange="loadSupportList('processing')">Chưa trả lời
                    </label>
                </div>

                <div id="supportList">
                      <!-- notification_item sẽ được JS đổ vào đây -->
                </div>
        `,

            "Thống kê báo cáo": `
            <h2>Thống kê báo cáo</h2>
            <div id="report_statistics">
                <p>Đang tải dữ liệu...</p>
            </div>
            `,

        "Đăng xuất": `
            <h2>Đăng xuất</h2>
            <p>Bạn có chắc muốn đăng xuất khỏi trang quản trị?</p>
            <form method="post" action="log-out">
                <button class="bt_xac_nhan" id="btn-dang-xuat">Đăng xuất</button>
            </form>     `
    };

    // Hiển thị mặc định
    infoBox.innerHTML = contents["Quản lý sản phẩm"];
    handleProductSearch();

    // Xử lý menu click
    items.forEach(item => {
        item.addEventListener("click", () => {
            const text = item.textContent.trim();
            infoBox.innerHTML = contents[text] || "<p>Chưa có nội dung</p>";
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
                handleUserSearch();
            }

            if (text === "Quản lý Khóa") {
                loadkeyList("all");
            }

            if (text === "Quản lý đơn hàng") {
                handleOrderSearch();
            }

            if (text === "Quản lý voucher") {
                loadVoucherList();

                const btnThem = document.getElementById("btn_them_v");
                if (btnThem) btnThem.addEventListener("click", () => {
                    addVoucher()
                });
            }

            if (text === "Quản lý thông báo") {
                handleNotificationSearch();
            }

            if (text === "Quản lý dịch vụ, chính sách") {
                loadService_PolicyList();

                const btnThem = document.getElementById("btn_them_sp");
                if (btnThem) btnThem.addEventListener("click", () => {
                    addService_Policy()
                });
            }

            if (text === "Trả lời câu hỏi") {
                loadSupportList("all");
            }

            if (text === "Thống kê báo cáo") {
                getReportStatistics();
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
        if (onConfirm) onConfirm(() => {
            popup.style.display = "none";
        });
    };
}

// document.addEventListener("DOMContentLoaded", function () {
//     const btnDoiThongTin = document.getElementById("btn-doi-thong-tin");
//     if (btnDoiThongTin) {
//         btnDoiThongTin.addEventListener("click", () => {
//             // language=HTML format=false
//             openAdminPopup(
//                 "Thay đổi thông tin",
//                 `
//                 <div class="popup_item">
//                     <label>Tên tài khoản</label>
//                     <input class="in4_input" type="text" id="name" value="Nguyễn Văn A" readonly>
//                 </div>
//
//                 <div class="popup_item">
//                     <label>Avata</label>
//                     <div class="popup_avata">
//                         <div class="preview-item">
//                             <img src="admin/image/avatar.jpg">
//                         </div>
//
//                         <div class="img-upload-box" id="drop-zone">
//                             <span>+</span>
//                             <p>Kéo hoặc click để thêm ảnh</p>
//                         </div>
//                     </div>
//                 </div>
//                 `,
//                 () => {
//                     alert("Đã thay đổi thông tin thành công!");
//                 }
//             );
//         });
//     }
// });

//================================= hàm hỗ trợ ajax-json =================================
//================================= các phương thước phần user =================================
function handleUserSearch() {
    const keyword = document.getElementById("searchUserInput").value.trim();
    loadUsers(keyword);
}

function loadUsers(keyword = "") {
    let url = '/projectWeb_war/admin/users';

    if (keyword !== "") {
        url = `/projectWeb_war/admin/user_Sreach?keyword=${encodeURIComponent(keyword)}`;
    }

    fetch(url)
        .then(res => res.json())
        .then(users => {
            //check rỗng
            if (!users || users.length === 0) {
                alert("Không tìm thấy người nào.");
                loadUsers("");
                return;
            }
            printUserTable(users);
        })
        .catch(err => console.error(err));
}

function printUserTable(users) {
    const table = document.getElementById("userTable");

    //xóa bảng cũ
    table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Email</th>
            <th>Trạng thái</th>
            <th>Vai trò</th>
            <th>Thao tác</th>
        </tr>
    `;

    users.forEach(u => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${u.uid}</td>
            <td>${u.name}</td>
            <td>${u.email}</td>
            <td>${u.status ? "Hoạt động" : "Bị khóa"}</td>
            <td>
                <button onclick="UserRoleChange(${u.uid})">${u.role}</button>
            </td>
            <td>
                <button onclick="toggleUserStatus(${u.uid})">${u.status ? "Khóa" : "Mở"}</button>
            </td>
        `;
        table.appendChild(row);
    });
}

function UserRoleChange(uid) {
    if (!confirm("Bạn có chắc muốn thay đổi vai trò user này?")) {
        return;
    }

    fetch(`/projectWeb_war/admin/user/toggle_user_Role?uid=${uid}`, {
        method: "POST"
    })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            handleUserSearch(); // load lại bảng
        })
        .catch(err => {
            console.error(err);
            alert("Có lỗi xảy ra");
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
            handleUserSearch(); // load lại bảng
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
                            <option value="normal">Đang bán</option>
                            <option value="hot">Bán chạy</option>
                            <option value="canceled">Dừng bán</option>
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

                // language=HTML
                div.innerHTML = `
                    <img src="${URL.createObjectURL(file)}" alt="">
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
                <span class="file-name">${file.name}</span>
            `;

                previewBox.appendChild(div);
            });
        });
    }, 0);
}

function addProductVariant(pid, name) {
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
                    <select id="p_size">
                        <option value="S">S</option>
                        <option value="M">M</option>
                        <option value="L">L</option>
                        <option value="XL">XL</option>
                        <option value="XXL">XXL</option>
                    </select>
                </div>
                
                <div class="popup_item">
                    <label>Màu sắc:</label>
                    <select id="p_color">
                        <option value="red">Đỏ</option>
                        <option value="orange">Cam</option>
                        <option value="yellow">Vàng</option>
                        <option value="green">Xanh lá</option>
                        <option value="blue">Xanh dương</option>
                        <option value="indigo">Chàm</option>
                        <option value="violet">Tím</option>
                        <option value="white">Trắng</option>
                        <option value="black">Đen</option>
                        <option value="grey">Xám</option>
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
            <td>${p.TotlaProduct}</td>
            <td>${p.producer}</td>
            <td>${p.status}</td>
            <td>
                <button onclick="addProductVariant(${p.pid}, '${p.name}')">+</button>
                <button onclick="editProduct(${p.pid})">Sửa</button>
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
            <td>${formatVND(pv.price)}</td>
            <td>${pv.quantity}</td>
            <td>
                <button onclick="editProductVariant(${pv.pvid}, '${pv.productName}', '${pv.size}', 
                '${pv.color}', ${pv.price}, ${pv.quantity})">Sửa
                </button>
            </td>
        `;
        table.appendChild(row);
    });
}

function editProduct(pid) {
    fetch(`/projectWeb_war/admin/product_edit?pid=${pid}`)
        .then(res => res.json())
        .then(data => {
            openAdminPopup(
                "Cập nhật sản phẩm",
                `
                <div class="popup_item">
                    <label>Tên sản phẩm:</label>
                    <input type="text" id="p_name">
                </div>

                <div class="popup_item">
                    <label>Loại:</label>
                    <select id="p_type">
                        option value="Balo">Balo</option>
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
                        <option value="hợp kim">hợp kim</option>
                        <option value="coston">coston</option>
                    </select>
                </div>

                <div class="popup_item">
                    <label>Nhà cung cấp:</label>
                    <input type="text" id="p_producer">
                </div>

                <div class="popup_item">
                    <label>Trạng thái:</label>
                    <select id="p_status">
                        <option value="normal">Đang bán</option>
                        <option value="hot">Bán chạy</option>
                        <option value="canceled">Dừng bán</option>
                    </select>
                </div>
                    
                <div class="popup_item">
                    <label>Mô tả:</label>
                    <textarea id="p_description"></textarea>
                </div>

                <div class="popup_item">
                    <label>Ảnh sản phẩm:</label>

                    <div class="img-upload-box">
                        <span>+</span>
                        <p>Chọn ảnh mới nếu muốn thay thế</p>
                        <input type="file" id="sp-img" accept="image/*" multiple>
                    </div>

                    <div class="preview-list" id="preview-list"></div>
                </div>
                `,
                function () {
                    const name = document.getElementById("p_name").value.trim();
                    const type = document.getElementById("p_type").value;
                    const style = document.getElementById("p_style").value;
                    const material = document.getElementById("p_material").value;
                    const producer = document.getElementById("p_producer").value.trim();
                    const status = document.getElementById("p_status").value;
                    const description = document.getElementById("p_description").value;
                    const imgInput = document.getElementById("sp-img");
                    const files = imgInput.files;

                    if (name === "") {
                        alert("Vui lòng nhập tên sản phẩm");
                        return;
                    }
                    if (producer === "") {
                        alert("Vui lòng nhập nhà cung cấp");
                        return;
                    }

                    const formData = new FormData();
                    formData.append("pid", pid);
                    formData.append("name", name);
                    formData.append("type", type);
                    formData.append("style", style);
                    formData.append("material", material);
                    formData.append("producer", producer);
                    formData.append("status", status);
                    formData.append("description", description);

                    if (files && files.length > 0) {
                        Array.from(files).forEach(f => {
                            formData.append("images", f);
                        });
                    }

                    fetch("/projectWeb_war/admin/product_edit", {
                        method: "POST",
                        body: formData
                    })
                        .then(res => res.json())
                        .then(result => {
                            if (result.success) {
                                alert("Cập nhật thành công");
                                handleProductSearch();
                            } else {
                                alert(result.message || "Cập nhật thất bại");
                            }
                        })
                        .catch(err => {
                            console.error(err);
                            alert("Lỗi kết nối server");
                        });
                }
            );

            /* ===== ĐỔ DỮ LIỆU LÊN FORM ===== */
            const p = data.product;

            document.getElementById("p_name").value = p.name;
            document.getElementById("p_type").value = p.type;
            document.getElementById("p_style").value = p.style;
            document.getElementById("p_material").value = p.material;
            document.getElementById("p_producer").value = p.producer;
            document.getElementById("p_status").value = p.status;
            document.getElementById("p_description").value = p.description;

            const preview = document.getElementById("preview-list");
            preview.innerHTML = "";

            data.images.forEach(img => {
                const div = document.createElement("div");
                div.className = "preview-item";
                div.innerHTML = `<img src="${img.image}" alt="Đây là ảnh">`;
                preview.appendChild(div);
            });

            /* ===== PREVIEW ẢNH MỚI ===== */
            document.getElementById("sp-img").addEventListener("change", e => {
                preview.innerHTML = "";
                Array.from(e.target.files).forEach(file => {
                    const div = document.createElement("div");
                    div.className = "preview-item";
                    div.innerHTML = `<img src="${URL.createObjectURL(file)}" alt="Đây là ảnh">`;
                    preview.appendChild(div);
                });
            });
        });
}

function editProductVariant(pvid, productName, size, color, price, quantity) {
    openAdminPopup(
        "Cập nhật biến thể của " + productName,
        `
                <div class="popup_item">
                    <label>Kích thước:</label>
                    <input type="text" id="pv_size" readonly>            
                </div>

                <div class="popup_item">
                   <label>Màu sắc:</label>
                   <input type="text" id="pv_color" readonly>            
                </div>

                <div class="popup_item">
                    <label>Giá tiền:</label>
                    <input type="number" id="pv_price" min="0" step="1000">
                </div>

                <div class="popup_item">
                    <label>Số lượng sản phẩm:</label>
                    <input type="number" id="pv_quantity" min="0" step="1">
                </div>
                `,
        function () {
            const price = document.getElementById("pv_price").value || 0;
            const quantity = document.getElementById("pv_quantity").value || 0;

            const formData = new FormData();
            formData.append("pvid", pvid);
            formData.append("price", price);
            formData.append("quantity", quantity);

            fetch("/projectWeb_war/admin/product_variant_edit", {
                method: "POST",
                body: formData
            })
                .then(res => res.json())
                .then(result => {
                    if (result.success) {
                        alert("Cập nhật thành công");
                        handleProductVariantSearch();
                    } else {
                        alert(result.message || "Cập nhật thất bại");
                    }
                })
                .catch(err => {
                    console.error(err);
                    alert("Lỗi kết nối server");
                });
        }
    );
    /* ===== ĐỔ DỮ LIỆU LÊN FORM ===== */
    document.getElementById("pv_size").value = size;
    document.getElementById("pv_color").value = color;
    document.getElementById("pv_price").value = price;
    document.getElementById("pv_quantity").value = quantity;
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

//================================= các phương thước phần Voucher =================================
function loadVoucherList() {
    fetch('/projectWeb_war/admin/Voucher/voucher_load')
        .then(res => res.json())
        .then(vouchers => {
            const table = document.getElementById("voucherTable");

            // XÓA DỮ LIỆU CŨ
            table.innerHTML = `
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Giảm</th>
                <th>Điều kiện</th>
                <th>hiệu lực đến</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
            `;
            vouchers.forEach(v => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${v.vid}</td>
                    <td>${v.name}</td>
                    <td>${formatDiscount(v)}</td>
                    <td>${formatVND(v.condition)}</td>
                    <td>${v.expiredDate}</td>
                    <td>${v.status ? "Đang áp dụng" : "Ngừng áp dụng"}</td>
                    <td>
                        <button onclick="toggleVoucherStatus(${v.vid})">
                            ${v.status ? "Khóa" : "Mở"}
                        </button>
                    </td>
                `;
                table.appendChild(row);
            });
        });
}

function formatDiscount(v) {
    if (v.name === "phan_tram") {
        return (v.discount * 100.0) + "%";
    }
    if (v.name === "mien_phi_van_chuyen") {
        return "Miễn phí";
    }
    return formatVND(v.discount);
}

function addVoucher() {
    openAdminPopup(
        "Tạo voucher mới",
        `
            <label>Loại voucher:</label>
            <select id="v_type">
                <option value="giam_gia">Giảm giá (VNĐ)</option>
                <option value="phan_tram">Giảm theo %</option>
                <option value="mien_phi_van_chuyen">Miễn phí vận chuyển</option>
            </select>

            <label>Nội dung:</label>
            <div id="v_content_area"></div>

            <label>Ngày hết hạn:</label>
            <input type="date" id="v_expired">
        `,
        function () {
            /* ========= 1. LẤY DỮ LIỆU ========= */

            const type = document.getElementById("v_type").value;
            const expiredDate = document.getElementById("v_expired").value;

            let discount = null;
            let condition = null;

            if (type === "giam_gia") {
                discount = document.getElementById("amount")?.value;
                condition = document.getElementById("min")?.value;
            }

            if (type === "phan_tram") {
                discount = document.getElementById("percent")?.value;
                condition = document.getElementById("min")?.value;
            }

            if (type === "mien_phi_van_chuyen") {
                discount = 0.0;
                condition = document.getElementById("min")?.value;
            }

            /* ========= 2. VALIDATE ========= */

            if (!expiredDate) {
                alert("Vui lòng chọn ngày hết hạn");
                return;
            }

            // convert sang number để check
            const discountNum = discount !== null ? Number(discount) : null;
            const conditionNum = condition !== null ? Number(condition) : null;

            // check condition chung
            if (conditionNum === null || isNaN(conditionNum) || conditionNum < 0) {
                alert("Điều kiện áp dụng phải là số không âm");
                return;
            }

            if (type === "giam_gia") {
                if (discountNum === null || isNaN(discountNum) || discountNum <= 0) {
                    alert("Vui lòng nhập số tiền giảm hợp lệ (> 0)");
                    return;
                }
            }

            if (type === "phan_tram") {
                if (
                    discountNum === null ||
                    isNaN(discountNum) ||
                    discountNum < 0 ||
                    discountNum > 100
                ) {
                    alert("Phần trăm giảm phải nằm trong khoảng 1–100");
                    return;
                }
            }

            if (type === "mien_phi_van_chuyen") {
                // không cần check discount
                discount = 0;
            }

            /* ========= 3. GỬI AJAX ========= */
            fetch("/projectWeb_war/admin/Voucher_add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
                },
                body:
                    "type=" + encodeURIComponent(type) +
                    "&discount=" + encodeURIComponent(discount ?? 0) +
                    "&condition=" + encodeURIComponent(condition ?? 0) +
                    "&expiredDate=" + encodeURIComponent(expiredDate)
            })
                .then(res => res.json())
                .then(data => {
                    if (data.success) {
                        alert("Tạo voucher thành công");
                        loadVoucherList();
                    } else {
                        alert(data.message || "Tạo voucher thất bại");
                    }
                })
                .catch(err => {
                    console.error(err);
                    alert("Có lỗi xảy ra");
                });
        }
    );
    setTimeout(() => {
        const typeSelect = document.getElementById("v_type");
        const contentArea = document.getElementById("v_content_area");

        function updateContentInput() {
            const type = typeSelect.value;

            if (type === "giam_gia") {
                contentArea.innerHTML = `
                    <input type="number" id="amount" min="0" step="1000" placeholder="Số tiền giảm">
                    <input type="number" id="min" min="0" step="1000" placeholder="Áp dụng cho đơn từ (VD: 200000)">
                `;
            } else if (type === "phan_tram") {
                contentArea.innerHTML = `
                    <input type="number" id="percent" min="0" max="100" placeholder="Giảm (%)">
                    <input type="number" id="min" min="0" step="1000" placeholder="Áp dụng cho đơn từ">
                `;
            } else if (type === "mien_phi_van_chuyen") {
                contentArea.innerHTML = `
                    <input type="number" id="min" placeholder="Áp dụng cho đơn từ">
                `;
            }
        }

        typeSelect.addEventListener("change", updateContentInput);
        updateContentInput();
    }, 0);
}

function toggleVoucherStatus(vid) {
    if (!confirm("Bạn có chắc muốn thay đổi trạng thái voucher này?")) {
        return;
    }

    fetch(`/projectWeb_war/admin/voucher/toggle_voucher_status?vid=${vid}`, {
        method: "POST"
    })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            loadVoucherList();
        })
        .catch(err => {
            console.error(err);
            alert("Có lỗi xảy ra");
        });
}

//================================= các phương thước phần dịch vụ, chính sách =================================
function loadService_PolicyList() {
    fetch('/projectWeb_war/admin/Service_Policy/Service_Policy_load')
        .then(res => res.json())
        .then(Service_Policys => {
            const table = document.getElementById("Service_PolicyTable");
            table.innerHTML = `
                <tr>
                    <th>ID</th>
                    <th>Loại</th>
                    <th>Tiêu đề</th>
                    <th>Ngày đăng</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
                `;
            Service_Policys.forEach(sp => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${sp.spid}</td>
                    <td>${sp.isService ? "Dịch vụ" : "Chính sách"}</td>
                    <td>${sp.title}</td>
                    <td>${sp.lasted_update}</td>
                    <td>${sp.status ? "Đang áp dụng" : "Ngừng áp dụng"}</td>
                    <td>
                        <button onclick="toggleServicePolicyStatus(${sp.spid})">
                            ${sp.status ? "Khóa" : "Mở"}
                        </button>
                    </td>
                    `;
                table.appendChild(row);
            });
        });
}

function toggleServicePolicyStatus(spid) {
    if (!confirm("Bạn có chắc muốn thay đổi trạng thái của mục này?")) {
        return;
    }

    fetch(`/projectWeb_war/admin/Service_Policy/toggle_Service_Policy_status?spid=${spid}`, {
        method: "POST"
    })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            loadService_PolicyList();
        })
        .catch(err => {
            console.error(err);
            alert("Có lỗi xảy ra");
        });
}

function addService_Policy() {
    openAdminPopup(
        "Tạo dịch vụ, chính sách mới",
        `
           <label>Tên chính sách, dịch vụ:</label>
                 <input type="text" id="sp_title" placeholder="Nhập tiêu đề">

                 <label>Loại:</label>
                 <select id="sp_type">
                    <option value="0">Chính sách</option>
                    <option value="1">Dịch vụ</option>
                </select>

                 <label>Nội dung:</label>
                 <div class="popup_item">
                <label>Nhập nhập từ Word:</label>
                <div class="img-upload-box" id="excel-drop-zone">
                    <span>+</span>
                    <p>Kéo hoặc click để tải file Word (.xlsx)</p>
                    <input type="file" id="sp_word_file" accept=".doc,.docx" multiple>
                </div>
                <p style="font-size:13px; color:#666;">
                Chỉ được nhập 1 file word, file ko được chứa hình ảnh.
                </p>
                                
                 <!-- Khu vực preview -->
                <div class="excel-preview" id="sp_excel_preview_list">
                    <!-- file excel sẽ hiện ở đây -->
                </div>
            </div>
        `,
        function () {
            /* ===== 1. LẤY DỮ LIỆU ===== */
            const title = document.getElementById("sp_title").value.trim();
            const type = document.getElementById("sp_type").value;
            const file = document.getElementById("sp_word_file").files[0];

            /* ===== 2. VALIDATE ===== */
            if (!title) {
                alert("Vui lòng nhập tiêu đề");
                return;
            }

            if (!file) {
                alert("Vui lòng chọn file Word");
                return;
            }

            /* ===== 3. GỬI FORM ===== */
            const formData = new FormData();
            formData.append("title", title);
            formData.append("type", type);
            formData.append("contentFile", file);

            fetch("/projectWeb_war/admin/Service_Policy_add", {
                method: "POST",
                body: formData
            })
                .then(res => res.json())
                .then(data => {
                    if (data.success) {
                        alert("Tạo dịch vụ / chính sách thành công");
                        loadService_PolicyList();
                    } else {
                        alert(data.message || "Thất bại");
                    }
                })
                .catch(err => {
                    console.error(err);
                    alert("Có lỗi xảy ra");
                });
        }
    );
    setTimeout(() => {
        const excelInput = document.getElementById("sp_word_file");
        const previewBox = document.getElementById("sp_excel_preview_list");

        if (!excelInput || !previewBox) return;

        excelInput.addEventListener("change", () => {
            previewBox.innerHTML = "";

            Array.from(excelInput.files).forEach(file => {
                const div = document.createElement("div");
                div.className = "preview-item excel-preview";

                div.innerHTML = `
                <span class="file-name">${file.name}</span>
            `;

                previewBox.appendChild(div);
            });
        });
    }, 0);
}

//================================= các phương thước phần notification =================================
function handleNotificationSearch() {
    const keyword = document.getElementById("searchNotificationInput").value.trim();
    loadNotifications(keyword);
}

function loadNotifications(keyword = "") {
    let url = '/projectWeb_war/admin/Notification_load';

    if (keyword !== "") {
        url = `/projectWeb_war/admin/Notification_Sreach?keyword=${encodeURIComponent(keyword)}`;
    }

    fetch(url)
        .then(res => res.json())
        .then(Notifications => {
            //check rỗng
            if (!Notifications || Notifications.length === 0) {
                alert("Không tìm thông báo nào.");
                loadNotifications("");
                return;
            }
            printNotificationTable(Notifications);
        })
        .catch(err => console.error(err));
}

function printNotificationTable(notificatios) {
    const table = document.getElementById("NotificationTable");

    //xóa bảng cũ
    table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Đối tượng nhận</th>
            <th>Tiêu đề</th>
            <th>Nội dung</th>
            <th>Ngày đăng</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
    `;
    notificatios.forEach(n => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${n.nid}</td>
            <td>${n.type}</td>
            <td>${n.title}</td>
            <td>${n.content}</td>
            <td>${n.createdDate}</td>
            <td>${n.status ? "Còn hiệu lực" : "Hết hiệu lực"}</td>
            <td>
                <button onclick="toggleNotificationStatus(${n.nid})">${n.status ? "Khóa" : "Mở"}</button>
            </td>
        `;
        table.appendChild(row);
    });
}

function toggleNotificationStatus(nid) {
    if (!confirm("Bạn có chắc muốn thay đổi trạng thái thông báo này?")) {
        return;
    }

    fetch(`/projectWeb_war/admin/product/toggle_Notification_status?nid=${nid}`, {
        method: "POST"
    })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            handleNotificationSearch();
        })
        .catch(err => {
            console.error(err);
            alert("Có lỗi xảy ra");
        });
}

//================================= các phương thước phần order =================================
function handleOrderSearch() {
    const keyword = document.getElementById("searchOrderInput").value.trim();
    loadOrders(keyword);
}

function loadOrders(keyword = "") {
    let url = '/projectWeb_war/admin/Order_load';

    if (keyword !== "") {
        url = `/projectWeb_war/admin/Order_Sreach?keyword=${encodeURIComponent(keyword)}`;
    }

    fetch(url)
        .then(res => res.json())
        .then(Orders => {
            //check rỗng
            if (!Orders || Orders.length === 0) {

                alert("Không tìm thông báo nào.");
                loadOrders("");
                return;
            }
            printOrderTable(Orders);
        })
        .catch(err => console.error(err));
}

function printOrderTable(orders) {
    const table = document.getElementById("OrderTable");

    //xóa bảng cũ
    table.innerHTML = `
        <tr>
            <th>ID</th>
            <th>Khách hàng</th>
            <th>Tổng tiền</th>
            <th>Ngày tạo đơn</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
    `;
    orders.forEach(o => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${o.oid}</td>
            <td>${o.customer}</td>
            <td>${formatVND(o.totalPrice)}</td>
            <td>${o.createdDate}</td>
            <td>${o.status}</td>
            <td>
                <button onclick="viewOrder(${o.oid},'${o.customer}',${o.totalPrice},'${o.createdDate}','${o.status}')">Xem</button>
            </td>
`;
        table.appendChild(row);
    });
}

function viewOrder(oid, customer, totalPrice, createdDate, status) {
    // Tạm thời demo – sau sẽ fetch chi tiết theo oid
    openAdminPopup(
        "Chi tiết đơn hàng",
        `
        <div class="popup_item">
            <label>Mã đơn:</label>
            <input type="text" value="${oid}" readonly>
        </div>

        <div class="popup_item">
            <label>Khách hàng:</label>
            <input type="text" value="${customer}" readonly>
        </div>

        <div class="popup_item">
            <label>Tổng tiền:</label>
            <input type="text" value="${formatVND(totalPrice)}" readonly>
        </div>
        
        <div class="popup_item">
            <label>Ngày tạo đơn:</label>
            <input type="text" value="${createdDate}" readonly>
        </div>
        
        <div class="popup_item">
            <label>Trạng thái:</label>
            <input type="text" value="${status}" readonly>
        </div>

         <div class="popup_item">
            <label>Sản phẩm trong đơn:</label>
            <textarea id="order-products" readonly>Đang tải...</textarea>
        </div>
        `,
        () => {
        }
    );

    loadOrderProducts(oid);
}

function loadOrderProducts(oid) {
    fetch(`/projectWeb_war/admin/Product_Order_load?oid=${oid}`)
        .then(res => res.json())
        .then(products => {
            const textarea = document.getElementById("order-products");

            let content = "";
            products.forEach(p => {
                content += p.productName + " x" + p.quantity + " - " + formatVND(p.unitPrice * p.quantity) + "\n";
            });

            textarea.value = content || "Không có sản phẩm";
        })
        .catch(() => {
            document.getElementById("order-products").value = "Lỗi tải dữ liệu";
        });
}

function formatVND(value) {
    if (!value) value = 0;
    value = value.toString().replace(/[^\d]/g, "");
    return new Intl.NumberFormat("vi-VN").format(value) + " ₫";
}

//hàm hỗ trợ ajax-json
//load user
function loadSupportList(filter) {
    fetch(`/projectWeb_war/admin/Supports?filter=${filter}`)
        .then(res => res.json())
        .then(list => {
            const container = document.getElementById("supportList");
            container.innerHTML = ""; // xóa dữ liệu cũ

            list.forEach(s => {
                const div = document.createElement("div");
                div.className = "notification_item";
                div.dataset.spid = s.spid;

                div.onclick = () => openAdminPopup(
                    "Trả lời câu hỏi",
                    `
                        <div class="popup_item">
                            <label>chủ đề:</label>
                            <input type="text" value="${s.topic}" readonly>
                        </div>
                        
                        <div class="popup_item">
                            <label>Nội dung:</label>
                            <input type="text" value="${s.message}" readonly>
                        </div>
                
                         <div class="popup_item">
                            <label>Phần nhập câu trả lời:</label>
                            <textarea id="answerContent"></textarea>
                        </div>
                    `,
                    () => {
                        const content = document.getElementById("answerContent").value;

                        if (!content.trim()) {
                            alert("Vui lòng nhập nội dung trả lời");
                            return;
                        }

                        const form = new URLSearchParams();
                        form.append("spid", s.spid);
                        form.append("uid", s.uid);
                        form.append("message", content);

                        fetch("/projectWeb_war/admin/Supports", {
                            method: "POST",
                            body: form
                        })
                            .then(res => {
                                if (!res.ok) throw new Error("Gửi thất bại");
                                return res.json();
                            })
                            .then(() => {
                                alert("Đã gửi câu trả lời");
                                loadSupportList("all");
                            })
                            .catch(err => {
                                console.error(err);
                                alert("Có lỗi xảy ra");
                            });
                    }
                );

                div.innerHTML = `
                <div class="info">
                    <h4>${s.topic} | ${s.title}</h4>
                    <p>Mã câu hỏi: ${s.spid} | Người gửi: ${s.uid} | Trạng thái: ${s.status}</p>
                    <span>${s.createdDate}</span>
                </div>
                `;

                container.appendChild(div);
            });
        });
}

function getReportStatistics() {
    fetch("/projectWeb_war/admin/ReportStatistics")
        .then(response => {
            if (!response.ok) {
                throw new Error("Lỗi khi lấy thống kê");
            }
            return response.json();
        })
        .then(data => {
            renderReportStatistics(data);
        })
        .catch(error => {
            document.getElementById("report_statistics").innerHTML =
                "<p>Lỗi tải thống kê</p>";
            console.error(error);
        });
}

function renderReportStatistics(stats) {
    const container = document.getElementById("report_statistics");

    container.innerHTML = `
        <p><b>Tổng số đơn:</b> ${stats.totalOrders}</p>
        <p><b>Số đơn thành công:</b> ${stats.completedOrders}</p>
        <p><b>Sản phẩm bán chạy nhất:</b> ${stats.bestSellingProductName}</p>
        <p><b>Tổng số sản phẩm đã bán:</b> ${stats.totalProductsSold}</p>
        <p><b>Doanh thu tháng:</b> ${stats.monthlyRevenue.toLocaleString()} ₫</p>
        <p><b>Số khách hàng:</b> ${stats.totalCustomers}</p>
    `;
}
//key
function loadkeyList(filter) {
    fetch(`/projectWeb_war/admin/Keys?filter=${filter}`)
        .then(res => res.json())
        .then(list => {
            const container = document.getElementById("keyList");
            container.innerHTML = ""; // xóa dữ liệu cũ

            list.forEach(s => {
                const div = document.createElement("div");
                div.className = "notification_item";
                div.dataset.spid = s.spid;

                div.innerHTML = `
                   <div class="keyinfo">
                       <div>
                         <h4>${s.topic} | ${s.title}</h4>
                         <p>Mã câu hỏi: ${s.spid} | Người gửi: ${s.uid} | Trạng thái: ${s.status}</p>
                         <span>${s.createdDate}</span>
                       </div>
                
                    <button class="btn-create">Xác nhận</button>
                   </div>
                `;

                container.appendChild(div);
            });
        });
}