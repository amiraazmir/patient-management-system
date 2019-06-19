/**
 * side navigation javascript
 */
    var openMenu = true;

    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
    }

    function closeMenu() {
        if (openMenu)
            openNav();
        else
            closeNav();
        openMenu = !openMenu;
    }
    