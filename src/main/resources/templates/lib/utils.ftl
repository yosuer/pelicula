<#macro page tituloPagina>
<!DOCTYPE html>
<html>
    <head>
        <title>${tituloPagina}</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="materialize/css/materialize.min.css" rel="stylesheet" />
        </head>
    <body>
    <#nested>
        </body>
    </html>
</#macro>

<#macro imports>
<script src="js/lib/jquery3.min.js"></script>
<script src="js/lib/jsviews.min.js"></script>
<script src="materialize/js/materialize.min.js"></script>
<script src="js/app.js"></script>
<script src="js/service/service.js"></script>
<script src="js/ui/ui.js"></script>
</#macro>