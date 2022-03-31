<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Films</title>
    <style>
        body {
            font: 11pt Arial, Helvetica, sans-serif; /* Рубленый шрифт текста */
            margin: 0; /* Отступы на странице */
        }
        h1 {
            font-size: 36px; /* Размер шрифта */
            margin: 0; /* Отступы на странице */
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
    <h1>List of movies</h1>
    <table width="1000" border="1" cellpadding="4" cellspacing="0">
        <thead bgcolor="#C0C0C0">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Year</th>
            <th>Age Restriction</th>
            <th>Description</th>
            <th>Poster</th>
        </tr>
        </thead>
        <tbody>
        <#list movies as movie>
            <tr >
                <td width="10%" align="center">${movie.id}</td>
                <td width="20%" align="center">${movie.title}</td>
                <td width="10%" align="center">${movie.year}</td>
                <td width="10%" align="center">${movie.ageRestriction.explanation}</td>
                <td width="30%" align="center">${movie.description}</td>
                <td width="20%" align="center">
                    <#if movie.posterLink??>
                        <img src="/admin/panel/films/poster/${movie.posterLink}" width="100" height="100">
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
    <br/>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="title" placeholder="Title: "/>
        <input type="number" min="1895" max="2022" name="year" placeholder="Year: "/>
        <input type="text" name="description" placeholder="Description: "/>
        <select name="ageRestriction">
            <#list ages as age>
                <option value="${age}">${age.explanation}</option>
            </#list>
        </select>
        <input type="file" name="file" accept="image/*">
        <button type="submit" >New Move</button>
    </form>
</body>
</html>