<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Greeting</title>
    <style>
        body {
            font: 11pt Arial, Helvetica, sans-serif; /* Рубленый шрифт текста */
            margin: 0; /* Отступы на странице */
        }
        h1 {
            font-size: 36px; /* Размер шрифта */
            margin: 0; /* Убираем отступы */
            color: #fc6; /* Цвет текста */
        }
        h2 {
            margin-top: 0; /* Убираем отступ сверху */
        }
        #container {
            width: 1000px; /* Ширина слоя */
            margin: 0 auto; /* Выравнивание по центру */
        }
        #sidebar { /* Левая колонка */
            float: left; /* Обтекание справа */
            width: 300px; /* Ширина колонки */
            padding: 5px; /* Поля вокруг текста */
        }
        #content { /* Правая колонка */
            margin: 10px 5px 20px 310px; /* Значения отступов */
            padding: 5px; /* Поля вокруг текста */
        }
        #footer { /* Нижний блок */
            width: 1000px; /* Ширина слоя */
        }
    </style>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

</head>
<body>

    <input type="text" name="search" id="search" placeholder="Movie name: "/>

    <table id="tbl" width="1000" border="1" cellpadding="4" cellspacing="0">
        <thead bgcolor="#C0C0C0">
        <tr>
            <th>Time</th>
            <th>Movie Name</th>
            <th>Poster</th>
        </tr>
        </thead>
        <tbody id="ajaxResponse">
        </tbody>
    </table>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#search").keyup(function () {
                $("#ajaxResponse").html('');
                $("#tbl > tbody").empty();
                $.ajax({
                    url: "/sessions/search",
                    type: "GET",
                    data: {'filmName': $("#search").val()},
                    dataType: 'json',
                    success: function (date) {
                        for (const element of date) {
                            $("#ajaxResponse").append(
                                '<tr>' +
                                    '<td>' + element.time + '</td>' +
                                    '<td><a href ="/sessions/' + element.id + '">' + element.movie.title + '</a></td>' +
                                    '<td><img src="/admin/panel/films/poster/' + element.movie.posterLink + '" width="50" height="50"></td>' +
                                '</tr>'
                            )
                        }
                    },
                })
            })
        })
    </script>

</body>
</html>