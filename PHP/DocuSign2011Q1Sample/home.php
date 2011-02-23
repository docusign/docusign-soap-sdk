<?php 

?>

<!DOCTYPE html">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/homestyle.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DocuSign PHP SDK Sample</title>
        <script type="text/javascript" src="js/jquery-1.4.4.js"></script>
        <script type="text/javascript">
            $(function(){
                $('article.tabs section > h3').click(function(){
                    $('article.tabs section').removeClass('current');
                    $(this) .closest('section').addClass('current');
                });
            });
        </script>
    </head>
    <body>
        <article class="tabs">
            <section class="current">
                <h3>Send Document</h3>
                <iframe src="senddocument.php"></iframe>
            </section>
            <section>
                <h3>Send a Template</h3>
                <iframe src="sendatemplate.php"></iframe>
            </section>
            <section>
                <h3>Embed DocuSign</h3>
                <iframe src="embeddocusign.php"></iframe>
            </section>
            <section>
                <h3>Get Status and Docs</h3>
                <iframe src="getstatusanddocs.php"></iframe>
            </section>
        </article>
    </body>
</html>
