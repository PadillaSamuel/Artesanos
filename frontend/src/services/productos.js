import { apiRequest } from "./api";

export async function actualizarProductoPorId(id,body){
    return apiRequest(`/api/producto/actualizar/${id}`,{
        metodo:'PUT',
        body:body
    })
}