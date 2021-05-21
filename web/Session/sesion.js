$(document).ready(
          //Asignar elemento submit a variable botonEnviar <input type="submit">
  
          function(){
                        
            //implementacion de funcion click del boton
            $('#btnIngresar').click(
                    
                    function(e){
                        e.preventDefault();
                       
                       var txtIdUsuario =  $('#txtCorreo');
                       var txtPassword = $('#txtPassword');
                      // alert(txtIdUsuario.value);
                        if(txtIdUsuario.value===null ||  txtPassword.value===null){
                           
                            alert("Favor de llenar los textos");
                        }
                        
                        else{
                            
                            $.post('../ServletValidacionLogin',$('#formulario').serialize()).done(
                                    function(datos)
                                        {
                                            
                                            var info=datos;                                           
                                            
                                            if(datos==="esta"){                                              
                                                $(location).attr('href',"../principal.html");
                                                
                                            }
                                            else{
                                                
                                                alert("Contrasena o Usuario incorrecto");
                                                
                                               
                                                $(location).attr('href',"sesion.html");
                                                
                                            }

                                        }
                                );
                                
                        }
                    }                     
                      );
        
          }
     
          ); 
        
        