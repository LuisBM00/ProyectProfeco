/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(
        function () {
            
                       
            

            $('#aCerrarSesion').click(
                    function (e) {

                        e.preventDefault();
                        // var frm=new FormData();

                        $.post('../ServletCerrarSesion ',
                                function (datos)
                                {
                                    alert("Cerraste Sesion");
                                    $(location).attr('href', "../Session/sesion.html");

                                }
                        );
                    }
            );

     
          
            $('#btnRegistrar').click(
                    function (e) {
                        e.preventDefault();
                        // var frm=new FormData();

                        $.post('../ServletAgregarUsuarioCliente', $('#registro').serialize()).done(
                                function (datos)
                                {
                                    alert("Se agrego");
                                    $('#IdCliente').value = "";
                                    $('#Nombre').value = "";
                                    $('#APaterno').value = "";
                                    $('#AMaterno').value = "";
                                    $('#Direccion').value = "";
                                    $('#Correo').value = "";
                                    $('#IdUsuario').value = "";
                                    alert("Se ha registrado correctamente");
                                    $(location).attr('href', "../Session/sesion.html");

                                }
                        );
                    }

            );

      


        }
);

