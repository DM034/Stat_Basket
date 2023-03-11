package trtmt;

import connect.*;
import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.sql.*;
import java.util.Vector;

public class Traitement {

  public Connection psqlConnected() throws Exception {
    Connexion con = new Connexion();
    Connection connex = con.connex(
      "jdbc:postgresql://localhost:5432/basketpro",
      "postgres",
      "366325"
    );
    connex.setAutoCommit(false);
    return connex;
  }

  public String idM() throws Exception {
    String id = generateID("M", getSeq("idMatch_seq", null));
    return id;
  }

  public String generateID(String pref, int seq) {
    String zero = "0000";
    String sequ = String.valueOf(seq);
    int len = zero.length() - sequ.length();
    StringBuilder val = new StringBuilder();
    val.append(pref);
    for (int i = 0; i < len; i++) {
      val.append("0");
    }
    val.append(sequ);
    String result = val.toString();
    return result;
  }

  public int getSeq(String seqName, Connection con) throws Exception {
    boolean open = true;
    if (con == null) {
      con = psqlConnected();
    } else {
      open = false;
    }
    Statement stmt = null;
    int nb = 0;
    try {
      stmt = con.createStatement();
      ResultSet rSet = stmt.executeQuery("select nextval('" + seqName + "')");
      while (rSet.next()) {
        nb = rSet.getInt(1);
      }
    } catch (Exception e) {
      // TODO: handle exception
    } finally {
      if (open == true) {
        con.close();
      }
    }
    return nb;
  }

  public void createSeq(String seqName, int incr, int min, Connection con)
    throws Exception {
    boolean open = true;
    if (con == null) {
      con = psqlConnected();
    } else {
      open = false;
    }
    Statement stat = null;
    try {
      stat = con.createStatement();
      stat.executeUpdate(
        "create sequence " +
        seqName +
        " increment by " +
        incr +
        " start with " +
        min +
        " minvalue 1"
      );

      con.commit();
    } catch (Exception e) {
      // TODO: handle exception
      con.rollback();
      e.printStackTrace();
    } finally {
      if (open == true) {
        con.close();
      }
    }
  }

  public void delete(String table, String col, String ind, Connection con)
    throws Exception {
    boolean open = true;
    if (con == null) {
      con = psqlConnected();
    } else {
      open = false;
    }
    Statement stat = null;
    try {
      stat = con.createStatement();
      stat.executeUpdate(
        "delete from " + table + " where " + col + " = '" + ind + "'"
      );

      con.commit();
    } catch (Exception e) {
      // TODO: handle exception
      con.rollback();
      e.printStackTrace();
    } finally {
      if (open == true) {
        con.close();
      }
    }
  }

  public void update(
    String table,
    String value,
    String col,
    String col1,
    String ind,
    Connection con
  ) throws Exception {
    boolean open = true;
    if (con == null) {
      con = psqlConnected();
    } else {
      open = false;
    }
    Statement stat = null;
    try {
      stat = con.createStatement();
      stat.executeUpdate(
        "update " +
        table +
        " set " +
        col +
        " = " +
        value +
        " where " +
        col1 +
        " = '" +
        ind +
        "'"
      );

      con.commit();
    } catch (Exception e) {
      // TODO: handle exception
      con.rollback();
      e.printStackTrace();
    } finally {
      if (open == true) {
        con.close();
      }
    }
  }

  public void inserToTable(String table, String[] value, Connection con)
    throws Exception {
    boolean open = true;
    if (con == null) {
      con = psqlConnected();
    } else {
      open = false;
    }
    Statement stat = null;
    try {
      StringBuilder str = new StringBuilder();
      String[] valeur = new String[value.length];
      for (int i = 0; i < valeur.length - 1; i++) {
        valeur[i] = "'" + value[i] + "',";
        str = str.append(valeur[i]);
      }
      valeur[value.length - 1] = "'" + value[valeur.length - 1] + "'";
      str.append(valeur[value.length - 1]);
      // System.out.println("string"+str.toString());

      stat = con.createStatement();
      // System.out.println("insert into "+table+ " values("+str+")");
      stat.executeUpdate("insert into " + table + " values(" + str + ")");
      con.commit();
    } catch (Exception e) {
      // TODO: handle exception
      con.rollback();
      e.printStackTrace();
    } finally {
      if (open == true) {
        con.close();
      }
    }
  }

