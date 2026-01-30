import { apiRequest } from "./api";

export async function autenticar(data) {
    return apiRequest("/auth/login",{
        metodo:"POST",
        body:data
    }
    )
}