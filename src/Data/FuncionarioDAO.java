package Data;

import Model.Departamento;
import Model.Funcionario;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements DAO<Funcionario>{
    @Override
    public void save(Funcionario func) {
        String sql = "INSERT INTO funcionario VALUES ( ?, ?, ?, ?)";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, func.getId());
            stmt.setString(2, func.getCpf());
            stmt.setString(3, func.getNome());
            stmt.setInt(4, func.getDepartamento().getId());
            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(Funcionario func) {
        String sql = "UPDATE funcionario SET nome =?, cpf =?, departamento =? WHERE id =?";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){

            stmt.setString(1, func.getCpf());
            stmt.setString(2, func.getNome());
            stmt.setString(3, func.getDepartamento().getNome());
            stmt.setInt(4, func.getId());

            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Funcionario func) {
        String sql = "DELETE FROM funcionario WHERE id = ? ";
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, func.getId());
            stmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Funcionario findById(int idFunc) {
        String sql = "SELECT * FROM funcionario WHERE id =?";
        Funcionario func = null;
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            stmt.setInt(1, idFunc);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Departamento dep = new DepartamentoDAO().findById(rs.getInt("departamento"));
                 func = new Funcionario(rs.getInt("id"),rs.getString("nome"), rs.getString("cpf"),
                        dep);


            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return func;
    }

    @Override
    public List<Funcionario> findAll() {
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcs = new ArrayList<>();
        try(PreparedStatement stmt = ConnectionFactory.createStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Departamento dep = new DepartamentoDAO().findById(rs.getInt("departamento"));
                Funcionario func = new Funcionario(rs.getInt("id"),rs.getString("nome"),
                        rs.getString("cpf"),
                        dep);
                funcs.add(func);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return funcs;
    }
}
