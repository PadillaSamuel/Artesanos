import { apiRequest } from "./api";

export async function actualizarProductoPorId(id,body){
    return apiRequest(`/api/producto/actualizar/${id}`,{
        metodo:'PUT',
        body:body
    })
}

export async function crearProducto(body) {
    return apiRequest(`/api/producto/crear`,{
        metodo:'POST',
        body:body
    })
}