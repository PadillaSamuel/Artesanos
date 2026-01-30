

function getToken(){
    return localStorage.getItem("token")
}

function crearHeaders(extraHeaders={}){
    const token=getToken();

    return{
        "Content-Type":"application/json",
        ...(token?{Authorization:`Bearer ${token}`}:{}),
        ...extraHeaders
    };
}

export async function apiRequest(path,options={}){
    const{
          metodo="GET",
          body,
          headers={}      
    }=options

    const res= await fetch(path,{
        method:metodo,
        headers:crearHeaders(headers),
        body:body!==undefined? JSON.stringify(body):undefined
    });


    // Si el backend devuelve 401, normalmente significa token inválido o expirado
if (res.status === 401) {
  // Opcional: limpiar token
  localStorage.removeItem("token");
}

// Manejo de errores: intentamos leer mensaje del backend
if (!res.ok) {
  const contentType = res.headers.get("content-type") || "";

  const errorPayload = contentType.includes("application/json")
    ? await res.json().catch(() => ({}))
    : await res.text().catch(() => "");

  const message =
    (typeof errorPayload === "string" && errorPayload) ||
    errorPayload?.message ||
    errorPayload?.error ||
    errorPayload?.mensaje ||
    `Error ${res.status}`;

  throw new Error(message);
}

// Si no hay contenido (204 No Content)
if (res.status === 204) return null;

// Si hay contenido, lo leemos según el tipo
const contentType = res.headers.get("content-type") || "";

if (contentType.includes("application/json")) {
  return res.json();
}

// Por si el backend devuelve texto
return res.text();

}