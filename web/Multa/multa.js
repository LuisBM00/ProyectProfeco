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

            $.get('/ProyectProfeco/api/multas/',
                    function (datos)
                    {
                        var infoJSON = datos;
                        console.log(datos);

                        console.log(infoJSON);
                        var infoJSONhtml;
                        for (var i = 0; i < infoJSON.length; i++) {
                            infoJSONhtml += '<tr> <td>' + infoJSON[i].idMulta + '</td> <td>' + infoJSON[i].idSupermercado + '</td> <td>' + infoJSON[i].tipoSancion + '</td> <td>' + infoJSON[i].numInconsistencias + '</td> <td> <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalEliminar" onclick="MandarDatos(' + infoJSON[i].idMulta + ',`' + infoJSON[i].idSupermercado + '`,`' + infoJSON[i].TipoSancion + '`,`' + infoJSON[i].NumInconsistencias + '`)">Eliminar</button> </td></tr>';
                        }
                        $('#tbody').html(infoJSONhtml);
//                       |
                    }
            );



            MandarDatos = function (xid, xnombre, xdireccion) {


                $('#ModalEliminar').on('show.bs.modal', function (e) {
                    var id = xid;
                    var nombre = xnombre;
                    var direccion = xdireccion;
                    $('#eid').val(id);
                    $('#eNombre').val(nombre);
                    $('#eDireccion').val(direccion);
                });
            }

            $('#btnEliminar').click(
                    function (e) {
                        e.preventDefault();


                        var id = $('#eid').val();


                        $.ajax({
                            url: '/ProyectProfeco/api/multas/' + id,
                            method: 'delete',
                            contentType: "application/json",
                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");
                                $(location).attr('href', "multa.html");
                            },
                            error: function (error) {
                                alert("No funciona");
                            }
                        });
                    }

            );


        }

);
