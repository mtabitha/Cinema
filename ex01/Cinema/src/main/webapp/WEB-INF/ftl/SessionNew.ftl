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
</head>
<body>
    <table width="1000" border="1" cellpadding="4" cellspacing="0">
        <thead bgcolor="#C0C0C0">
        <tr>
            <th>Id</th>
            <th>Hall</th>
            <th>Movie Name</th>
            <th>Movie Year</th>
            <th>Description</th>
            <th>Time</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <#list sessions as session>
            <tr >
                <td width="50%" align="center">${session.id}</td>
                <td width="50%" align="center">${session.hall.id}</td>
                <td width="50%" align="center">${session.movie.title}</td>
                <td width="50%" align="center">${session.movie.description}</td>
                <td width="50%" align="center">${session.movie.year}</td>
                <td width="50%" align="center">${session.time}</td>
                <td width="50%" align="center">${session.price}</td>
            </tr>
        </#list>
        </tbody>
    </table>



</body>
</html>