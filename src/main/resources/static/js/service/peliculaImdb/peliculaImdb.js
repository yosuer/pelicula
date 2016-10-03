app.service.peliculaImdb = (function () {

    function buscarPorTitulo(titulo) {
        var url = app.service.url() + "peliculaImdb?titulo=" + titulo;
        console.log('GET | ' + url);
        return app.service.get(url);
    }

    return {
        buscarPorTitulo: buscarPorTitulo
    };
})();