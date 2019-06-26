package Folhdadepagamento;
import java.util.*;
public class Assalariado extends Empregado
{
    public Assalariado()
    {
        this.setTipo("assalariado");
        this.setDia("Último dia útil do mês");
        this.setNovaAgenda("mensal");

    }

    @Override
    public void addEmpregado(Empregado employee, int id) {
        super.addEmpregado(employee, id);
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o salário do Assalariado");
        setSalario(input.nextDouble());
        System.out.println("Empregado adicionado com sucesso");
    }

    @Override
    public void editarEmpregado(Empregado employee) {
        super.editarEmpregado(employee);
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o salário do Assalariado");
        setSalario(input.nextDouble());
        System.out.println("Empregado editado com sucesso");

    }

    @Override
    public void rodarFolha(Empregado employee) {
        super.rodarFolha(employee);
        double salary = this.getSalario();
        if (this.isSindicato()) salary = salary - (salary * (this.getTaxaSindical() + this.getTaxaDeServico()/100));
        System.out.println("Salario: R$"+salary);
        System.out.println("\n\n");
    }
}
