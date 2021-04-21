

$(document).ready(
        function () {

            $('#btnBuscar').click(function () {
      

                var nombre = $('#xnombre').val();
                var id = $('#xid').val();

                var filtro = "";

                if (id > 0) {
                    filtro = "/" + id;
                } else {
                    filtro = "?nombreSupermercado=" + nombre;
                }

                if (nombre === undefined && id === 0) {
                    filtro = "";
                }


                console.log(filtro);
                $.get('/ProfecoAPP/api/supermercados' + filtro,
                        function (datos)
                        {
                            $('#tbody').empty();
                            var infoJSON = datos;
                            console.log(datos);
                     
                            console.log(infoJSON);
                            var infoJSONhtml;
                            for (var i = 0; i < infoJSON.length; i++) {
                                  infoJSONhtml += '<tr> <td>' + infoJSON[i].idSupermercado + '</td> <td>' + infoJSON[i].nombreSupermercado + '</td> <td>' + infoJSON[i].direccion + '</td> <td> <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalModificar" onclick="MandarDatos(' + infoJSON[i].idSupermercado + ',`' + infoJSON[i].nombreSupermercado + '`,`' + infoJSON[i].direccion + '`)">Modificar</button>  <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalEliminar" onclick="MandarDatos(' + infoJSON[i].idSupermercado + ',`' + infoJSON[i].nombreSupermercado + '`,`' + infoJSON[i].direccion + '`)">Eliminar</button>  </td></tr>';
                            }
                            $('#tbody').html(infoJSONhtml);
//                       
                        }
                );




            });



            $.get('/ProfecoAPP/api/supermercados',
                    function (datos)
                    {
                        var infoJSON = datos;
                        console.log(datos);
                     
                        console.log(infoJSON);
                        var infoJSONhtml;
                        for (var i = 0; i < infoJSON.length; i++) {
                            infoJSONhtml += '<tr> <td>' + infoJSON[i].idSupermercado + '</td> <td>' + infoJSON[i].nombreSupermercado + '</td> <td>' + infoJSON[i].direccion + '</td> <td> <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalModificar" onclick="MandarDatos(' + infoJSON[i].idSupermercado + ',`' + infoJSON[i].nombreSupermercado + '`,`' + infoJSON[i].direccion + '`)">Modificar</button>  <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalEliminar" onclick="MandarDatos(' + infoJSON[i].idSupermercado + ',`' + infoJSON[i].nombreSupermercado + '`,`' + infoJSON[i].direccion + '`)">Eliminar</button>  </td></tr>';
                        }
                        $('#tbody').html(infoJSONhtml);
//                       |
                    }
            );




            MandarDatos = function (xid, xnombre, xdireccion) {

                $('#ModalModificar').on('show.bs.modal', function (e) {
                    var id = xid;
                    var nombre = xnombre;
                    var direccion = xdireccion;
                    $('#mid').val(id);
                    $('#mNombre').val(nombre);
                    $('#mDireccion').val(direccion);

                });

                $('#ModalEliminar').on('show.bs.modal', function (e) {
                   var id = xid;
                    var nombre = xnombre;
                    var direccion = xdireccion;
                    $('#eid').val(id);
                    $('#eNombre').val(nombre);
                    $('#eDireccion').val(direccion);
                });

            }

            $('#btnAgregar').click(
                    function (e) {
                        e.preventDefault();
                        // var frm=new FormData();
                        const data = {
                            nombreSupermercado: $('#Nombre').val(),
                            direccion: $('#direccion').val()
                        };
                        console.log(data);

                        $.ajax({
                            url: '/ProfecoAPP/api/supermercados/',
                            data: JSON.stringify(data),
                            method: 'post', //en este caso 
                            contentType: "application/json",
                            
                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");
                                $('#Nombre').value = "";
                                $('#direccion').value = "";
                                $(location).attr('href', "supermercado.html");
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
                            nombreSupermercado: $('#mNombre').val(),
                            direccion: $('#mDireccion').val()
                        };
                        console.log(data);

                        $.ajax({
                            url:'/ProfecoAPP/api/supermercados/' + id,
                            data: JSON.stringify(data),
                            method: 'put', //en este caso 
                            contentType: "application/json",
                            Accept: "application/json",
                           
                          
                            success: function (response) {
                                alert("funciona bien");
                                $(location).attr('href', "supermercado.html");
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
                            url: '/ProfecoAPP/api/supermercados/' + id,                           
                            method: 'delete',  
                            contentType: "application/json",
                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");
                                $(location).attr('href', "supermercado.html");
                            },
                            error: function (error) {
                                alert("No funciona");
                            }
                        });
                    }

            );


        }
);
