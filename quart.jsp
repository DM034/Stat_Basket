<%@page import='java.sql.*,connect.*,listener.*,trtmt.*,basket.*' %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=d, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%  
        String idMatch = request.getParameter("id").toString();
        int[] qt = new int[1];
        try{
            Treatement trtmt = new Treatement();
            Connection con = trtmt.psqlConnected();
            qt = trtmt.getQuart(con,idMatch);
        
            con.close();

        }catch(Exception e){
            out.print(e);
        }
    %>
    <!-- <p> <a href=" statistiqueTotal.jsp?id=<% out.print(idMatch); %>"><% out.print("Total"); %> </a></p> -->
    <% for(int i=0;i<qt.length;i++) { %>
        <p><a href="statistique.jsp?qt=<% out.print(qt[i]); %>&&id=<% out.print(idMatch); %>"> <% out.print("Quart-temps "+qt[i]); %></a></p>
    <% } %>   
  

</body>
</html>