/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




$(document).ready(function () {

    $.get('./ServletConsultarUsuarioIniciado', function (response) {
        console.log(response);
        if (response === 'null') {
            console.log(response);
            $(location).attr('href', "sesion.html");
        } else {


        }
    });
});

$('#aSupermercados').click(
        function (e) {

            e.preventDefault();
            // var frm=new FormData();

            $.get('./ServletValidarUsuario ',
                    function (datos)
                    {
                        var d = datos;

                        if (d === "si") {
                            $(location).attr('href', "supermercado/supermercado.html");
                        } else {
                            alert("No tienes permisos para ingresar a este catalogo")
                        }

                    }
            );
        }
);
//$('#aListaArticulos').click(
//        function (e) {
//
//            e.preventDefault();
//            // var frm=new FormData();
//
//            $.get('./ServletValidarUsuario ',
//                    function (datos)
//                    {
//                        var d = datos;
//
//                        if (d === "No") {
//                            $(location).attr('href', "CatalogoTipoUsuarios/TipoUsuarios.jsp");
//                        } else {
//                            alert("No tienes permisos para ingresar a este catalogo");
//                        }
//
//                    }
//            );
//        }
//);

$('#aCerrarSesion').click(
        function (e) {

            e.preventDefault();
            // var frm=new FormData();

            $.post('./ServletCerrarSesion ',
                    function (datos)
                    {
                        alert("Cerraste Sesion");
                        $(location).attr('href', "sesion.html");

                    }
            );
        }
);
//
//$('#aMovimientos').click(
//        function (e) {
//            e.preventDefault();
//            // var frm=new FormData();
//
//            $.get('./ServletValidarUsuario ',
//                    function (datos)
//                    {
//                        var d = datos;
//
//                        if (d === "si") {
//                            $(location).attr('href', "Movimientos/Movimientos.jsp");
//                        } else {
//                            alert("No tienes permisos para ingresar a este catalogo")
//                        }
//
//                    }
//            );
//
//
//        }
//);




$(".e-list").slideUp(function () {
    $(".e-button").removeClass("open");
});

$(".e-button").on("click", function () {
    if ($(this).hasClass("open")) {
        $(".e-list").slideUp(function () {
            $(".e-button").removeClass("open");
        });
    } else {
        $(this).addClass("open");
        setTimeout(function () {
            $(".e-list").stop().slideDown();
        }, 200);
    }
});

