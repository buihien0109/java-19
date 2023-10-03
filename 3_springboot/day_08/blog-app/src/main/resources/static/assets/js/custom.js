jQuery(document).ready(function ($) {
    "use strict";

    $(window).scroll(function () {
        let scroll = $(window).scrollTop();
        let box = $('.header-text').height();
        let header = $('header').height();

        if (scroll >= box - header) {
            $("header").addClass("background-header");
        } else {
            $("header").removeClass("background-header");
        }
    });
});

// Active menu
const activeMenu = () => {
    const menuEls = document.querySelectorAll("#navbarResponsive > li");
    let path = window.location.pathname;

    menuEls.forEach(menu => {
        const menuItemEls = menu.querySelectorAll(".nav-treeview a");
        menuItemEls.forEach(menuItem => {
            if(menuItem.getAttribute("href") === path) {
                menuItem.classList.add("active");
                menu.firstElementChild.classList.add("active")
                menu.classList.add("menu-is-opening", "menu-open")
            }
        })
    })
}

activeMenu();
