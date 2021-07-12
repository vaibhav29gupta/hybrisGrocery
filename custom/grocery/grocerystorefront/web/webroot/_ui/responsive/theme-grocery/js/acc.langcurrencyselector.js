ACC.langcurrencyselector = {
    _autoload: ["bindLangCurrencySelector", "bindSelectBoxes"],

    bindLangCurrencySelector: function () {
        $("#lang-selector").change(function () {
            $("#lang-form").submit();
        });

        $("#currency-selector").change(function () {
            $("#currency-form").submit();
        });
        $("#country-selector").change(function () {
            $("#country-form").submit();
        });
    },

    bindSelectBoxes: function () {
        for (const dropdown of document.querySelectorAll(".js-language-select-wrapper")) {
            dropdown.addEventListener("click", function () {
                this.querySelector(".js-language-select").classList.toggle("open");
            });
        }

        for (const option of document.querySelectorAll(".js-language-custom-option")) {
            option.addEventListener("click", function () {
                if (!this.classList.contains("selected") && !this.classList.contains("disabled")) {
                    this.closest(".js-language-select").querySelector(
                        ".js-language-select__trigger span"
                    ).textContent = this.textContent;
                    this.closest(".js-language-select").querySelector(".set-value-to-input").value = this.getAttribute(
                        "data-value"
                    );
                    $("#country-form").submit();
                }
            });
        }

        window.addEventListener("click", function (e) {
            for (const select of document.querySelectorAll(".js-language-select")) {
                if (!select.contains(e.target)) {
                    select.classList.remove("open");
                }
            }
        });
    },
};