  public Object[][] select(String table, String att, Connection con)
    throws Exception {
    boolean open = true;
    if (con == null) {
      con = psqlConnected();
    } else {
      open = false;
    }
    Statement stat = null;
    Object[][] obj = null;
    try {
      stat = con.createStatement();
      ResultSet rSet = stat.executeQuery("select count(*) from " + table);
      int row = 0;
      while (rSet.next()) {
        row = rSet.getInt(1);
      }

      ResultSet rSet2 = rsltset(table, att, con);
      ResultSetMetaData rSetMetaData = rSet2.getMetaData();
      int col = rSetMetaData.getColumnCount();
      obj = new Object[row][col];
      int j = 0;
      while (rSet2.next()) {
        for (int i = 0; i < col; i++) {
          if (rSet2.getObject(i + 1) == null) {
            obj[j][i] = 0;
            //  System.out.println("null");
          } else {
            obj[j][i] = rSet2.getObject(i + 1);
          }
        }
        j++;
      }
      // System.out.println(obj[0][0]);
      for (int l = 0; l < row; l++) {
        for (int m = 0; m < col; m++) {
          System.out.println(obj[l][m].toString());
        }
      }
      rSet.close();
      rSet2.close();
      return obj;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      return obj;
    } finally {
      if (open == true) {
        con.close();
      }
      return obj;
    }
  }

  public Object[][] result_tObjects(String table, Connection con)
    throws Exception {
    boolean open = true;
    if (con == null) {
      con = psqlConnected();
    } else {
      open = false;
    }
    Statement stat = null;
    Object[][] obj = null;
    try {
      stat = con.createStatement();
      ResultSet rSet = stat.executeQuery("select count(*) from " + table);
      int row = 0;
      while (rSet.next()) {
        row = rSet.getInt(1);
      }

      ResultSet rSet2 = rsltset(table, "*", con);
      ResultSetMetaData rSetMetaData = rSet2.getMetaData();
      int col = rSetMetaData.getColumnCount();
      obj = new Object[row][col];
      int j = 0;
      while (rSet2.next()) {
        for (int i = 0; i < col; i++) {
          if (rSet2.getObject(i + 1) == null) {
            obj[j][i] = 0;
            //  System.out.println("null");
          } else {
            obj[j][i] = rSet2.getObject(i + 1);
          }
        }
        j++;
      }
      rSet.close();
      rSet2.close();
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    } finally {
      if (open == true) {
        con.close();
      }
    }
    return obj;
  }

  public ResultSet rsltset(String table, String att, Connection con)
    throws Exception {
    boolean open = true;
    if (con == null) {
      con = psqlConnected();
    } else {
      open = false;
    }
    Statement stat = null;
    ResultSet rSet = null;
    try {
      stat = con.createStatement();
      rSet = stat.executeQuery("select " + att + " from " + table);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    } finally {
      if (open == true) {
        con.close();
      }
    }
    return rSet;
  }

