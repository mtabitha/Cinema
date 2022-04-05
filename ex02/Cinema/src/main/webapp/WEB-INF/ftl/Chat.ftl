<html>
<head>
  <title>Hello WebSocket</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<#--  <script src="classpath:../ftl/app.js"></script>-->

  <style>
    .layer {
      overflow: scroll; /* Добавляем полосы прокрутки */
      overflow-anchor: none;
      width: 500px; /* Ширина блока */
      height: 300px; /* Высота блока */
      padding: 5px; /* Поля вокруг текста */
      border: solid 1px black; /* Параметры рамки */
      margin: 0 auto; /* Выравнивание по центру */
    }
  </style>

  <script type="text/javascript">
    var stompClient = null;

    function connect(path) {
      var socket = new SockJS('/ws');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic' + path + "/messages", function (message) {
          showGreeting(JSON.parse(message.body));
        });
      });
    }

    function disconnect() {
      if (stompClient !== null) {
        stompClient.disconnect();
      }
    }

    function sendName(path) {
      var id = document.cookie.split(';').find(s => s.match('id')).split('=')[1];
      if ($("#name").val().length != 0) {
        stompClient.send("/app" + path, {}, JSON.stringify({'text': $("#name").val(), 'channelId': id}));
      }
    }

    function showGreeting(messages) {
      $("#greetings").empty()
      for ( message of messages) {
        var image = message.user.avatar == null ? null : message.user.avatar.uuid;
        $("#greetings").append(
                '<tr>' +
                '<td  width="30%" align="center"><img src="/images/' + image + '" width="50" height="50"></td>' +
                '<td  width="20%" align="center">user' + message.user.id + '</td>' +
                '<td  width="50%" align="center">' + message.text + '</td>' +
                '</tr>'
        )
      }
    }

    function upload(path) {
      var file_data = $('#file').prop('files')[0];
      var form_data = new FormData();
      form_data.append('file', file_data);
      $.ajax({
        url: path + '/images', // point to server-side controller method
        dataType: 'text', // what to expect back from the server
        cache: false,
        contentType: false,
        processData: false,
        data: form_data,
        type: 'post'
      });
    }

    $(function () {
      $("form").on('submit', function (e) {
        e.preventDefault();
      });
      const path = new URL(document.URL, document.location, true).pathname;
      connect(path);
      $( "#send" ).click(function() { sendName(path); $('#name').val(''); });
      $("#upload").on('click', function () { upload(path);  $('#file').val('');})
    });

  </script>

</head>
<body>
<div id="load-avatar" align="center">
  <input id=file type="file" name="file" multiple>
  <button type="submit" id="upload">Load avatar</button>
</div>
<div id="main-content" class="container">
  <br/>
  <div class="layer">
      <table id="conversation">
        <thead>
        <tr>
          <th width="30%" align="center"></th>
          <th width="20%" align="center">User</th>
          <th width="50%" align="center">Message</th>
        </tr>
        </thead>
        <tbody id="greetings">
        <#list messages as message>
          <tr >
            <td width="30%" align="center">
              <#if message.user.avatar??>
                <img src="/images/${message.user.avatar.uuid}" width="50" height="50">
              </#if>
            </td>
            <td width="20%" align="center">user${message.user.id}</td>
            <td width="50%" align="center">${message.text}</td>
          </tr>
        </#list>
        </tbody>
      </table>
  </div>
  <br/>
</div>

<div class="row" align="center">
  <div class="col-md-6">
    <form class="form-inline">
      <div class="form-group">
        <label for="name"></label>
        <input type="text" id="name" class="form-control" placeholder="Type message:" size="30">
        <button id="send" class="btn btn-default" type="submit">Send</button>
      </div>
    </form>
  </div>
</div>


</body>
</html>