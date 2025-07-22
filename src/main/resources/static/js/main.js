let $btn = document.getElementById("btn");

document.addEventListener("click", (e)=>{
    if($btn.contains(e.target)){
        let datos ={
            nombre: "Desire de la rosa",
            celular: "3254585332",
            correo: "mivida@gmail.com"            
        }
        let datosE = {
            
            nombre: "Desire de la rosa",
            celular: "3254585332",
            
        }

        fetch("http://localhost:8080/contacto/18",{
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(datos),
        }).then(e=>{
            return e.json();
        }).then(e=>{
            console.log(e);
        }).catch(e=>{
            console.log("ERROR");
            console.log(e);
        });
    }
})