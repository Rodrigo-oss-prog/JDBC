import Data.DepartamentoDAO;
import Data.FuncionarioDAO;
import Model.Departamento;
import Model.Funcionario;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Exercício 1\n");



        Departamento departamento1 = new Departamento( 1,"Secretaria", "SEC1");
        Departamento departamento2 = new Departamento( 2,"Financeiro", "FIN1");
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        departamentoDAO.save(departamento1);
        departamentoDAO.save(departamento2);

        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        Funcionario func1 = new Funcionario( 1, "22.233.556-11", "José", departamento1);
        Funcionario func2 = new Funcionario( 2,"222.333.444-13" , "Carla",departamento2);

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