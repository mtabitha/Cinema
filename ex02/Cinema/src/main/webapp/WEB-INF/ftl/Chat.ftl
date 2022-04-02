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
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic' + path + "/messages", function (message) {
          console.log(message)
          showMessage(JSON.parse(message.body).con);
        });
      });
    }

    function disconnect() {
      if (stompClient !== null) {
        stompClient.disconnect();
      }
    }


    function sendMessage(path) {
      var id = document.cookie.split(';').find(s => s.match('id')).split('=')[1];
      stompClient.send("/app" + path, {}, JSON.stringify({'text': $("#text").val(), 'channelId' : id}));
    }

    function showMessages(messages) {
      $("#resp").empty()
      for (const message of messages) {
        showMessage(message)
      }
    }

    function showMessage(message) {
      $("#messages").append("<tr><td>" + message.text + "</td></tr>");
    }

    $(function () {
      $("form").on('submit', function (e) {
        e.preventDefault();
      });
      const path = new URL(document.URL, document.location, true).pathname;
      connect(path);
      $(window).unload( function () { disconnect()})
      $( "#send" ).click(function() { sendMessage(path); });
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
          <input type="text" id="text" class="form-control" placeholder="Your name here...">
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
          <th>Greetings</th>
        </tr>
        </thead>
        <tbody id="messages">
        <#list messages as message>
          <tr >
            <td width="50%" align="center">${message.text}</td>
          </tr>
        </#list>
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>