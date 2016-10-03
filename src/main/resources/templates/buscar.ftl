<#import "/lib/utils.ftl" as util>

<#assign tituloCabecera="Pelicula - admin">

<@util.page tituloPagina="Admin">
<#include "header.ftl">
<div class="container">
    <br>
    <div class="row">
        <div class="input-field col s6">
            <input id="titulo" type="text" class="validate">
            <label class="active" for="titulo">Titulo de peli</label>
            </div>
        <div class="input-field col s3">
            <button class="btn waves-effect waves-light" id="btn-buscar" type="submit" name="action">Buscar
                <i class="material-icons left">search</i>
                </button>
            </div>
        </div>

    <div class="row">
        <div class="col s12 m6">
        <#include "lib/cuadroPelicula.ftl">
            </div>
        </div>

    </div>
<@util.imports />
<script src="js/lib/spin.min.js"></script>
<script src="js/service/peliculaImdb/peliculaImdb.js"></script>
<script src="js/ui/home/home.js"></script>
</@util.page>