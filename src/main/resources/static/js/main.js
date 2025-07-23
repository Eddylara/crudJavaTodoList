let actualizarReader = ()=>{
    fetch("http://localhost:8080/contacto").then(e=>{
        return e.json();
    }).then(e=>{
        let fragmento = document.createDocumentFragment();
        e.forEach(ele => {
            let fila = document.createElement("tr");
            
            let id = document.createElement("td");
            id.innerHTML = ele["id"];
            let nombre = document.createElement("td");
            nombre.innerHTML = ele["nombre"];
            let celular = document.createElement("td");
            celular.innerHTML = ele["celular"];
            let correo = document.createElement("td");
            correo.innerHTML = ele["correo"];


            fila.appendChild(id);
            fila.appendChild(nombre);
            fila.appendChild(celular);
            fila.appendChild(correo);

            fragmento.appendChild(fila)

        });
        let scene = document.querySelector(".table_body");
        scene.innerHTML = "";
        
        scene.appendChild(fragmento);
    });
}

actualizarReader();