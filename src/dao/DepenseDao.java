package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Depense;
import util.DbUtil;
import java.util.logging.Logger;

public class DepenseDao {

    private Connection connection;
    private static final Logger LOGGER = Logger.getLogger(DepenseDao.class.getName());

    public DepenseDao() {
        connection = DbUtil.getConnection();
    }

    public void addDepense(Depense depense) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into historique(depense,description,date_depense) values (?, ?, ?)"
            );
            // Parameters start with 1
            preparedStatement.setDouble(1, depense.getDepense());
            preparedStatement.setString(2, depense.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(depense.getDate_depense().getTime()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepense(int depenseId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from historique where id=?"
            );
            // Parameters start with 1
            preparedStatement.setInt(1, depenseId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDepense(Depense depense) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update historique set depense=?, description=?, date_depense=?" +
                            "where id=?");
            // Parameters start with 1
            preparedStatement.setDouble(1, depense.getDepense());
            preparedStatement.setString(2, depense.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(depense.getDate_depense().getTime()));
            preparedStatement.setInt(4, depense.getId_depense());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Depense> getAllDepenses() {
        List<Depense> depenses = new ArrayList<Depense>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from historique");
            while (rs.next()) {
                Depense depense = new Depense();
                depense.setId_depense(rs.getInt("id"));
                depense.setDepense(rs.getDouble("depense"));
                depense.setDescription(rs.getString("description"));
                depense.setDate_depense(rs.getDate("date_depense"));
                depenses.add(depense);
                LOGGER.info("Depenses List: " + depenses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return depenses;
    }

    public Depense getDepenseById(int depenseId) {
        Depense depense = new Depense();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from historique where id=?"
            );
            preparedStatement.setInt(1, depenseId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                depense.setId_depense(rs.getInt("id"));
                depense.setDepense(rs.getDouble("depense"));
                depense.setDescription(rs.getString("description"));
                depense.setDate_depense(rs.getDate("date_depense"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return depense;
    }
}
