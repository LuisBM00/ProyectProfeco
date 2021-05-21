$(document).ready(
        function () {
            var idSuper;
            var idCli;
            console.log();
            $.get('/ProyectProfeco/api/supermercados',
                    function (datos)
                    {

                        var infoJSON = datos;
                        console.log(datos);

                        console.log(infoJSON);
                        var infoJSONhtml;
                        for (var i = 0; i < infoJSON.length; i++) {
                            infoJSONhtml += '<tr> <td>' + infoJSON[i].idSupermercado + '</td> <td>' + infoJSON[i].nombreSupermercado + '</td> <td>' + infoJSON[i].direccion + '</td> <td> <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalEliminar" onclick="MandarDatos(' + infoJSON[i].idSupermercado + ',`' + infoJSON[i].nombreSupermercado + '`)">Calificar</button>  </td></tr>';
                        }
                        $('#tbody').html(infoJSONhtml);
//                       |
                    }
            );
            $.get('/ProyectProfeco/ServletConsultarUsuarioIniciado',
                    function (datos)
                    {


                        idCli = datos;


//                       |
                    }
            );

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
                $.get('/ProyectProfeco/api/supermercados' + filtro,
                        function (datos)
                        {
                            $('#tbody').empty();
                            var infoJSON = datos;
                            console.log(datos);

                            console.log(infoJSON);
                            var infoJSONhtml;
                            for (var i = 0; i < infoJSON.length; i++) {
                                infoJSONhtml += '<tr> <td>' + infoJSON[i].idSupermercado + '</td> <td>' + infoJSON[i].nombreSupermercado + '</td> <td>' + infoJSON[i].direccion + '</td> <td> <button type="button" class="btn btn-secondary edit" data-toggle="modal" data-target="#ModalEliminar" onclick="MandarDatos(' + infoJSON[i].idSupermercado + ',`' + infoJSON[i].nombreSupermercado + '`)">Calificar</button>  </td></tr>';
                            }
                            $('#tbody').html(infoJSONhtml);
//                    
                        }
                );




            });





            MandarDatos = function (xid, xnombre) {


                $('#ModalEliminar').on('show.bs.modal', function (e) {
                    var id = xid;
                    var nombre = xnombre;
                    idSuper = id;
                    $('#idSup').html(id);
                    $('#tituloComentarios').html("Supermercado " + nombre);

                    $.get('/ProyectProfeco/api/calificaciones?idSupermercado=' + idSuper,
                            function (datos)
                            {
                                var infoJSON = datos;
                                console.log(datos);

                                console.log(infoJSON);
                                var infoJSONhtml = "";
                                for (var i = 0; i < infoJSON.length; i++) {
                                    infoJSONhtml += '<p>' + infoJSON[i].idCliente + '</p> <p>' + infoJSON[i].comentario + '</p>   <hr>';
                                }
                                $('#Comentarios').html(infoJSONhtml);

                            }
                    );

                });

            }

            $('#comentar').click(
                    function (e) {
                        e.preventDefault();
                        // var frm=new FormData();
                        const data = {

                            idCliente: idCli,
                            idSupermercado: idSuper,
                            calificacion: $('#calificacion').val(),
                            comentario: $('#Comentario').val()
                        };
                        console.log(data);

                        $.ajax({
                            url: '/ProyectProfeco/api/calificaciones/',
                            data: JSON.stringify(data),
                            method: 'post', //en este caso 
                            contentType: "application/json",
                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");
                                $('#Nombre').value = "";
                                $('#direccion').value = "";
                                console.log(response);
                            },
                            error: function (error) {
                                alert("No funciona");
                            }
                        });

                    }

            );





        }
);

Comentar = function ()
{

}
