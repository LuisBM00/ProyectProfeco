/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(
        function () {

            $.get('../ServletConsultarUsuarioIniciado', function (response) {
                console.log(response);
                             
                if (response=== 'null') {
                    console.log(response);
                    $(location).attr('href', "../Session/sesion.html");
                } else {


                }
            });

            $('#btnBuscar').click(function () {


                var nombre = $('#xnombre').val();
                //var id = $('#xid').val();

                var filtro = "";

//                if (id > 0) {
//                    filtro = "/" + id;
//                } else {
//                    filtro = "?nombreSupermercado=" + nombre;
//                }
//
//                if (nombre === undefined && id === 0) {
//                    filtro = "";
//                }

                filtro = nombre;
                console.log(filtro);
                $.get('/ProyectProfeco/api/articulos/?ArticuloNombre=' + filtro,
                        function (datos)
                        {
                            $('#tbody').empty();
                            var infoJSON = datos;
                            console.log(datos);

                            console.log(infoJSON);
                            var infoJSONhtml;
                            for (var i = 0; i < infoJSON.length; i++) {
                                infoJSONhtml += '<tr> <td>' + infoJSON[i].idArticulo + '</td> <td>' + infoJSON[i].nombreArticulo + '</td> <td>' + infoJSON[i].marca + '</td> <td> <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalModificar" onclick="MandarDatos(' + infoJSON[i].idArticulo + ',`' + infoJSON[i].nombreArticulo + '`,`' + infoJSON[i].marca + '`)">Modificar</button>  <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalEliminar" onclick="MandarDatos(' + infoJSON[i].idArticulo + ',`' + infoJSON[i].nombreArticulo + '`,`' + infoJSON[i].marca + '`)">Eliminar</button>  </td></tr>';
                            }
                            $('#tbody').html(infoJSONhtml);
//                       
                        }
                );




            });



            $.get('/ProyectProfeco/api/articulos/',
                    function (datos)
                    {
                        var infoJSON = datos;
                        console.log(datos);

                        console.log(infoJSON);
                        var infoJSONhtml;
                        for (var i = 0; i < infoJSON.length; i++) {
                            infoJSONhtml += '<tr> <td>' + infoJSON[i].idArticulo + '</td> <td>' + infoJSON[i].nombreArticulo + '</td> <td>' + infoJSON[i].marca + '</td> <td> <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalModificar" onclick="MandarDatos(' + infoJSON[i].idArticulo + ',`' + infoJSON[i].nombreArticulo + '`,`' + infoJSON[i].marca + '`)">Modificar</button>  <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalEliminar" onclick="MandarDatos(' + infoJSON[i].idArticulo + ',`' + infoJSON[i].nombreArticulo + '`,`' + infoJSON[i].marca + '`)">Eliminar</button>  </td></tr>';
                        }
                        $('#tbody').html(infoJSONhtml);
//                       |
                    }
            );




            MandarDatos = function (xid, xnombre, xmarca) {

                $('#ModalModificar').on('show.bs.modal', function (e) {
                    var id = xid;
                    var nombre = xnombre;
                    var marca = xmarca;
                    $('#mid').val(id);
                    $('#mNombre').val(nombre);
                    $('#mMarca').val(marca);

                });

                $('#ModalEliminar').on('show.bs.modal', function (e) {
                    var id = xid;
                    var nombre = xnombre;
                    var marca = xmarca;
                    $('#eid').val(id);
                    $('#eNombre').val(nombre);
                    $('#eMarca').val(marca);
                });

            }

            $('#btnAgregar').click(
                    function (e) {
                        e.preventDefault();
                        // var frm=new FormData();
                        const data = {
                            nombreArticulo: $('#Nombre').val(),
                            marca: $('#Marca').val()
                        };
                        console.log(data);

                        $.ajax({
                            url: '/ProyectProfeco/api/articulos/',
                            data: JSON.stringify(data),
                            method: 'post', //en este caso 
                            contentType: "application/json",

                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");
                                $('#Nombre').value = "";
                                $('#Marca').value = "";
                                $(location).attr('href', "articulo.html");
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

                        var id = $('#mid').val();
                        const data = {
                            nombreArticulo: $('#mNombre').val(),
                            marca: $('#mMarca').val()
                        };
                        console.log(data);

                        $.ajax({
                            url: '/ProyectProfeco/api/articulos/' + id,
                            data: JSON.stringify(data),
                            method: 'put', //en este caso 
                            contentType: "application/json",
                            Accept: "application/json",

                            success: function (response) {
                                alert("funciona bien");
                                $(location).attr('href', "articulo.html");
                            },
                            error: function (error) {
                                alert("No funciona");
                            }
                        });
                    }

            );

            $('#btnEliminar').click(
                    function (e) {
                        e.preventDefault();


                        var id = $('#eid').val();


                        $.ajax({
                            url: '/ProyectProfeco/api/articulos/' + id,
                            method: 'delete',
                            contentType: "application/json",
                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");
                                $(location).attr('href', "articulo.html");
                            },
                            error: function (error) {
                                alert("No funciona");
                            }
                        });
                    }

            );


        }
);
