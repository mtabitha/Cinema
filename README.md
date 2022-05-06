# Cinema

Implemented a movie theater administrator functionality using Spring MVC mechanisms.
Each new exercise complements the previous one.

## ex00
- */admin/panel/halls* The page for working with movie halls contains a list of all movie halls created by an administrator. The administrator can create a movie hall with a certain configuration.To each movie hall, a serial number and number of seats are assigned.
- */admin/panel/films* A movie page contains a list of all movies created by an administrator. An administrator can add a movie. For each movie, the title, year of release, age restrictions, and a description are specified. It is also possible to upload a poster for a movie.
- */admin/panel/sessions* A page for working with movie shows. An administrator can create a session for a certain movie in a certain movie hall at a required time. An administrator should be able to indicate a ticket cost.

   Implemented loading of all movie and movie hall data as attributes onto the page for subsequent selection by an administrator.
repository layer will implemented using **Hibernate** framework in conjunction with **JPA**. Thus, each of the models annotated with *@Entity*.
- Each page a **Freemarker** template.
- Spring controllers used.
- WebApplicationInitializer implementation.

## ex01
   We will use **JQuery** frontend technology to implement an **AJAX** request to get a list of all shows according to a search query. In response to a */sessions/search?filmName=<movie title>* GET request, the server return a list of shows with movie titles matching a search query. 
On the basis of this data, a list of movie shows is compiled on a user’s page in real time without completely reloading the page during user input. 
When clicking on a movie title, a user shall follow */sessions/session-id* link. On this page, a user see a full description of the movie, along with information about the movie hall assigned to this show.

## ex02
  For each movie, created a chat room with a discussion. Any user who follows */films/{film-id}/chat* link will see a page with a movie discussion in real time. This page is accessible from the movie show page.Each chat user is assigned a unique identifier, which is stored in browser’s cookies. Thus,one user will correspond to one browser.
- When this page is opened, the last 20 chat messages be loaded.
- Messages  be available after restarting the application, refreshing pages in browsers, etc.
- Messaging implemented using Websockets-based **STOMP** protocol.
