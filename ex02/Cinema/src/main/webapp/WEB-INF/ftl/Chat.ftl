<html>
<head>
  <title>Hello WebSocket</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<#--  <script src="classpath:../ftl/app.js"></script>-->
  <script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
      $("#connect").prop("disabled", connected);
      $("#disconnect").prop("disabled", !connected);
      if (connected) {
        $("#conversation").show();
      }
      else {
        $("#conversation").hide();
      }
      $("#greetings").html("");
    }

    function connect(path) {
      var socket = new SockJS('/ws');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic' + path + "/messages", function (message) {
          showGreeting(JSON.parse(message.body));
        });
      });
    }

    function disconnect() {
      if (stompClient !== null) {
        stompClient.disconnect();
      }
      setConnected(false);
      console.log("Disconnected");
    }

    function sendName(path) {
      stompClient.send("/app" + path, {}, JSON.stringify({'text': $("#text").val()}));
    }

    function showGreeting(message) {
      $("#greetings").append("<tr><td>" + message.text + "</td></tr>");
    }

    $(function () {
      $("form").on('submit', function (e) {
        e.preventDefault();
      });
      const path = new URL(document.URL, document.location, true).pathname;
      console.log('Path: ' + path);
      connect(path);
      $( "#disconnect" ).click(function() { disconnect(); });
      $( "#send" ).click(function() { sendName(path); });
    });
  </script>
</head>
<body>

<div id="main-content" class="container">
  <div class="row">
    <div class="col-md-6">
      <form class="form-inline">
        <div class="form-group">
          <label for="connect">WebSocket connection:</label>
          <button id="connect" class="btn btn-default" type="submit">Connect</button>
          <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
          </button>
        </div>
      </form>
    </div>
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
        <tbody id="greetings">
        </tbody>
      </table>
    </div>
  </div>
</div>
</body>
</html>