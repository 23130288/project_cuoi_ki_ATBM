document.querySelectorAll(".add-to-cart").forEach(btn => {
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
                if (data.success) {
                    this.innerText = "Đã thêm ✔";
                    this.disabled = true;
                }
            })
            .catch(err => console.error(err));
    });
});