
$(document).ready(
        function () {
              $.get('/ProyectProfeco/api/usuarios',
                    function (datos)
                    {
                        var infoJSON = datos;
                        console.log(datos);});
                    
            $('#btnAgregar').click(
                    function (e) {
                
                        const datos = {     
                            idUsuario: $('#idUsuario').val(),
                            idTipoUsuario:3,
                            idCOS:$('#idSupermercado').val(),
                            nombre:$('#txtNombre').val(),
                            correo:$('#Correo').val(),
                            contrasena:$('#Contraseña').val()
                          
                        };
                              
                        $.ajax({
                            url: '/ProyectProfeco/api/usuarios/',
                            data: JSON.stringify(datos),                          
                            method: 'post', //en este caso 
                            contentType: "application/json",
                            Accept: "application/json",
                            success: function (response) {
                                alert("funciona bien");                         
                            },
                            error: function (error) {
                                alert("No funciona");
                            }
                        });

                    }

            );

       
      


        }
);

