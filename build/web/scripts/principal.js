/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




$(document).ready(function () {

    $.get('./ServletConsultarUsuarioIniciado', function (response) {
                console.log(response);
                             
                if (response=== 'null') {
                    console.log(response);
                    $(location).attr('href', "Session/sesion.html");
                } else {


                }
            });
});

$('#aArticulos').click(
        function (e) {

            e.preventDefault();
            // var frm=new FormData();

            $.get('./ServletValidarUsuario ',
                    function (datos)
                    {
                        var d = datos;
                        console.log(d);
                        if (d === '2' || d ==='3') {                          
                           alert("No tienes permisos para ingresar a este catalogo")
                        } else {
                           $(location).attr('href', "articulo/articulo.html");
                        }

                    }
            );
        }
);

$('#aMultas').click(
        function (e) {

            e.preventDefault();
            // var frm=new FormData();

            $.get('./ServletValidarUsuario ',
                    function (datos)
                    {
                        var d = datos;

                        if (d === '2' || d ==='3') {                          
                           alert("No tienes permisos para ingresar a este catalogo")
                        } else {
                           $(location).attr('href', "Multa/multa.html");
                        }

                    }
            );
        }
);

$('#aSupermercados').click(
        function (e) {

            e.preventDefault();
            // var frm=new FormData();

            $.get('./ServletValidarUsuario ',
                    function (datos)
                    {
                        var d = datos;

                        if (d === '2' || d ==='3') {                          
                           alert("No tienes permisos para ingresar a este catalogo")
                        } else {
                           $(location).attr('href', "supermercado/supermercado.html");
                        }

                    }
            );
        }
);

$('#aCalificarSupers').click(
        function (e) {

            e.preventDefault();
            // var frm=new FormData();

            $.get('./ServletValidarUsuario ',
                    function (datos)
                    {
                        var d = datos;

                        if (d ==='3') {                          
                           alert("No tienes permisos para ingresar a este catalogo")
                        } else {
                           $(location).attr('href', "Calificaciones/Calificaciones.html");
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

$('#a').click(
        function (e) {

            e.preventDefault();
            // var frm=new FormData();

            $.post('./ServletCerrarSesion ',
                    function (datos)
                    {
                        alert("Cerraste Sesion");
                        $(location).attr('href', "Session/sesion.html");

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

