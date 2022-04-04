<html>
<head>
  <title>Hello WebSocket</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<#--  <script src="classpath:../ftl/app.js"></script>-->
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
      stompClient.send("/app" + path, {}, JSON.stringify({'text': $("#name").val(), 'channelId' : id}));
    }

    function showGreeting(messages) {
      $("#greetings").empty()
      for ( message of messages) {
        var image = message.user.avatar == null ? null : message.user.avatar.uuid;
        $("#greetings").append(
                '<tr>' +
                '<td>user' + message.user.id + '</td>' +
                '<td>' + message.text + '</td>' +
                '<td><img src="/images/' + image + '" width="50" height="50"></td>' +
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
<div id="main-content" class="container">
  <div class="row">
    <div class="col-md-6">
      <form class="form-inline">
        <div class="form-group">
          <label for="name">What is your name?</label>
          <input type="text" id="name" class="form-control" placeholder="Your name here...">
        </div>
        <button id="send" class="btn btn-default" type="submit">Send</button>
      </form>
    </div>
  </div>
  <div class="row">
    <div class="col-md-12">
      <table id="conversation" class="table table-striped">
        <thead>
        <tr>
          <th>User</th>
          <th>Text</th>
          <th>Image</th>
        </tr>
        </thead>
        <tbody id="greetings">
        <#list messages as message>
          <tr >
            <td width="50%" align="center">user${message.user.id}</td>
            <td width="50%" align="center">${message.text}</td>
            <td width="50%" align="center">
              <#if message.user.avatar??>
                <img src="/images/${message.user.avatar.uuid}" width="50" height="50">
              </#if>
            </td>
          </tr>
        </#list>
        </tbody>
      </table>
    </div>
  </div>
</div>
  <input id=file type="file" name="file" multiple>
  <button type="submit" id="upload">Avatar</button>


</body>
</html>