
$(document).ready(
        function () {
              $.get('/ProyectProfeco-main/api/usuarios',
                    function (datos)
                    {
                        var infoJSON = datos;
                        console.log(datos);});
                    
            $('#btnAgregar').click(
                    function (e) {
                 
                        const data = {
                            Nombre:$('#txtNombre').val(),
                            idUsuario: $('#idUsuario').val(),
                            idTipoUsuario:3,
                            idCOS:$('#idSupermercado').val(),
                            Nombre:$('#txtNombre').val(),
                            Correo:$('#Correo').val(),
                            Contrasena:$('#Contraseña').val()
                        };
                  console.log(JSON.stringify(data));
                  console.log(JSON.parse(JSON.stringify(data)));
                        $.ajax({
                            url: '/ProyectProfeco/api/usuarios/',
                            data: JSON.stringify(data),
                            method: 'post', //en este caso 
                            contentType: "application/json",
                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");
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

