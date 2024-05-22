import Data.DepartamentoDAO;
import Data.FuncionarioDAO;
import Model.Departamento;
import Model.Funcionario;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Exercício 1\n");



        Departamento departamento1 = new Departamento( "Secretaria", "SEC1");
        Departamento departamento2 = new Departamento( "Financeiro", "FIN1");
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        departamentoDAO.save(departamento1);
        departamentoDAO.save(departamento2);

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario func1 = new Funcionario( "Jonas A.", "22.233.556-11", departamento1);
        Funcionario func2 = new Funcionario( "Carla B.", "22.233.556-12", departamento2);

        funcionarioDAO.save(func1);
        funcionarioDAO.save(func2);

        List<Departamento> listaDepartamentos = departamentoDAO.findAll();
        for (Departamento de : listaDepartamentos)
            System.out.println(de);

        List<Funcionario> listaFuncionarios = funcionarioDAO.findAll();
        for (Funcionario fu : listaFuncionarios)
            System.out.println(fu);
    }
}