<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Halls</title>
    <style>
        body {
            font: 11pt Arial, Helvetica, sans-serif; /* Рубленый шрифт текста */
            margin: 0; /* Отступы на странице */
        }
        h1 {
            font-size: 36px; /* Размер шрифта */
            margin: 0 auto; /* Выравнивание по центру */
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
    <h1>List of halls</h1>
    <table width="1000" border="1" cellpadding="4" cellspacing="0">
        <thead bgcolor="#C0C0C0">
        <tr>
            <th>Id</th>
            <th>Size</th>
        </tr>
        </thead>
        <tbody>
        <#list halls as hall>
            <tr >
                <td width="50%" align="center">${hall.id}</td>
                <td width="50%" align="center">${hall.size}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <br/>
    <form action="/admin/panel/halls" method="post">
        <input type="number" required="required" min="1" max="300" name="size" placeholder="Size: "/>
        <input type="submit" value="New Hall"/>
    </form>
</body>
</html>