<%@page import='java.sql.*,connect.*,listener.*,trtmt.*,basket.*' %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Basket</title>
    <style>
        body{text-align: center;}
        #content{display: block;margin-left: 8cm;}
        #tir{display: flex;}
        /* #t1{float: left;} */
        /* #t2{float: right;} */
        #rebond{clear: both;display: flex;}
        /* #r1{float: left;} */
        /* #r2{float: right;} */
        table{width: 500px;}
        h1{margin-left: 15cm;}

    </style>
</head>
<body>
    <%
        int score1 = 0;
        int score2 = 0;
        Joueur[] joueur = new Joueur[10];
        Object[][] tir = new Object[1][1];
        Object[][] rebond = new Object[1][1];
        int[] panier=new int[1];
        int[] passeD = new int[1];
        int[] sumrebond = new int[1];
        int[] dureeP = new int[1];
        double dTotal = 0;
        double dEquipe1 = 0;
        double dEquipe2 = 0;
        double percentE1 = 0;
        double percentE2 = 0;
        
        String id=request.getParameter("id").toString();
        Traitement trtmt1 = new Traitement();
        try {
        Traitement trtmt = new Traitement();

            Connection con = trtmt.psqlConnected();
            tir = trtmt.detailTir(con,id);
            score1 = trtmt.getScore(1,id,con);
            score2 = trtmt.getScore(2,id,con);
            passeD = new int[tir.length];
            panier=new int[tir.length];
            dureeP = new int[tir.length];
            
            for(int i=0;i<tir.length;i++){
                panier[i]=trtmt.getMarque(con,Integer.parseInt(tir[i][0].toString()),id);
                passeD[i]=trtmt.getPassD(con,Integer.parseInt(tir[i][0].toString()),id);
                dureeP[i]=trtmt.getduree(con,Integer.parseInt(tir[i][0].toString()),id);
            }
            rebond = trtmt.getRebond(con,id);
            sumrebond = new int[rebond.length];
            for(int i = 0; i < rebond.length; i++){
                sumrebond[i] = trtmt.sumRebond(con,Integer.parseInt(rebond[i][3].toString()),id);
            }
            dTotal = trtmt.getdureeTotal(con,id);
            dEquipe1 = trtmt.getdureeEquipe(con,1,id);
            dEquipe2 = trtmt.getdureeEquipe(con,2,id);
            percentE1 = trtmt.percent(dTotal,dEquipe1);
            percentE2 = trtmt.percent(dTotal,dEquipe2);
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        // int sommetire1 = 0;
        // int sommematy1 = 0;
        // for(int i = 0; i < tir.length; i ++){
        //     if(Integer.parseInt(tir[i][0].toString())<5){    
            
        //     sommetire1 = sommetire1 + Integer.parseInt(tir[i][4].toString());
        //     sommematy1 = sommematy1 + panier[i];
        //     }
        // } 
        // int val1 = (sommematy1 * 100)/sommetire1;
        // int sommetire2 = 0;
        // int sommematy2 = 0;
        // for(int i = 0; i < tir.length; i ++){
        //     if(Integer.parseInt(tir[i][0].toString())>=5){    
            
        //     sommetire2 = sommetire2 + Integer.parseInt(tir[i][4].toString());
        //     sommematy2 = sommematy2 + panier[i];
        //     }
        // } 
        // int val2 = (sommematy2 * 100)/sommetire2;


    %>
        <h2>E 1 <% out.println(+score1+" | "+score2); %> E A1</h2>
        <h1><% out.println(trtmt1.round(percentE1)+"% E1"); %></h1>
        <h1><% out.println(trtmt1.round(percentE2)+"% EA1"); %></h1>
    <div id="content">
        <h1>Tir</h1>
        <div id="tir">
            <div id="t1">

                <!-- Tir E1 -->
                <table>
                    <th>Name</th>
                <th>Points</th>
                <th>Nb de Tir</th>
                <th>Passe decisive</th>
                <th>Duree</th>
                <th>Percent</th>
                <% for(int i = 0; i < tir.length; i++){ 
                    if(Integer.parseInt(tir[i][0].toString())<5){    
                        %>
                        <tr>
                            <td> <% out.println(tir[i][1]); %> </td>
                            <td> <% out.println(tir[i][2]); %> </td>
                        <td> <% out.println(panier[i]+"/"+tir[i][4]); %> </td>
                        <td> <% out.println(passeD[i]); %> </td>
                        <td> <% out.println(dureeP[i]); %> </td>
                        <td> <% out.println(trtmt1.round(dureeP[i]*100/dEquipe1)); %> </td>
                    </tr>
                    <% }
                } %>
            </table>
            </div>
            <br>
            <div id="t2">

                <!--  Tir E2 -->
            <table>
                <th>Name</th>
                <th>Points</th>
                <th>Nb de Tir</th>
                <th>Passe decisive</th>
                <th>Duree</th>
                <th>Percent</th>
                <% for(int i = 0; i < tir.length; i++){ 
                    if(Integer.parseInt(tir[i][0].toString())>=5){    
                        %>
                        <tr>
                        <td> <% out.println(tir[i][1]); %> </td>
                        <td> <% out.println(tir[i][2]); %> </td>
                        <td> <% out.println(panier[i]+"/"+tir[i][4]); %> </td>
                        <td> <% out.println(passeD[i]); %> </td>
                        <td> <% out.println(dureeP[i]); %> </td>
                        <td> <% out.println(trtmt1.round(dureeP[i]*100/dEquipe2)); %> </td>
                    </tr>
                    <% }
                } %>
            </table>
            </div>
        </div>
        <br>
        <h1>Rebond</h1>

        <div id="rebond">
            <div id="r1">

                <!-- Rebond -->
                <table>
                    <th>Name</th>
                <th>Nb de Rebond</th>
                <th>Valeur du rebond</th>
                <% for(int i = 0; i < rebond.length; i++){ 
                    if(rebond[i][3]!=null && Integer.parseInt(rebond[i][3].toString())<5){
                        %>
                        <tr>
                            <td> <% out.println(rebond[i][2]); %> </td>
                            <td> <% out.println(rebond[i][1]+" / "+sumrebond[i]); %> </td>
                            <td> <% out.println(rebond[i][0]); %> </td>
                        </tr>
                        <% }
                    } %>
                </table>
            </div>
                <br>
            <div id="r2">

                <table>
                    <th>Name</th>
                    <th>Nb de Rebond</th>
                    <th>Valeur du rebond</th>
                    <% for(int i = 0; i < rebond.length; i++){ 
                        if(rebond[i][3]!=null && Integer.parseInt(rebond[i][3].toString())>=5){
            
                       
                            %>
                            <tr>
                                <td> <% out.println(rebond[i][2]); %> </td>
                                <td> <% out.println(rebond[i][1]+" / "+sumrebond[i]); %> </td>
                                <td> <% out.println(rebond[i][0]); %> </td>
                            </tr>
                            <% }
                        } %>
                    </table>
                </div>
            </div>
            
    </div>
</body>
</html>