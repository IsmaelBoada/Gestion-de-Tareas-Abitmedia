package com.prueba_tecnica.abitmedia.services.messages;


public abstract class ApiMessages {

    private ApiMessages() {
    }

    public static final String ERROR_CORREO_REGISTRADO = "El correo ya se encuentra registrado";
    public static final String ERROR_NO_EXISTE_TAREA = "La tarea no existe o no se encuentra disponible.";
    public static final String ERROR_NO_EXISTE_USUARIO = "El usuario no existe";
    public static final String ERROR_JWT_MAL_FORMADO_EXPIRADO = "El token proporcionado ha expirado o es inválido.";
    public static final String ERROR_OPERACION = "No se puede realizar la operación requerida";
    public static final String ERROR_VALIDACIONES_REQUEST = "Existe los siguientes errores: ";


}
