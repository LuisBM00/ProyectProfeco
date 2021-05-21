/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(
        function () {
    
           
            var Usuario;
            var idSup;
           
            ObtenerUsuario = function () {
                $.get('/ProyectProfeco/ServletConsultarUsuarioIniciado',
                        function (datos)
                        {

                            Usuario = JSON.parse(datos);
                            console.log(Usuario);
//                       |
                        }
                );
            }
         
         ListarSup=function(){   $.get('/ProyectProfeco/api/supermercados',
                    function (datos)
                    {
                        ObtenerUsuario();
                        var infoJSON = datos;
                        console.log(datos);
                        console.log(infoJSON);
                        var infoJSONhtml;
                        for (var i = 0; i < infoJSON.length; i++) {
                            if (Usuario.idTipoUsuario !== 3) {
                                if (i == 0)
                                {
                                    infoJSONhtml += '<option value="' + infoJSON[i].idSupermercado + '" selected>' + infoJSON[i].nombreSupermercado + '</option> ';
                                    idSup = infoJSON[i].idSupermercado;
                                } else {
                                    infoJSONhtml += '<option value="' + infoJSON[i].idSupermercado + '">' + infoJSON[i].nombreSupermercado + '</option> ';
                                }
                            } else {
                                if (Usuario.idSupermercado == infoJSON[i].idSupermercado)
                                {
                                    infoJSONhtml += '<option value="' + infoJSON[i].idSupermercado + '" selected>' + infoJSON[i].nombreSupermercado + '</option> ';
                                    idSup = infoJSON[i].idSupermercado;
                                } else {
                                    infoJSONhtml += '<option value="' + infoJSON[i].idSupermercado + '">' + infoJSON[i].nombreSupermercado + '</option> ';
                                }
                            }
                        }
                        $('#ListaSupermercados').html(infoJSONhtml);
                        Listar(idSup);
                    }
            );
         }
                 ObtenerUsuario();
                  ListarSup();
            $('#btnBuscar').click(function () {

                $('#tbody').html("");
                Listar($('select[name="ListaSuper"] option:selected').val());
            });
            $.get('/ProyectProfeco/api/articulos/',
                    function (datos)
                    {
                        var infoJSON = datos;
                        console.log(datos);
                        console.log(infoJSON);
                        var infoJSONhtml;
                        for (var i = 0; i < infoJSON.length; i++) {

                            if (i == 0)
                            {
                                infoJSONhtml += '<option value="' + infoJSON[i].idArticulo + '" selected>' + infoJSON[i].nombreArticulo + '</option> ';
                            } else {
                                infoJSONhtml += '<option value="' + infoJSON[i].idArticulo + '">' + infoJSON[i].nombreArticulo + '</option> ';
                            }
                        }
                        $('#ListaArticulos').html(infoJSONhtml);
//                       |
                    }
            );
            Listar = function (idSuper) {
                $.get('/ProyectProfeco/api/ListaArticulosXSupermercados/?idSupermercado=' + idSuper,
                        function (datos)
                        {
                            var infoJSON = datos;
                            console.log(datos);
                            var infoJSONhtml;
                            for (var i = 0; i < infoJSON.length; i++) {

                                infoJSONhtml += BuscarArticulo(infoJSONhtml, infoJSON[i].idArticulo, infoJSON[i]);
                            }

//                       |
                        }
                );
            }

            BuscarArticulo = function (infoJSONhtml, id, infoJSON)
            {
                $.get('/ProyectProfeco/api/articulos/' + id,
                        function (datos)
                        {

                            var infoArt = datos;
                            if (Usuario.idTipoUsuario === 1 || Usuario.idTipoUsuario === 3)
                            {
                                infoJSONhtml += '<tr> <td>' + infoJSON.idArticulo + '</td> <td>' + infoArt[0].nombreArticulo + '</td>' + '<td>' + infoArt[0].marca + '</td>' + ' <td>' + infoJSON.cantidad + '</td> <td>' + infoJSON.precioActual + '</td> <td><button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalModificar" onclick="MandarDatos(' + infoJSON.idArticulo + ',`' + infoArt[0].nombreArticulo + '`,' + infoJSON.idLista + ',' + infoJSON.precioActual+',' + infoJSON.precioInicial + ',' + infoJSON.estaDescuento + ')">Modificar Precio</button></td></tr>  ';
                                if (Usuario.idTipoUsuario === 3)
                                {
                                    $('#ListaSupermercados').prop("disabled", true);
                                }
                            } else {
                                infoJSONhtml += '<tr> <td>' + infoJSON.idArticulo + '</td> <td>' + infoArt[0].nombreArticulo + '</td>' + '<td>' + infoArt[0].marca + '</td>' + ' <td>' + infoJSON.cantidad + '</td> <td>' + infoJSON.precioActual + '</td> <td><button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalReportar" onclick="MandarDatos(' + infoJSON.idArticulo + ',`' + infoArt[0].nombreArticulo + '`,' + infoJSON.idLista + ',' + infoJSON.precioActual + ',' + infoJSON.precioInicial+',' + infoJSON.estaDescuento + ')">Reportar</button></td></tr>  ';
                                $('#agregar').css('display', 'none');
                            }
                            $('#tbody').html($('#tbody').html() + infoJSONhtml);
                            return infoJSONhtml;
                        }


                );
            }


            MandarDatos = function (idArticulo, nombre, idLista, precioActual,precioAnterior, oferta) {

                $('#ModalModificar').on('show.bs.modal', function (e) {
                    $('#modArticulo').html(nombre);
                    $('#modPrecioAnterior').val(precioActual);
                    $('#mid').html(idLista);
                });
                $('#ModalReportar').on('show.bs.modal', function (e) {
                     $('#rArticulo').html(nombre);
                       $('#rPrecioActual').val(precioActual);
                    $('#rPrecioAnterior').val(precioAnterior);
                    $('#rid').html(idArticulo);
                });
            }

            $('#btnAgregar').click(
                    function (e) {
                        e.preventDefault();
                        // var frm=new FormData();
                        const data = {
                            idSupermercado: idSup,
                            idArticulo: $('#ListaArticulos').val(),
                            cantidad: $('#Cantidad').val(),
                            precioActual: $('#Precio').val(),
                            precioInicial: $('#Precio').val(),
                            estaDescuento: 0

                        };
                        console.log(data);
                        $.ajax({
                            url: '/ProyectProfeco/api/ListaArticulosXSupermercados/',
                            data: JSON.stringify(data),
                            method: 'post', //en este caso 
                            contentType: "application/json",
                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");
                                $('#Nombre').value = "";
                                $('#Marca').value = "";
                                $(location).attr('href', "ListaArticulosXSuper.html");
                            },
                            error: function (error) {
                                alert("No funciona");
                            }
                        });
                    }

            );
            $('#btnModificar').click(
                    function (e) {
                        e.preventDefault();
                        var oferta = 0;
                        if ($('#modOferta').prop('checked'))
                        {
                            oferta = 1;
                        }
                        var id = $('#mid').html();
                        const data = {
                            precioActual: $('#modPrecioActual').val(),
                            precioAnterior: $('#modPrecioAnterior').val(),
                            estaDescuento: oferta
                        };
                        console.log(id);
                        $.ajax({
                            url: '/ProyectProfeco/api/ListaArticulosXSupermercados/' + id,
                            data: JSON.stringify(data),
                            method: 'put', //en este caso 
                            contentType: "application/json",
                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");
                                $(location).attr('href', "ListaArticulosXSuper.html");
                            },
                            error: function (error) {
                                alert("No funciona");
                            }
                        });
                    }

            );
            $('#btnReportar').click(
                    function (e) {
                        e.preventDefault();
                        var id = $('#rid').html();
                        
                        const data = {
                            idCliente:Usuario.idCliente,
                            idSupermercado: idSup,
                            idArticulo: id,
                            precioPublicado: $('#rPrecioActual').val(),
                            precioReal: $('#rPrecioAnterior').val(),
                            comentarios: $('#Comentario').val()
                        

                        };
                        console.log(data);
                        $.ajax({
                            url: '/ProyectProfeco/api/ReporteInconsistencia/',
                            method: 'post',
                            data: JSON.stringify(data),
                            contentType: "application/json",
                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");
                          $(location).attr('href', "ListaArticulosXSuper.html");
                            },
                            error: function (error) {
                                alert("No funciona");
                            }
                        });
                    }


            );
        }
);
