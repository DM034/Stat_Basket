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
    <% Object[][] match=new Object[1][1]; 
        try{
            Treatement trtmt = new Treatement();
            Connection con = trtmt.psqlConnected();
            match=trtmt.select("match","id",con);

            con.close();

        }catch(Exception e){
            out.print(e);
        }
    %>
    <% for(int i=0;i<match.length;i++) { %>
        <p> <a href=" quart.jsp?id=<% out.print(match[i][0]); %>"><% out.print(match[i][0]); %> </a></p>

     <% } %>   

</body>
</html>