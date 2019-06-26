package Folhdadepagamento;
import java.util.*;
public class Comissionado extends  Empregado
{

    private  double percentualComissao;
    private double totalVendas;

    public Comissionado()
    {
        this.percentualComissao = 0;
        this.totalVendas = 0;
        this.setTipo("comissionado");
        this.setFreq(2);
        this.setDia("sexta");
        this.setNovaAgenda("semanal");
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(double percentualComissao) {
        this.percentualComissao = percentualComissao;
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(double totalVendas) {
        this.totalVendas += totalVendas;
    }

    @Override
    public void addEmpregado(Empregado employee, int id) {
        super.addEmpregado(employee, id);
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o salário do comissionado");
        this.setSalario(input.nextDouble());
        System.out.println("Digite o percentual de comissão");
        this.setPercentualComissao(input.nextDouble());
        System.out.println("Empregado adicionado com sucesso");
    }

    public void vendas(Empregado employee)
    {
        int diavenda,mesvenda,anovenda;
        Scanner input = new Scanner(System.in);
        System.out.println("Informe a data da venda:");
        System.out.print("Dia: ");
        diavenda = input.nextInt();
        System.out.print("Mes: ");
        mesvenda = input.nextInt();
        System.out.print("Ano: ");
        anovenda = input.nextInt();
        System.out.println("Informe o valor da venda: ");
        double solds  = input.nextDouble();
        this.setTotalVendas(solds);
        System.out.println("Resultado da venda:\n" + "Empregado: "+this.getNome() + "\nData da venda: "+diavenda + "/"+mesvenda + "/"+anovenda +"\nValor da venda: R$"+solds);


    }

    @Override
    public void editarEmpregado(Empregado employee) {
        super.editarEmpregado(employee);
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o salário do comissionado");
        this.setSalario(input.nextDouble());
        System.out.println("Digite o percentual de comissão");
        this.setPercentualComissao(input.nextDouble());
        System.out.println("Empregado editado com sucesso");

    }

    @Override
    public void rodarFolha(Empregado employee) {
        super.rodarFolha(employee);
        double salary = this.getSalario() + (this.getTotalVendas() * this.getPercentualComissao());
        if (this.isSindicato()) salary = salary - (salary * (this.getTaxaSindical() + this.getTaxaDeServico()/100));
        System.out.println("Salario: R$"+salary);
        System.out.println("\n\n");
    }


}