  public Object[][] detailTir(Connection con, String idJeu) throws Exception {
    boolean open = false;
    if (con == null) {
      try {
        con = psqlConnected();
        open = true;
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
    }
    Object[][] obj = new Object[1][1];
    try {
      Statement stat = con.createStatement();
      String sql =
        "SELECT Joueur.id,Joueur.name,sum(tir.point),Joueur.idMatch,count(tir.point) FROM tir RIGHT OUTER JOIN Joueur on tir.idP=Joueur.id and tir.idMatch=Joueur.idMatch WHERE Joueur.idMatch='" +
        idJeu +
        "' GROUP BY Joueur.id,Joueur.name,Joueur.idMatch ORDER BY Joueur.id";
      ResultSet res = stat.executeQuery(sql);

      int col = res.getMetaData().getColumnCount();
      int n = 10;

      obj = new Object[n][col];
      int j = 0;
      while (res.next()) {
        for (int i = 0; i < col; i++) {
          if (res.getObject(i + 1) != null) {
            obj[j][i] = res.getObject(i + 1);
          } else {
            obj[j][i] = 0;
          }
        }
        j++;
      }

      return obj;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      System.out.println(e.getMessage());
    } finally {
      if (open == true) {
        try {
          con.close();
        } catch (Exception e) {
          // TODO: handle exception
        }
      }
    }

    return obj;
  }

  public int getScore(int team, String jeu, Connection con) throws Exception {
    int n = 0;
    boolean open = false;
    if (con == null) {
      try {
        con = psqlConnected();
        open = true;
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    try {
      Statement stat = con.createStatement();

      String sql1 =
        "select sum(point) from tir where idp<5 and idMatch='" + jeu + "'";
      String sql2 =
        "select sum(point) from tir where idp>=5 and idMatch='" + jeu + "'";
      String sql = null;
      if (team == 2) {
        sql = sql2;
      } else {
        sql = sql1;
      }
      System.out.println(sql);
      ResultSet rSet = stat.executeQuery(sql);

      while (rSet.next()) {
        n = rSet.getInt(1);
      }
      return n;
    } catch (Exception e) {
      e.printStackTrace();
      return n;
    } finally {
      if (open == true) {
        try {
          con.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  public int getMarque(Connection con, int idPl, String idJeu)
    throws Exception {
    int pts = 0;
    try {
      Statement stat = con.createStatement();
      String sql =
        "select count(point) from tir  WHERE point!=0 and idMatch='" +
        idJeu +
        "' and idp='" +
        idPl +
        "'";
      System.out.println(sql);
      ResultSet rSet = stat.executeQuery(sql);

      while (rSet.next()) {
        if (rSet.getObject(1) != null) {
          pts = rSet.getInt(1);
        } else {
          pts = 0;
        }
      }
      rSet.close();
      stat.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pts;
  }

  public int getPassD(Connection con, int idPl, String idJeu) throws Exception {
    int pts = 0;
    try {
      Statement stat = con.createStatement();
      String sql =
        "select count(idp2) from passe where type='Decisive' and idp2='" +
        idPl +
        "' and idMatch='" +
        idJeu +
        "'";
      System.out.println(sql);
      ResultSet rSet = stat.executeQuery(sql);

      while (rSet.next()) {
        if (rSet.getObject(1) != null) {
          pts = rSet.getInt(1);
        } else {
          pts = 0;
        }
      }
      rSet.close();
      stat.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pts;
  }

  public Object[][] getRebond(Connection con, String idJeu) throws Exception {
    boolean open = false;
    if (con == null) {
      try {
        con = psqlConnected();
        open = true;
      } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace();
      }
    }
    Object[][] obj = new Object[1][1];
    try {
      Statement stat = con.createStatement();
      String sql =
        "select distinct(value),count(value),joueur.name,joueur.id from rebond JOIN joueur on joueur.id=rebond.idP and rebond.idMatch=joueur.idMatch where rebond.idMatch='" +
        idJeu +
        "' group by value,rebond.idP,joueur.name,joueur.id";
      System.out.println(sql);
      ResultSet res = stat.executeQuery(sql);

      Statement countStat = con.createStatement();
      ResultSet count = countStat.executeQuery(
        "SELECT count(*) from rebond WHERE rebond.idMatch='" + idJeu + "'"
      );

      int col = res.getMetaData().getColumnCount();
      int n = 0;

      while (count.next()) {
        n = count.getInt(1);
      }

      obj = new Object[n][col];
      int j = 0;
      while (res.next()) {
        for (int i = 0; i < col; i++) {
          if (res.getObject(i + 1) != null) {
            obj[j][i] = res.getObject(i + 1);
          } else {
            obj[j][i] = 0;
          }
        }
        j++;
      }

      return obj;
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
      System.out.println(e.getMessage());
    } finally {
      if (open == true) {
        try {
          con.close();
        } catch (Exception e) {
          // TODO: handle exception
        }
      }
    }

    return obj;
  }

  public int sumRebond(Connection con, int idPl, String idJeu)
    throws Exception {
    int pts = 0;
    try {
      Statement stat = con.createStatement();
      String sql =
        "select count(idp) from rebond where idp='" +
        idPl +
        "' and idMatch='" +
        idJeu +
        "'";
      System.out.println(sql);
      ResultSet rSet = stat.executeQuery(sql);

      while (rSet.next()) {
        if (rSet.getObject(1) != null) {
          pts = rSet.getInt(1);
        } else {
          pts = 0;
        }
      }
      rSet.close();
      stat.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pts;
  }

  public int getduree(Connection con, int idPl, String idJeu) throws Exception {
    int pts = 0;
    try {
      Statement stat = con.createStatement();
      String sql =
        "select sum(duree.duree) from duree  WHERE idMatch='" +
        idJeu +
        "' and idp='" +
        idPl +
        "'";
      System.out.println(sql);
      ResultSet rSet = stat.executeQuery(sql);

      while (rSet.next()) {
        if (rSet.getObject(1) != null) {
          pts = rSet.getInt(1);
        } else {
          pts = 0;
        }
      }
      rSet.close();
      stat.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pts;
  }

  public int getdureeTotal(Connection con, String idJeu) throws Exception {
    int pts = 0;
    try {
      Statement stat = con.createStatement();
      String sql =
        "SELECT sum(duree.duree) from duree where idmatch='" + idJeu + "'";

      System.out.println(sql);
      ResultSet rSet = stat.executeQuery(sql);

      while (rSet.next()) {
        if (rSet.getObject(1) != null) {
          pts = rSet.getInt(1);
        } else {
          pts = 0;
        }
      }
      rSet.close();
      stat.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pts;
  }

  public int getdureeEquipe(Connection con, int equipe, String idJeu)
    throws Exception {
    int pts = 0;
    try {
      Statement stat = con.createStatement();
      String sql;
      if (equipe == 1) {
        sql =
          "SELECT sum(duree.duree) from duree where idmatch='" +
          idJeu +
          "' and idp<5";
      } else {
        sql =
          "SELECT sum(duree.duree) from duree where idmatch='" +
          idJeu +
          "' and idp>=5";
      }

      System.out.println(sql);
      ResultSet rSet = stat.executeQuery(sql);

      while (rSet.next()) {
        if (rSet.getObject(1) != null) {
          pts = rSet.getInt(1);
        } else {
          pts = 0;
        }
      }
      rSet.close();
      stat.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pts;
  }

  public double percent(double total, double ask) {
    double valiny = 0;
    valiny = ask * 100 / total;
    return valiny;
  }

  public double round(double val) {
    double valiny = 0;
    valiny = (double) Math.round(val * 100) / 100;
    return valiny;
  }
}
