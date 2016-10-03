app.ui.home = (function () {

    var template = $.templates("#template-cuadro-pelicula");
    var opts = {
        lines: 15,
        className: 'spinner'
    };
    var spinner = new Spinner(opts).spin();

    function bindearBotonBuscar() {
        $("#btn-buscar").on("click", function (e) {
            $(this).append(spinner.el);
            var titulo = $("#titulo").val();
            app.service.peliculaImdb.buscarPorTitulo(titulo)
                    .done(mostrarPeliculaImdb);
        });
    }

    function init() {
        bindearBotonBuscar();
    }

    function mostrarPeliculaImdb(pelicula) {
        spinner.stop();
        template.link("#cuadroPelicula", pelicula);
    }

    return {
        init: init
    };
})();

$(document).ready((function () {
    app.ui.home.init();
    $(".button-collapse").sideNav();
}));