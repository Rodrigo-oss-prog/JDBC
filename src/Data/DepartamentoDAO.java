package Data;

import Model.Departamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO implements DAO<Departamento>{


    @Override
    public void save(Departamento dep) {
        String sql = "INSERT INTO departamento(id, nome, sigla) VALUES (?, ?, ?)";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, dep.getId());
            stmt.setString(2, dep.getNome());
            stmt.setString(3, dep.getSigla());
            stmt.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();

        }

    }

    @Override
    public void update(Departamento dep) {
        String sql = "UPDATE departamento SET nome =? WHERE id =?";
        try {
            PreparedStatement stmt = ConnectionFactory.createStatement(sql);

            stmt.setString(1, dep.getNome());
            stmt.setString(2, dep.getSigla());
            stmt.setInt(3, dep.getId());

            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Departamento type) {
        String sql = "DELETE FROM departamento WHERE id =?";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)) {

            stmt.setInt(1, type.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Departamento findById(int id) {
        String sql = "SELECT * FROM departamento WHERE id =?";
        Departamento dep = null;
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                dep = new Departamento(rs.getInt("id")
                        ,rs.getString("nome"),
                        rs.getString("sigla"));

            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return dep;
    }

    @Override
    public List<Departamento> findAll() {
        String sql = "SELECT * FROM departamento";
        List<Departamento> departamentos = new ArrayList<>();
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Departamento dep = new Departamento(rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sigla"));

                departamentos.add(dep);


            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return departamentos;
    }
}
