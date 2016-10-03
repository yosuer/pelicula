<div id="cuadroPelicula">
    </div>

<script id="template-cuadro-pelicula" type="text/x-jsrender">

{{if title}}
    <div class="card medium">
        <div class="card-image waves-effect waves-block waves-light">
            {{if poster === 'N/A'}}
                Sin Imagen...
            {{else}}
                <img class="activator" src="{{:poster}}">
                <span class="card-title">{{:title}}</span>
            {{/if}}
            </div>
        <div class="card-content">
            <p>Genero: {{:genre}} <i class="material-icons right activator">more_vert</i></p> 
            <p>Pais: {{:country}}</p>
            <p>Año: {{:year}}</p>
            </div>
        <div class="card-action">
            <a href="#">Agregar a videoteca</a>
            </div>
        <div class="card-reveal">
            <span class="card-title grey-text text-darken-4">{{:title}}<i class="material-icons right">close</i></span>
            <p>{{:plot}}</p>
            </div>
    </div>
    
{{else}}
    <p>Pelicula no encontrada</p>
{{/if}}
    
    

</script>